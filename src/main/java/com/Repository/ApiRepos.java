package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Api;

@Repository
public interface ApiRepos extends JpaRepository<Api, Integer>
{

	public Api findByStatusAndType(String status,String type);
}
