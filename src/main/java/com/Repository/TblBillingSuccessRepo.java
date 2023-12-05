package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.TblBillingSuccess;

@Repository
public interface TblBillingSuccessRepo extends JpaRepository<TblBillingSuccess, Integer>

{

}
