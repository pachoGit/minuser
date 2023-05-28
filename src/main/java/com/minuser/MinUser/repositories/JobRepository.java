package com.minuser.MinUser.repositories;

import com.minuser.MinUser.entities.JobEntity;

import org.springframework.data.repository.CrudRepository;

/**
 * JobRepository
 */
public interface JobRepository extends CrudRepository<JobEntity, Long>
{

}
