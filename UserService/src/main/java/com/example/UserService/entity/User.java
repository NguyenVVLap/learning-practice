package com.example.UserService.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor @NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email(message = "Email is not valid")
    private String email;

    @Size(min = 8, message = "Password >= 8 characters")
    private String password;

    @Column(name = "createdAt", columnDefinition = "DATETIME DEFAULT GETDATE()", insertable = false, updatable = false)
    private Date createdAt;
}
