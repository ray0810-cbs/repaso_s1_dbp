package com.example.repasito1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO<T> {
    private int status;
    private String message;
    private String error;
    private T data;
}
