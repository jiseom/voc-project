package com.example.voc.choijiseon.domain.repository;

import com.example.voc.choijiseon.domain.CompensationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CompensationInfoRepository extends JpaRepository<CompensationInfo,Long> {

}
