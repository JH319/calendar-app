package com.example.calendarapp.repository;

import com.example.calendarapp.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findByNameOrderByModifiedAtDesc(String name);

    List<Calendar> findAllByOrderByModifiedAtDesc();
}
