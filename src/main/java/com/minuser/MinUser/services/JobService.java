package com.minuser.MinUser.services;

import java.util.Optional;

import com.minuser.MinUser.entities.JobEntity;
import com.minuser.MinUser.repositories.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * JobService
 */
@Service
public class JobService
{
    private JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository)
    {
        this.jobRepository = jobRepository;
    }

    public Optional<JobEntity> show(Long id)
    {
        return this.jobRepository.findById(id);
    }

    public Iterable<JobEntity> get()
    {
        return this.jobRepository.findAll();
    }
}
