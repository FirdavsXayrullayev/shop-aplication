package kv25.shop.service.impl;

import jakarta.mail.internet.MimeMessage;
import kv25.shop.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    @Override
    public Boolean sendMessage(String toEmail, String message) {
        try {
            MimeMessage sendMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(sendMessage, true);

            helper.setTo(toEmail);
            helper.setSubject("Yunusobod shop");
            helper.setText(message, true);
//            helper.addAttachment("XayrullayevFirdavs.pdf", new File("C:\\Users\\Firdavs\\Downloads\\XayrullayevFirdavs.pdf"));
            javaMailSender.send(sendMessage);

            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
