package com.example.Kisisel_Asistan.Services;

import com.example.Kisisel_Asistan.Dto.HabitDTO;
import com.example.Kisisel_Asistan.Dto.HabitMapper;
import com.example.Kisisel_Asistan.Entity.Habit;
import com.example.Kisisel_Asistan.Entity.User;
import com.example.Kisisel_Asistan.Repository.HabitRepository;
import com.example.Kisisel_Asistan.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class HabitService {
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitDTO createHabit(HabitDTO habitDTO){
        User user = userRepository.findById(habitDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Habit habit = HabitMapper.toEntity(habitDTO, user);
        Habit savedHabit = habitRepository.save(habit);

        return HabitMapper.toDTO(savedHabit);
    }

    public List<HabitDTO> getHabitsByUserId(Long userId) {
        return habitRepository.findByUserId(userId).stream()
                .map(HabitMapper::toDTO)
                .collect(Collectors.toList());
    }

    public HabitDTO getHabitById(Long habitId){
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new EntityNotFoundException("Habit not found"));

        return HabitMapper.toDTO(habit);
    }

    public HabitDTO updateHabit(Long habitId, HabitDTO habitDTO) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new EntityNotFoundException("Habit not found"));

        habit.setTitle(habitDTO.getTitle());
        habit.setDescription(habitDTO.getDescription());
        habit.setFrequency(habitDTO.getFrequency());
        habit.setLastTrackedDate(habitDTO.getLastTrackedDate());
        habit.setStreakCount(habitDTO.getStreakCount());

        Habit updated = habitRepository.save(habit);
        return HabitMapper.toDTO(updated);
    }

    public void deleteHabit(Long habitId) {
        if (!habitRepository.existsById(habitId)) {
            throw new EntityNotFoundException("Habit not found");
        }
        habitRepository.deleteById(habitId);
    }

    public HabitDTO completeHabitToday(Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Alışkanlık bulunamadı."));

        LocalDate today = LocalDate.now();
        LocalDate lastDate = habit.getLastTrackedDate();

        if (lastDate != null && lastDate.isEqual(today)) {
            // Bugün zaten işaretlenmiş
            return HabitMapper.toDTO(habit);
        }

        if (lastDate != null && lastDate.plusDays(1).isEqual(today)) {
            // Dün yapılmış → streak devam ediyor
            habit.setStreakCount(habit.getStreakCount() + 1);
        } else {
            // Ya ilk defa yapılıyor ya da arada gün atlanmış → sıfırla
            habit.setStreakCount(1);
        }

        habit.setLastTrackedDate(today);
        Habit updated = habitRepository.save(habit);
        return HabitMapper.toDTO(updated);
    }


}
