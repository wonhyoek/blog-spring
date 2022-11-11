package com.cos.blog.controller;


import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.controller.api.BoardApiController;
import com.cos.blog.dto.BoardResDTO;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.dto.SaveBoardReqDTO;
import com.cos.blog.dto.SaveReplyReqDto;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.criteria.CriteriaBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class BoardControllerTest {
   @InjectMocks
    private BoardApiController boardApiController;

   @Mock
    private BoardService boardService;

   @Test
    public void save(){
       //given
       User user = User.builder()
               .id(1)
               .email("a@a.com")
               .password("asdf")
               .build();

       SaveBoardReqDTO reqDTO = new SaveBoardReqDTO();
       reqDTO.setTitle("good");
       reqDTO.setContent("good test");

       PrincipalDetail principalDetail = new PrincipalDetail(user);

       BoardResDTO resDTO = BoardResDTO
               .builder()
               .id(1)
               .title("good")
               .content("good test")
               .build();
       //stub
        doReturn(resDTO).when(boardService).글쓰기(reqDTO, user);

       //when
       ResponseDto<BoardResDTO> responseDto = boardApiController.save(reqDTO, principalDetail);

       //then
       System.out.println(responseDto.getData().getTitle());
       assertThat(responseDto.getData().getTitle()).isEqualTo("good");
   }

   @Test
    public void deleteBoard(){
       //given
        int id = 1;
       //stub
        doReturn(id).when(boardService).글삭제하기(id);
       //when
        ResponseDto<Integer> responseDto = boardApiController.deleteById(id);
       //then
       System.out.println(responseDto.getData());
       assertThat(responseDto.getData()).isEqualTo(id);
   }

   @Test
   public void update(){
       //given
        int id = 1;
        String title = "good";
        String content = "good testttttttttttttttttttt";

        SaveBoardReqDTO reqDTO = new SaveBoardReqDTO();
        reqDTO.setTitle(title);
        reqDTO.setContent(content);

        BoardResDTO resDTO = BoardResDTO
                .builder()
                .title(title)
                .content(content)
                .build();

       //stub
       doReturn(resDTO).when(boardService).글수정하기(id, reqDTO);

       //when
       ResponseDto<BoardResDTO> responseDto = boardApiController.update(id, reqDTO);

       //then
       System.out.println(responseDto.getData().getTitle());
       System.out.println(responseDto.getData().getContent());
       assertThat(responseDto.getData().getTitle()).isEqualTo(title);
       assertThat(responseDto.getData().getContent()).isEqualTo(content);
   }

   @Test
   public void replySave(){
       //given
       int userId = 1;
       int boardId = 1;
       String content = "good reply";

       SaveReplyReqDto reqDto = new SaveReplyReqDto();
       reqDto.setBoardId(boardId);
       reqDto.setUserId(userId);
       reqDto.setContent(content);

       int rowCount = 1;
       //stub
        doReturn(rowCount).when(boardService).댓글쓰기(reqDto);
       //when
        ResponseDto<Integer> responseDto = boardApiController.replySave(reqDto);
       //then
       System.out.println(responseDto.getData());
       assertThat(responseDto.getData()).isEqualTo(rowCount);
   }

   @Test
    public void replyDelete(){
       //given
        int replyId = 1;
       //stub
        doReturn(replyId).when(boardService).댓글삭제(replyId);
       //when
        ResponseDto<Integer> responseDto = boardApiController.replyDelete(replyId);
       //then
       System.out.println(responseDto.getData());
       assertThat(responseDto.getData()).isEqualTo(replyId);
   }

}

    //given

    //stub

    //when

    //then
