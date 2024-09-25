package com.devsouzx.ecommerce_with_docker.dto;

import lombok.Data;

@Data
public class EmailConfirmationRequest {
    private String email;
    private String confirmationCode;
}
