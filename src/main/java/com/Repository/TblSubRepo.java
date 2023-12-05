package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Entity.Tbl_Subscription;

@Repository
public interface TblSubRepo extends JpaRepository<Tbl_Subscription, Integer>
{

	//find the count of user in tbl_subscription
	@Query(value="select * from tbl_subscription where ani=:ani",nativeQuery=true)
	public List<Tbl_Subscription> checkSub(@Param("ani") String ani);
	
//	@Query(value="select * from tbl_subscription where ani=:ani",nativeQuery=true)
//	public List<Tbl_Subscription> checkSubNextDate(@Param("ani") String ani);
}
