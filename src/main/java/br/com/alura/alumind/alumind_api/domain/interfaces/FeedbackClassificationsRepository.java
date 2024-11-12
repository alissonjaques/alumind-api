package br.com.alura.alumind.alumind_api.domain.interfaces;

import br.com.alura.alumind.alumind_api.domain.model.FeedbackClassifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackClassificationsRepository extends JpaRepository<FeedbackClassifications, Long> { }
