package com.sahan.netent.repository;

import com.sahan.netent.model.PreviousResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PreviousResultRepository extends JpaRepository<PreviousResult, Integer> {
    PreviousResult findByUniqueId(int id);

    PreviousResult findTopByOrderByUniqueIdDesc();
}
