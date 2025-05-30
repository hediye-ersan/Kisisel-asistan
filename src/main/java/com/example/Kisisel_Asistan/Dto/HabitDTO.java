package com.example.Kisisel_Asistan.Dto;

import com.example.Kisisel_Asistan.Entity.HabitFrequency;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class HabitDTO {
    private Long id;
    private String title;
    private String description;
    private HabitFrequency frequency;
    private int streakCount;
    private LocalDate lastTrackedDate;
    private LocalDateTime createdAt;
    private Long userId; // ilişkiyi doğrudan Entity yerine ID olarak taşıyoruz
}
