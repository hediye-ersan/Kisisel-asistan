package com.example.Kisisel_Asistan.Controller;

import com.example.Kisisel_Asistan.Dto.HabitDTO;
import com.example.Kisisel_Asistan.Services.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor

public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public ResponseEntity<HabitDTO> createHabit(@RequestBody HabitDTO habitDTO) {
        HabitDTO created = habitService.createHabit(habitDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HabitDTO>> getHabitsByUser(@PathVariable Long userId) {
        List<HabitDTO> habits = habitService.getHabitsByUserId(userId);
        return ResponseEntity.ok(habits);
    }

    @GetMapping("/{habitId}")
    public ResponseEntity<HabitDTO> getHabit(@PathVariable Long habitId) {
        HabitDTO habit = habitService.getHabitById(habitId);
        return ResponseEntity.ok(habit);
    }

    @PutMapping("/{habitId}")
    public ResponseEntity<HabitDTO> updateHabit(@PathVariable Long habitId,
                                                @RequestBody HabitDTO habitDTO) {
        HabitDTO updated = habitService.updateHabit(habitId, habitDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long habitId) {
        habitService.deleteHabit(habitId);
        return ResponseEntity.noContent().build();
    }


}
