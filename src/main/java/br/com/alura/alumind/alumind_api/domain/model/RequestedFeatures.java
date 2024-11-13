package br.com.alura.alumind.alumind_api.domain.model;

import br.com.alura.alumind.alumind_api.application.DTOs.requested_features.CreateRequestedFeaturesDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "requested_features")
@Entity(name = "RequestedFeatures")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RequestedFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "reason", nullable = false)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "feedback_id", nullable = false)
    private Feedback feedback;

    public RequestedFeatures(CreateRequestedFeaturesDTO data) {
        this.code = data.code();
        this.reason = data.reason();
        this.feedback = data.feedback();
    }

    public RequestedFeatures(Long id) {
        this.id = id;
    }
}
