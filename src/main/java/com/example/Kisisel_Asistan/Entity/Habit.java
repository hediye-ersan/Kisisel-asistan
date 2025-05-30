package com.example.Kisisel_Asistan.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "habits", schema = "kisisel_asistan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private HabitFrequency frequency;

    private int streakCount; // Günlük alışkanlık takibi için gün sayısı

    private LocalDate lastTrackedDate; // Son takip tarihi

    private LocalDateTime createdAt; // Oluşturulma tarihi

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // Kullanıcı ile ilişki

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
        if(this.streakCount<=0) {
            this.streakCount = 1;// İlk oluşturulduğunda 1 olarak başlatılır
        }
    }

}
