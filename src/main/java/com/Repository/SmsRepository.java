package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Entity.SaveRequest;

@Repository
public interface SmsRepository extends JpaRepository<SaveRequest, Integer>
{

	
}
