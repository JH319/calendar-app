package com.example.calendarapp.controller;

import com.example.calendarapp.dto.CreateCalendarRequest;
import com.example.calendarapp.dto.CreateCalendarResponse;
import com.example.calendarapp.dto.GetCalendarResponse;
import com.example.calendarapp.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    /* ----- 속성 ----- */
    private final CalendarService calendarService;

    /* ----- 생성자 ----- */


    /* ----- 기능 ----- */
    // 일정 생성
    @PostMapping("/calendars")
    public ResponseEntity<CreateCalendarResponse> createCalendarApi(@RequestBody CreateCalendarRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.save(request));
    }

    // 전체 일정 조회
    @GetMapping("/calendars")
    public ResponseEntity<List<GetCalendarResponse>> getCalendarsApi(@RequestParam(value = "name", required = false) String name) {
        List<GetCalendarResponse> result = calendarService.findSchedules(name);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 선택 일정 조회
    @GetMapping("/calendars/{Id}")
    public ResponseEntity<GetCalendarResponse> getCalendarApi(@PathVariable Long Id) {
        return ResponseEntity.status(HttpStatus.OK).body(calendarService.findOne(Id));
    }



}
