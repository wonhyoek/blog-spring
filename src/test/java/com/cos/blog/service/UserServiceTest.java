package com.cos.blog.service;

import com.cos.blog.dto.SaveUserReqDTO;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder encoder;

    @Test
    public void createUser(){
        //given
        String username = "콩순이";
        String email = "a@a.com";
        String password = "asdf";
        String encPassword = "12341234";

        SaveUserReqDTO reqDTO = new SaveUserReqDTO();
        reqDTO.setEmail(email);
        reqDTO.setPassword(password);
        reqDTO.setUsername(username);

        User user = User
                .builder()
                .password(encPassword)
                .email(email)
                .username(username)
                .roleType(RoleType.USER)
                .build();

        //stub
        doReturn(user).when(userRepository).save(user);
        doReturn(encPassword).when(encoder).encode(password);

        //when
        String resUsername = userService.회원가입(reqDTO);

        //then
        System.out.println(resUsername);
        assertThat(resUsername).isEqualTo(username);
    }

    
}
    //given

    //stub

    //when

    //then
