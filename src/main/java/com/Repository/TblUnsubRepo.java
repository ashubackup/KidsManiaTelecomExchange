package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Entity.TblUnsubscription;

@Repository
public interface TblUnsubRepo extends JpaRepository<TblUnsubscription, Integer>
{

	@Query(value="Insert into tbl_unsubscription select * from tbl_subscription where ani=:ani ",nativeQuery=true)
	String addInUnSub(@Param("ani")String ani);
	
}
