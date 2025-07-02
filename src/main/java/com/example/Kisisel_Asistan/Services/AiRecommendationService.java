package com.example.Kisisel_Asistan.Services;

import com.example.Kisisel_Asistan.Entity.Habit;
import com.example.Kisisel_Asistan.Entity.Task;
import com.example.Kisisel_Asistan.Entity.TaskStatus;
import com.example.Kisisel_Asistan.Entity.User;
import com.example.Kisisel_Asistan.Repository.HabitRepository;
import com.example.Kisisel_Asistan.Repository.TaskRepository;
import com.example.Kisisel_Asistan.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiRecommendationService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final HabitRepository habitRepository;

    public String generateDailyRecommendation(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Bu id'ye ait kullanıcı bulunamadı: " + userId));

        //görevleri kontrol et
       List<Task> todayTasks = taskRepository.findByUser(user).stream().filter(task -> {if(task.getDueDate() == null) return false;
        return task.getDueDate().toLocalDate().isEqual(LocalDate.now());
        })
                .toList();
        long doneCount = todayTasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.DONE)
                .count();

        String taskMessage;
        if (todayTasks.isEmpty()) {
            taskMessage = "Bugün için henüz görev planlamadınız.";
        } else if (doneCount == 0) {
            taskMessage = "Görevlerin hazır ama henüz başlamamışsın.";
        } else if (doneCount < todayTasks.size()) {
            taskMessage = "Harika başladın! Görevlerinin bir kısmını tamamladın.";
        } else {
            taskMessage = "Bugünkü tüm görevleri tamamladın. Süpersin!";
        }

        // Alışkanlıkları kontrol et
        List<Habit> habits = habitRepository.findByUser(user);
        long activeStreaks = habits.stream().filter(h -> h.getStreakCount() >= 3).count();
        long ignoredHabits = habits.stream().filter(h -> h.getStreakCount() == 0).count();

        String habitMessage;
        if (habits.isEmpty()) {
            habitMessage = "Henüz hiç alışkanlık eklemedin. Bir tane oluşturmak ister misin?";
        } else if (activeStreaks > 0) {
            habitMessage = "Alışkanlıklarını başarıyla sürdürüyor gibisin. Böyle devam et! 🔥";
        } else if (ignoredHabits == habits.size()) {
            habitMessage = "Alışkanlıklarını uzun süredir takip etmiyorsun. Geri dönmek için güzel bir gün.";
        } else {
            habitMessage = "Bazı alışkanlıkların için başarı grafiğin düşmüş. Bugün biraz odaklanmayı deneyebilirsin.";
        }

        return taskMessage + " " + habitMessage;
    }
}
