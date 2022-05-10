package com.capstone.newsletterapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", columnDefinition = "VARCHAR(255)")
    private UUID userId;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "is_authenticated", columnDefinition = "boolean default false")
    private Boolean isAuthenticated;

    @OneToMany(mappedBy = "user")
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category){
        this.categories.add(category);
        category.setUser(this);
    }
}

