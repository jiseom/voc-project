package com.example.voc.choijiseon.domain.repository;

import com.example.voc.choijiseon.domain.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PenaltyRepository extends JpaRepository<Penalty,Long> {

}
