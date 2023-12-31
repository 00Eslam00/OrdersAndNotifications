package com.FCAI.OrdersAndNotifications.DTOS;

import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String msg;
    private T responseObj;

    public Response() {
        code = 0;
        msg = "";
        responseObj = null;
    }

    public Response(T resObj) {
        code = 0;
        msg = "";
        responseObj = resObj;
    }
}
