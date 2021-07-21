package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${jwt.keys.privateKey}")
	private String privateKey;

	@Value("${jwt.keys.publicKey}")
	private String publicKey;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private DefaultTokenServices defaultTokenServices;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http
				.csrf()
				.disable()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.oauth2ResourceServer().jwt();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer config) {
		config.tokenServices(defaultTokenServices);
	}

	@Bean
	public JwtDecoder jwtDecoder(){
		return JwtDecoders.fromIssuerLocation("http://localhost/oauth/token");
	}

}
