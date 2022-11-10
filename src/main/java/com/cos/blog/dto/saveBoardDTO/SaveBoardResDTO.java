package com.cos.blog.dto.saveBoardDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaveBoardResDTO {
    private int id;
    private String title;
    private String content;
}
