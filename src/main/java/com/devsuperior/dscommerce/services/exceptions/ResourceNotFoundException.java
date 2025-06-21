package com.devsuperior.dscommerce.services.exceptions;

public class ResourceNotFoundException extends  RuntimeException{
    //Runtime exception não exige bloco tryCatch (Exception sim)

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
