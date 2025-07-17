package com.example.Kisisel_Asistan.Controller;

import com.example.Kisisel_Asistan.Entity.User;
import com.example.Kisisel_Asistan.Repository.UserRepository;
import com.example.Kisisel_Asistan.Services.AiRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiRecommendationController {


    private final AiRecommendationService aiRecommendationService;
    private final UserRepository userRepository;

    // Kullanıcıya günlük öneriler sunan endpoint
    @GetMapping("/recommendation")
    public ResponseEntity<String> getDailyRecommendation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Token'dan gelen kullanıcı adı

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        String recommendation = aiRecommendationService.generateDailyRecommendation(user.getId());
        return ResponseEntity.ok(recommendation);
    }
}
