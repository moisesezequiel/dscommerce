package com.devsuperior.dscommerce.services.exceptions;

public class RecourceNotFoundException extends  RuntimeException{
    //Runtime exception não exige bloco tryCatch (Exception sim)

    public RecourceNotFoundException(String msg) {
        super(msg);
    }
}
