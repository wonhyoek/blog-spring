package com.cos.blog.dto;

import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BoardResDTO {
    private int id;
    private User user;
    private List<Reply> reply;
    private String title;
    private String content;
}
