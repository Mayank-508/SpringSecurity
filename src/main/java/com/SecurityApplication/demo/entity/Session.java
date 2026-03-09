package com.SecurityApplication.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    private String refreshToken;


    private LocalDateTime lastUsedAt;

    @ManyToOne
    private User user;







}
