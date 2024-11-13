package br.com.alura.alumind.alumind_api.domain.model;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback.CreateFeedbackDTO;
import br.com.alura.alumind.alumind_api.application.DTOs.feedback.FeedbackDTO;
import br.com.alura.alumind.alumind_api.domain.enums.Sentiment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "feedback")
@Entity(name = "Feedback")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "feedback", nullable = false)
    private String feedback;

    @Column(name = "custom_response", nullable = false)
    private String customResponse;

    @Enumerated(EnumType.STRING)
    @Column(name = "sentiment", nullable = false)
    private Sentiment sentiment;

    public Feedback(CreateFeedbackDTO data) {
        this.feedback = data.feedback();
    }

    public Feedback(String feedback, FeedbackDTO data) {
        this.feedback = feedback;
        this.sentiment = data.sentiment();
        this.customResponse = data.customResponse();
    }

    public Feedback(Long id) {
        this.id = id;
    }
}
