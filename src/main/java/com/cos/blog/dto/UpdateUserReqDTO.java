package com.cos.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserReqDTO {
    private int id;
    private String username;
    private String password;
    private String email;
}
