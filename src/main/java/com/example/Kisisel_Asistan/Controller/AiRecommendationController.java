package com.example.Kisisel_Asistan.Controller;

import com.example.Kisisel_Asistan.Services.AiRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiRecommendationController {
    private final AiRecommendationService aiRecommendationService;

    @GetMapping("/recommendation/{userId}")
    public ResponseEntity<String> getDailyRecommendation(@PathVariable Long userId) {
        String recommendation = aiRecommendationService.generateDailyRecommendation(userId);
        return ResponseEntity.ok(recommendation);
    }
}
