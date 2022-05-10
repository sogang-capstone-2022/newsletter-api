package com.capstone.newsletterapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Category {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "category_id", columnDefinition = "VARCHAR(255)")
    private UUID categoryId;

    @Email
    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public void setUser(User user){
        this.user = user;
    }
}
