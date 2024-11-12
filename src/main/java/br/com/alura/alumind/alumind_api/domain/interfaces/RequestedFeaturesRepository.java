package br.com.alura.alumind.alumind_api.domain.interfaces;

import br.com.alura.alumind.alumind_api.domain.model.RequestedFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestedFeaturesRepository extends JpaRepository<RequestedFeatures, Long> { }
