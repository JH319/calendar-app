package com.example.calendarapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "calendars")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 500, nullable = false)
    private String content;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private String password;


    public Calendar(String title, String content, String name, String  password) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }

    public void update(String title,  String name, String password) {
        this.title = title;
        this.name = name;
        this.password = password;
    }
}
