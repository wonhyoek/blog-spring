package com.cos.blog.controller;

import com.cos.blog.controller.api.UserApiController;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.dto.SaveUserReqDTO;
import com.cos.blog.dto.UpdateUserReqDTO;
import com.cos.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserApiControllerTest {
    @InjectMocks
    private UserApiController userApiController;
    @Mock
    private UserService userService;
    @Mock
    private AuthenticationManager authenticationManager;

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

    @Test
    public void update(){
        //given
        int id = 1;
        String username = "username";
        String email = "email";
        String password = "password";

        UpdateUserReqDTO reqDTO = new UpdateUserReqDTO();
        reqDTO.setId(id);
        reqDTO.setEmail(email);
        reqDTO.setPassword(password);
        reqDTO.setUsername(username);

        //stub
        doReturn(email).when(userService).회원수정(reqDTO);
        doReturn(null).when(authenticationManager).authenticate(any());

        //when
        ResponseDto<String> responseDto = userApiController.update(reqDTO);

        //then
        System.out.println(responseDto.getData());
        assertThat(responseDto.getData()).isEqualTo(email);
    }
}
    //given

    //stub

    //when

    //then
