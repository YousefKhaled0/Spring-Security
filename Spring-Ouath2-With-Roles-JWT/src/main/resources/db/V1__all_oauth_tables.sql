create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

CREATE TABLE public.role
(
    id serial PRIMARY KEY,
    name text
);

CREATE TABLE public.users
(
    id serial PRIMARY KEY,
    username character varying(100) UNIQUE NOT NULL,
    password character varying(256)  NOT NULL,
    first_name character varying(256) NOT NULL,
    last_name character varying(256)  NOT NULL,
    phone_number character varying(256)  NOT NULL,
    email_address character varying(256)  NOT NULL,
    role_id integer NOT NULL,
     CONSTRAINT fk_role
          FOREIGN KEY(role_id)
    	  REFERENCES role(id)
);

CREATE TABLE public.oauth_client_token
(
    token_id character varying(256),
    token bytea,
    authentication_id character varying(256),
    user_name character varying(256),
    client_id character varying(256)
);

CREATE TABLE public.oauth_code
(
    code character varying(256),
    authentication bytea
);


INSERT INTO public.oauth_client_details(
	client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
	VALUES ('web', null, '$2a$10$MZThN8agKG0UNGoMEd06M.q5UydSfqpr0OTa18dyJ4ZubXRe7F4MO', 'read,write', 'password', null, null , 604800, 604800, null, null);

INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('USER');

INSERT INTO public.users(
	username, first_name, last_name ,phone_number,email_address, password , role_id)
	VALUES ('admin', 'Admin0', 'ADMIN0','01016382678','admin@yopmail.com',  '$2a$10$xZ06rVCcmG.kCg277lkroueZ.q1qE3A8e0SqnsbmxfDsYuD1NeZPm' , 1);


INSERT INTO public.users(
	username, first_name, last_name ,phone_number,email_address, password , role_id)
	VALUES ('user', 'User0', 'USER0','01016382678','mod@yopmail.com',  '$2a$10$JydGk3Vw5ERAwdHLJ596/.kE2oeoWPAdWqWoT/6p2letocuwOb1dq' , 2);
