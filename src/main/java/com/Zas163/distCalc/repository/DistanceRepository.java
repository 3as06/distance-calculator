package com.Zas163.distCalc.repository;

import com.Zas163.distCalc.entity.Distance;
import com.Zas163.distCalc.entity.DistanceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, DistanceKey> {
}
