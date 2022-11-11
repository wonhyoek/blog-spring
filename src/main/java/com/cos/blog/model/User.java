package com.cos.blog.model;

import com.cos.blog.dto.UpdateUserReqDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String oauth;

    @CreationTimestamp
    private Timestamp createDate;

    public User setNewDataInUser(BCryptPasswordEncoder encoder, UpdateUserReqDTO reqDTO){
        if(this.getOauth() == null || this.getOauth().equals("")) {
            String rawPassword = reqDTO.getPassword();
            String encPassword = encoder.encode(rawPassword);
            this.setPassword(encPassword);
            this.setEmail(reqDTO.getEmail());
        }
        return this;
    }
}
