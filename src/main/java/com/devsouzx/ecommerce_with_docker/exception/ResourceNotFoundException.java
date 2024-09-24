package com.devsouzx.ecommerce_with_docker.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){ super(message);}
}
