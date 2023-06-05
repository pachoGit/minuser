package com.minuser.MinUser.services;

import java.util.Optional;

import com.minuser.MinUser.entities.WorkAreaEntity;
import com.minuser.MinUser.repositories.WorkAreaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkAreaService
{
    private WorkAreaRepository workAreaRepository;

    @Autowired
    public WorkAreaService(WorkAreaRepository workAreaRepository)
    {
        this.workAreaRepository = workAreaRepository;
    }

    public Optional<WorkAreaEntity> show(Long id)
    {
        return this.workAreaRepository.findById(id);
    }

    public Iterable<WorkAreaEntity> get()
    {
        return this.workAreaRepository.findAll();
    }
}
