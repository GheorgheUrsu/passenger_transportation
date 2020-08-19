package com.passengertransportation.demo.repo;

import com.passengertransportation.demo.model.Buss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BussRepository  extends JpaRepository<Buss, Long> {
}
