package com.cccpharma.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cccpharma.app.model.Batch;

@Repository
public interface BatchRepository extends CrudRepository<Batch, Long> {
	@Query("SELECT b FROM Batch b WHERE b.expirationDate <= :expiringDate AND b.expirationDate >= :todayDate")
	public List<Batch> findByNextToExpireDate(@Param("expiringDate") Date expiring, @Param("todayDate") Date today);
}
