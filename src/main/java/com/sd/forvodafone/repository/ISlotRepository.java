package com.sd.forvodafone.repository;

import com.sd.forvodafone.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISlotRepository extends JpaRepository<Slot,Long> {
    List<Slot> findByStatus(int status);
}
