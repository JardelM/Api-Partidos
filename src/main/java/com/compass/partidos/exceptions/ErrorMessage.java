package com.compass.partidos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorMessage {

    private Integer statusCode;
    private Date timeStamp;
    private String message;
    private String status;
}
