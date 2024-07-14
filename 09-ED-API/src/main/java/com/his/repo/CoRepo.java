package com.his.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.COEntity;

public interface CoRepo extends JpaRepository<COEntity, Integer> {

}
