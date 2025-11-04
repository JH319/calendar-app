package com.example.calendarapp.controller;

import com.example.calendarapp.dto.CreateCalendarRequest;
import com.example.calendarapp.dto.CreateCalendarResponse;
import com.example.calendarapp.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    // 일정 생성
    @PostMapping("/calendars")
    public ResponseEntity<CreateCalendarResponse> createcalendar(@RequestBody CreateCalendarRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.save(request));
    }
}
