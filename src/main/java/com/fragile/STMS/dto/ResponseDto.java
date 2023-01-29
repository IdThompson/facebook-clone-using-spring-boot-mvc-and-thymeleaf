package com.fragile.STMS.dto;

import com.fragile.STMS.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
    private String message;
    private User user;
    private boolean status;
}
