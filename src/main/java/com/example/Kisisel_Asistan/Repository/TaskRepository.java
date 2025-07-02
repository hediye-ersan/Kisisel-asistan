package com.example.Kisisel_Asistan.Repository;

import com.example.Kisisel_Asistan.Entity.Task;
import com.example.Kisisel_Asistan.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
