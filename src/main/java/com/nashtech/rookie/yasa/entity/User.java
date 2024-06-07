package com.nashtech.rookie.yasa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User extends Audit {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false,length = 1024)
    private String secret;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false, length = 1024)
    private String salt;
    @OneToOne(mappedBy = "user")
    private Cart cart;
}
