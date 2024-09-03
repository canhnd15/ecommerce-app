package com.davidnguyenshop.app.dtos;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
}
