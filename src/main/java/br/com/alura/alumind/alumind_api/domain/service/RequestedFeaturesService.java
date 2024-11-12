package br.com.alura.alumind.alumind_api.domain.service;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.CreateRequestedFeaturesDTO;
import br.com.alura.alumind.alumind_api.domain.interfaces.RequestedFeaturesRepository;
import br.com.alura.alumind.alumind_api.domain.model.RequestedFeatures;
import br.com.alura.alumind.alumind_api.domain.validations.feedback_classifications.create.ICreateFeedbackClassficationsValidation;
import br.com.alura.alumind.alumind_api.domain.validations.requested_features.create.IRequestFeaturesValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestedFeaturesService {
    @Autowired
    private RequestedFeaturesRepository requestedFeaturesRepository;
    @Autowired
    List<IRequestFeaturesValidation> createRequestedFeaturesValidation;

    public void create(CreateRequestedFeaturesDTO createRequestedFeaturesDTO) {
        createRequestedFeaturesValidation.forEach(v -> v.validate(createRequestedFeaturesDTO));
        var newRequestedFeatures = new RequestedFeatures(createRequestedFeaturesDTO);
        requestedFeaturesRepository.save(newRequestedFeatures);
    }
}
