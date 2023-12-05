package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.ServiceInfo;

@Repository
public interface ServiceInfoRepo extends JpaRepository<ServiceInfo, Integer>
{	
	public ServiceInfo findByServicename(String servicename);
}
