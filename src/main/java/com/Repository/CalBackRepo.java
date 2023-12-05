package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.CallBackEntity;

@Repository
public interface CalBackRepo extends JpaRepository<CallBackEntity, Integer>
{

	public List<CallBackEntity> findByStatus(String status);
}
