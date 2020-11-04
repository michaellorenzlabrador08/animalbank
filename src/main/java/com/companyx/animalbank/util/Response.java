package com.companyx.animalbank.util;

import liquibase.pro.packaged.T;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private T data;
    private boolean success;
    private String errorMessage;

    public Response() {

    }

    public void success(T data) {
        this.data = data;
        this.success = true;
    }

    public void failed(String errorMessage) {
        this.data = null;
        this.success = false;
        this.errorMessage = errorMessage;
    }


}
