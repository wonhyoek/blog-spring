package com.cos.blog.service;

import com.cos.blog.dto.BoardListWrapperDTO;
import com.cos.blog.dto.saveBoardDTO.SaveBoardReqDTO;
import com.cos.blog.dto.BoardResDTO;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        BoardResDTO resDTO = boardService.글쓰기(reqDTO,user);

        //then
        System.out.println(resDTO.getTitle());
        System.out.println(resDTO.getContent());
        assertThat(resDTO.getTitle()).isEqualTo(title);
        assertThat(resDTO.getContent()).isEqualTo(content);
    }

    @Test
    public void getBoardLists(){
        //given
        String title = "good";
        String content = "good test";
        Board board1 = Board.builder().title(title).content(content).build();
        Board board2 = Board.builder().title("title").content("content").build();

        List<Board> boardList= new ArrayList<>();
        boardList.add(board1);
        boardList.add(board2);

        Pageable pageable =  PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<Board> boardsPage = new PageImpl<>(boardList);


        //stub
        when(boardRepository.findAll(pageable)).thenReturn(boardsPage);

        //when
        BoardListWrapperDTO resDTO = boardService.글목록(pageable);
        boardList = resDTO.getItems().get().collect(Collectors.toList());
        //then
        assertThat(boardList.get(1).getTitle()).isEqualTo("title");
        assertThat(boardList.get(1).getContent()).isEqualTo("content");
    }

    @Test
    public void getOneBoard(){
        //given
        String title = "good";
        String content = "good test";
        int id = 1;
        Board board = Board.builder().id(id).title(title).content(content).build();
        Optional<Board> boardOP = Optional.of(board);

        //stub
        doReturn(boardOP).when(boardRepository).findById(id);
        //doThrow(new Exception) 못 찾는 상황에서 사용

        //when
        BoardResDTO resDTO = boardService.글상세보기(id);

        //then
        System.out.println(title);
        System.out.println(content);
        assertThat(resDTO.getId()).isEqualTo(id);
        assertThat(resDTO.getTitle()).isEqualTo(title);
        assertThat(resDTO.getContent()).isEqualTo(content);
    }
}
