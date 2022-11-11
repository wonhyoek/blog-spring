package com.cos.blog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardResDTO {
    private int id;
    private String title;
    private String content;
}
