package com.cos.blog.controller;

import com.cos.blog.controller.api.UserApiController;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.dto.SaveUserReqDTO;
import com.cos.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserApiControllerTest {
    @InjectMocks
    private UserApiController userApiController;
    @Mock
    private UserService userService;

    @Test
    public void save(){
        //given
        String username = "username";
        String email = "email";
        String password = "password";

        SaveUserReqDTO reqDTO = new SaveUserReqDTO();
        reqDTO.setEmail(email);
        reqDTO.setPassword(password);
        reqDTO.setUsername(username);

        //stub
        doReturn(username).when(userService).회원가입(reqDTO);

        //when
        ResponseDto<String> responseDto = userApiController.save(reqDTO);

        //then
        System.out.println(responseDto.getData());
        assertThat(responseDto.getData()).isEqualTo(username);
    }
}
    //given

    //stub

    //when

    //then
