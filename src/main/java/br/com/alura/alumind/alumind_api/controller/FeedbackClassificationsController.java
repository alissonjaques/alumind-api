package br.com.alura.alumind.alumind_api.controller;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.CreateFeedbackClassificationsDTO;
import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.ResponseCreateFeedbackClassificationsDTO;
import br.com.alura.alumind.alumind_api.domain.interfaces.FeedbackClassificationsRepository;
import br.com.alura.alumind.alumind_api.domain.service.FeedbackClassificationsService;
import br.com.alura.alumind.alumind_api.infra.openai.OpenAIClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("feedbacks")
public class FeedbackClassificationsController {
    @Autowired
    private FeedbackClassificationsRepository feedbackClassificationsRepository;
    @Autowired
    FeedbackClassificationsService feedbackClassificationsService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateFeedbackClassificationsDTO createFeedbackClassificationsDTO, UriComponentsBuilder uriBuilder) {
        var feedbackClassifications = feedbackClassificationsService.create(createFeedbackClassificationsDTO);
        return ResponseEntity.ok().body(feedbackClassifications);
    }
}
