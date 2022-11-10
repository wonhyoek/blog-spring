package com.cos.blog.dto.saveBoardDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveBoardReqDTO {
    private String title;
    private String content;
}
