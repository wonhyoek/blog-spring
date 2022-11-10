package com.cos.blog.service;

import com.cos.blog.dto.saveBoardDTO.SaveBoardReqDTO;
import com.cos.blog.dto.saveBoardDTO.SaveBoardResDTO;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @InjectMocks
    private BoardService boardService;
    @Mock
    private BoardRepository boardRepository;

    @Test
    public void createBoard(){
        //given
        User user = User
                .builder()
                .email("a@a.com")
                .password("ababab")
                .username("a")
                .roleType(RoleType.USER)
                .build();

        String title = "good";
        String content = "goodTest";
        Board board = Board
                .builder()
                .title(title)
                .content(content)
                .count(0)
                .user(user)
                .build();

        SaveBoardReqDTO reqDTO = new SaveBoardReqDTO();
        reqDTO.setTitle(title);
        reqDTO.setContent(content);

        //stub
        when(boardRepository.save(any())).thenReturn(board);

        //when
        SaveBoardResDTO resDTO = boardService.글쓰기(reqDTO,user);

        //then
        System.out.println(resDTO.getTitle());
        System.out.println(resDTO.getContent());
        assertThat(resDTO.getTitle()).isEqualTo(title);
        assertThat(resDTO.getContent()).isEqualTo(content);
    }
}
