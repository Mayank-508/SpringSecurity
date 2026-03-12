package com.SecurityApplication.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostEntity    {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
   // @NotAudited  // changes to des will not be tracked
    private String description;

    @ManyToOne
    private User author;

    @PrePersist
    void beforeSave(){

    }
    @PreUpdate
    void beforeUpdate()
    {}

    @PreRemove
    void beforeDelete()
    {}



}