package com.example.calendarapp.service;

import com.example.calendarapp.dto.*;
import com.example.calendarapp.entity.Calendar;
import com.example.calendarapp.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    /* ----- 속성 ----- */
    private final CalendarRepository calendarRepository;

    /* ----- 생성자 ----- */


    /* ----- 기능 ----- */
    // 일정 생성
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


    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<GetCalendarResponse> findSchedules(String name) {
        List<Calendar> calendars;
        // 작성자명이 있을 때
        if (name != null) {
            calendars = calendarRepository.findByNameOrderByModifiedAtDesc(name);
        }
        // 작성자명이 없을 때
        else {
            calendars = calendarRepository.findAllByOrderByModifiedAtDesc();
        }
        List<GetCalendarResponse> dtos = new ArrayList<>();
        for (Calendar calendar : calendars) {
            GetCalendarResponse dto = new GetCalendarResponse(
                    calendar.getId(),
                    calendar.getTitle(),
                    calendar.getContent(),
                    calendar.getName(),
                    calendar.getCreatedAt(),
                    calendar.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }


    // 선택 일정 조회
    @Transactional(readOnly = true)
    public GetCalendarResponse findOne(Long Id) {
        Calendar calendar = calendarRepository.findById(Id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new GetCalendarResponse(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContent(),
                calendar.getName(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }


    // 일정 수정
    @Transactional
    public UpdateCalendarResponse updateCalendar(Long Id, UpdateCalendarRequest request) {
        // 일정 찾기
        Calendar calendar = calendarRepository.findById(Id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        // 비밀번호 인증
        if(!calendar.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        calendar.update(request.getTitle(),request.getName(),request.getPassword());

        return new UpdateCalendarResponse(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContent(),
                calendar.getName(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }

    // 일정 삭제
    @Transactional
    public void delete(Long Id, DeleteCalendarRequest request) {
        // 일정 찾기
        Calendar calendar = calendarRepository.findById(Id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        // 비밀번호 인증
        if(!calendar.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        calendarRepository.delete(calendar);
    }
}
