package com.climasync.common.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomErrorMessage {

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
