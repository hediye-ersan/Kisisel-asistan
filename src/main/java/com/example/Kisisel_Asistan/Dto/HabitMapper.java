package com.example.Kisisel_Asistan.Dto;

import com.example.Kisisel_Asistan.Entity.Habit;
import com.example.Kisisel_Asistan.Entity.User;

public class HabitMapper {
    public static HabitDTO toDTO(Habit habit){
        return HabitDTO.builder()
                .id(habit.getId())
                .title(habit.getTitle())
                .description(habit.getDescription())
                .frequency(habit.getFrequency())
                .badge(calculateBadge(habit.getStreakCount()))
                .streakCount(habit.getStreakCount())
                .lastTrackedDate(habit.getLastTrackedDate())
                .createdAt(habit.getCreatedAt())
                .userId(habit.getUser() != null ? habit.getUser().getId() : null)
                .build();
    }

    public static Habit toEntity(HabitDTO dto, User user) {
        return Habit.builder()
                .id(dto.getId()) // Update işlemi için gerekebilir
                .title(dto.getTitle())
                .description(dto.getDescription())
                .frequency(dto.getFrequency())
                .streakCount(dto.getStreakCount())
                .lastTrackedDate(dto.getLastTrackedDate())
                .user(user)
                .build();
    }

    private static String calculateBadge(int streak) {
        if (streak >= 14) return "Altın";
        if (streak >= 7) return "Gümüş";
        if (streak >= 3) return "Bronz";
        return "Başlangıç";
    }

}

//DTO sınıfı sadece ihtiyaç duyduğumuz verileri taşır — entity’nin aynısı değil.
//Mapper, DTO ↔ Entity dönüşümünü yönetir, servis katmanını sadeleştirir.
//userId alanını DTO’da Long olarak tutmak, frontend'in sadece ID göndermesi için pratiklik sağlar.
