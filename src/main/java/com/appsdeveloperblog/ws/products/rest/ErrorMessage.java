package com.appsdeveloperblog.ws.products.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessage {
    private Date timestamp;
    private String message;
    private String details;
}
