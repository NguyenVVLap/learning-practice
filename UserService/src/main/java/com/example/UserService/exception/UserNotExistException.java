package com.example.UserService.exception;

import lombok.*;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UserNotExistException extends RuntimeException {
    private String message;
}
