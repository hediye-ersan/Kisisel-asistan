package com.example.Kisisel_Asistan.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users", schema = "kisisel_asistan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Lombok anotasyonudur ve bir sınıf için Builder tasarım desenini otomatik olarak oluşturur. Bu, nesneleri daha okunabilir ve esnek bir şekilde oluşturmanıza olanak tanır.


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habit> habits;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }


}
