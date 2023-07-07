package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.dto.SaveUserReqDTO;
import com.cos.blog.dto.UpdateUserReqDTO;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/join-proc")
    public ResponseDto<String> save(@RequestBody SaveUserReqDTO reqDTO){
        String username = userService.registUser(reqDTO);
        return new ResponseDto<String>(HttpStatus.OK.value(), username);
    }

    @PutMapping("/user")
    public ResponseDto<String> update(@RequestBody UpdateUserReqDTO reqDTO) { // key=value, x-www-form-urlencoded
        String updatedEmail = userService.modifyUser(reqDTO);

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                reqDTO.getUsername(),
                                reqDTO.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<String>(HttpStatus.OK.value(), updatedEmail);
    }
}
