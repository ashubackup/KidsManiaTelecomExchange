package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.WapRequestEntity;

@Repository

public interface WapRequestRepo extends JpaRepository<WapRequestEntity, Integer>
{

	
}
