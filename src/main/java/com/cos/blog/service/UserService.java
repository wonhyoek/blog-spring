package com.cos.blog.service;

import com.cos.blog.dto.SaveUserReqDTO;
import com.cos.blog.dto.UpdateUserReqDTO;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public String 회원가입(SaveUserReqDTO reqDTO){
        String rawPassword = reqDTO.getPassword();
        String encPassword = encoder.encode(rawPassword);

        User user = User
                .builder()
                .username(reqDTO.getUsername())
                .password(encPassword)
                .email(reqDTO.getEmail())
                .roleType(RoleType.USER)
                .build();

        User userPS = userRepository.save(user);
        return userPS.getUsername();
    }

    @Transactional
    public String 회원수정(UpdateUserReqDTO reqDTO) {

        User userPS = userRepository.findById(reqDTO.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });
        User updatedUserPS = this.setNewDataInUser(userPS, reqDTO);

        return updatedUserPS.getEmail();

    }
    private User setNewDataInUser(User userPS, UpdateUserReqDTO reqDTO){
        if(userPS.getOauth() == null || userPS.getOauth().equals("")) {
            String rawPassword = reqDTO.getPassword();
            String encPassword = encoder.encode(rawPassword);
            userPS.setPassword(encPassword);
            userPS.setEmail(reqDTO.getEmail());
        }
        return userPS;
    }

    @Transactional(readOnly = true)
    public User 회원찾기(String username) {
        User user = userRepository.findByUsername(username).orElseGet(()->{
            return new User();
        });
        return user;
    }
}
