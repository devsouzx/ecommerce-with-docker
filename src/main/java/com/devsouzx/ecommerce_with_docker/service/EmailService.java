package com.devsouzx.ecommerce_with_docker.service;

import com.devsouzx.ecommerce_with_docker.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendOrderConfirmation(Order order){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("3210de6d30d855");
        message.setTo(order.getUser().getEmail());
        message.setSubject("Order confirmation");
        message.setText("Your order has been confirmed. Order ID " + order.getId());
        mailSender.send(message);
    }
}
