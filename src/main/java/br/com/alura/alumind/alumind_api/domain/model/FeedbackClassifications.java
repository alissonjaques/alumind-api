package br.com.alura.alumind.alumind_api.domain.model;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.CreateFeedbackClassificationsDTO;
import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.FeedbackClassificationsDTO;
import br.com.alura.alumind.alumind_api.domain.enums.Sentiment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "feedback_classifications")
@Entity(name = "FeedbackClassifications")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class FeedbackClassifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "feedback", nullable = false)
    private String feedback;

    @Column(name = "response", nullable = false)
    private String response;

    @Enumerated(EnumType.STRING)
    @Column(name = "sentiment", nullable = false)
    private Sentiment sentiment;

    public FeedbackClassifications(CreateFeedbackClassificationsDTO data) {
        this.feedback = data.feedback();
    }

    public FeedbackClassifications(String feedback, FeedbackClassificationsDTO data) {
        this.feedback = feedback;
        this.sentiment = data.sentiment();
        this.response = data.response();
    }

    public FeedbackClassifications(Long id) {
        this.id = id;
    }
}
