package com.cos.blog.dto;

import com.cos.blog.model.Board;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
public class BoardListWrapperDTO {
    private Page<Board> items;
}
