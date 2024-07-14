package com.his.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.KidsData;


public interface KidRepo extends JpaRepository<KidsData, Serializable> {

	List<KidsData> findByAppNumber(Integer appNumber);
}
