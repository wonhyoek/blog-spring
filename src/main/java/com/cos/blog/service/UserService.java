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
    public String registUser(SaveUserReqDTO reqDTO){
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
    public String modifyUser(UpdateUserReqDTO reqDTO) {

        User userPS = userRepository.findById(reqDTO.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });
        User updatedUserPS = userPS.setNewDataInUser(encoder, reqDTO);

        return updatedUserPS.getEmail();

    }


    @Transactional(readOnly = true)
    public String findUserByUsername(String username) {
        User userPS = userRepository.findByUsername(username).orElseGet(()->{
            return new User();
        });
        return userPS.getUsername();
    }
}
