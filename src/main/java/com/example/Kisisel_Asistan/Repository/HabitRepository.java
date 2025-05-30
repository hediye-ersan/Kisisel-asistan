package com.example.Kisisel_Asistan.Repository;

import com.example.Kisisel_Asistan.Entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUserId(Long userId);
}
