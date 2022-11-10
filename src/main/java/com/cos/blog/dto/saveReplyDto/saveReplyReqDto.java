package com.cos.blog.dto.saveReplyDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class saveReplyReqDto {
    private int userId;
    private int boardId;
    private String content;
}