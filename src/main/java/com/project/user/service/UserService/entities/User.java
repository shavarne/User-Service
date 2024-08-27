package com.project.user.service.UserService.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "micro_Users")
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "Name",length = 20)
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "ABOUT")
    private String about;

    @Transient
    private List<Rating> ratings;
}
