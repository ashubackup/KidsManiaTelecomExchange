package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.RequestResponseLogs;

@Repository
public interface RequestResponseRepo extends JpaRepository<RequestResponseLogs, Integer>
{

	
}
