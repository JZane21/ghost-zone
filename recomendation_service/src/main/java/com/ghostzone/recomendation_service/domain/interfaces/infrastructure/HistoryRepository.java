package com.ghostzone.recomendation_service.domain.interfaces.infrastructure;

import com.ghostzone.history_service.domain.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

}
