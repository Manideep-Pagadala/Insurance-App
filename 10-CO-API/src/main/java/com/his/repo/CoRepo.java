package com.his.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.his.entity.COEntity;

public interface CoRepo extends JpaRepository<COEntity, Integer> {

	@Query(" From COEntity where appNumber=:appNumber and coStatus=:coStatus")
	public List<COEntity> fetchNotices(Integer appNumber, String coStatus);
	
	
	
	

}
