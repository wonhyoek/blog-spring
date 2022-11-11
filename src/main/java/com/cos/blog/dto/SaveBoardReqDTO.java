package com.cos.blog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveBoardReqDTO {
    @Size(max = 20)
    private String title;
    @Size(max = 20) // 테스트를 위해 작은 값을 넣어둠
    private String content;
}
