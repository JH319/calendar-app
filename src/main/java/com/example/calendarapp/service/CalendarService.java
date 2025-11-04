package com.example.calendarapp.service;

import com.example.calendarapp.dto.CreateCalendarRequest;
import com.example.calendarapp.dto.CreateCalendarResponse;
import com.example.calendarapp.entity.Calendar;
import com.example.calendarapp.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    @Transactional
    public CreateCalendarResponse save(CreateCalendarRequest request) {
        Calendar calendar = new Calendar(
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword()
        );
        Calendar savedCalendar = calendarRepository.save(calendar);
        return new CreateCalendarResponse(
                savedCalendar.getId(),
                savedCalendar.getTitle(),
                savedCalendar.getContent(),
                savedCalendar.getName(),
                savedCalendar.getCreatedAt(),
                savedCalendar.getModifiedAt()
        );
    }
}
