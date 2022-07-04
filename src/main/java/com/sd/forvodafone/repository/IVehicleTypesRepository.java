package com.sd.forvodafone.repository;


import com.sd.forvodafone.model.VehicleTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVehicleTypesRepository extends JpaRepository<VehicleTypes,Long> {

    List<VehicleTypes> findByName(String name);
}
