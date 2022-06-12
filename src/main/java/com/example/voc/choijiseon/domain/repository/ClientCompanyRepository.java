package com.example.voc.choijiseon.domain.repository;

import com.example.voc.choijiseon.domain.ClientCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ClientCompanyRepository extends JpaRepository<ClientCompany, Long> {

}
