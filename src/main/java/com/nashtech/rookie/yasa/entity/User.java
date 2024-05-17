package com.nashtech.rookie.yasa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
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
    private String role;
    @Column(nullable = false, length = 1024)
    private String salt;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Cart cart;
}
