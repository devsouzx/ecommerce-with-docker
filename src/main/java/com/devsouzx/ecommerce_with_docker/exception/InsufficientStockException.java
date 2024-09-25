package com.devsouzx.ecommerce_with_docker.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message){ super(message);}
}