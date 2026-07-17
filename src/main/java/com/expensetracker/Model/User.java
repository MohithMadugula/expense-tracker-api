package com.expensetracker.Model;

import com.expensetracker.Enums.AccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="username", unique=true, nullable=false,length=30)
    private String username;

    @Email
    @Column(name = "email", unique=true, nullable=false,length=100)
    private String email;

    @Column(name = "password", nullable=false,length=255)
    private String password;

    @Column(name = "phone_number", unique=true, length=15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name= "status", nullable=false)
    private AccountStatus status;

    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable=false)
    private LocalDateTime updatedAt;

}
