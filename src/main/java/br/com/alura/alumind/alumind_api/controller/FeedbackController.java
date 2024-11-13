package br.com.alura.alumind.alumind_api.controller;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback.CreateFeedbackDTO;
import br.com.alura.alumind.alumind_api.domain.interfaces.FeedbackRepository;
import br.com.alura.alumind.alumind_api.domain.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    FeedbackService feedbackService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateFeedbackDTO createFeedbackDTO, UriComponentsBuilder uriBuilder) {
        var feedback = feedbackService.create(createFeedbackDTO);
        return ResponseEntity.ok().body(feedback);
    }
}
