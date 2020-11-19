package com.example.services;

import com.example.DTOs.UserDto;
import com.example.DTOs.VerificationTokenDto;
import com.example.repos.TokenRepo;
import com.example.repos.UserRepo;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class UserActivationService {

    @Autowired
    private SendGrid sendGrid;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserRepo userRepo;

    public Response sendActivationEmail(UserDto userDto, String token) throws IOException {
        Email from = new Email("YOUREMAILADDRESS@YOURCOMAPNY");
        String subject = "Your account activation link";
        Email to = new Email(userDto.getEmail());
        Content content = new Content("text/plain",
                String.format("Please go to http://localhost:8080/user/activate?token=%s", token));
        Mail mail = new Mail(from, subject, to, content);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        return sendGrid.api(request);
    }

    public boolean activateAccount(String token) {
        VerificationTokenDto byToken = tokenRepo.findByToken(token);
        long ts = new Date().getTime();
        if (byToken != null && byToken.getExp() >= ts) {
            UserDto user = byToken.getUser();
            user.setVerified(true);
            userRepo.save(user);
            return true;
        }
        return false;
    }

    public boolean generateNewToken(String token) {
        VerificationTokenDto byToken = tokenRepo.findByToken(token);
        if (byToken == null){
            return false;
        }
        String newToken = userRegistrationService.generateToken(byToken.getUser());
        try {
            this.sendActivationEmail(byToken.getUser() , newToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
