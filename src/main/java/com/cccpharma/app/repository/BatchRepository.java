package com.cccpharma.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cccpharma.app.model.Batch;

@Repository
public interface BatchRepository extends CrudRepository<Batch, Long> {

}
