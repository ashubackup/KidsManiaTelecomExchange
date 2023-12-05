package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Tbl_sms;

@Repository
public interface TblSmsRepo extends JpaRepository<Tbl_sms, Integer>
{

	public Tbl_sms findByType(String type);
}
