package com.example.calendarapp.dto;

import lombok.Getter;

@Getter
public class CreateCalendarRequest {
    private String title;
    private String content;
    private String name;
    private String password;

}
