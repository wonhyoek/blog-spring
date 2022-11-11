package com.cos.blog.controller;


import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.controller.api.BoardApiController;
import com.cos.blog.dto.BoardResDTO;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.dto.SaveBoardReqDTO;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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

}

    //given

    //stub

    //when

    //then
