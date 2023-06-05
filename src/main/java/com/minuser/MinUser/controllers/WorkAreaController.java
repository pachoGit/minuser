package com.minuser.MinUser.controllers;

import java.util.Optional;

import com.minuser.MinUser.entities.WorkAreaEntity;
import com.minuser.MinUser.models.ResponseEstandar;
import com.minuser.MinUser.services.WorkAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * WorkAreaController
 */
@RestController
@RequestMapping(path = "/workarea")
public class WorkAreaController
{
    private WorkAreaService workAreaService;

    @Autowired
    public WorkAreaController(WorkAreaService workAreaService)
    {
        this.workAreaService = workAreaService;
    }

    @GetMapping(path = "/show")
    public @ResponseBody ResponseEntity<ResponseEstandar<WorkAreaEntity>> show(@RequestParam Long id)
    {
        Optional<WorkAreaEntity> workAreaEntity = this.workAreaService.show(id);
        ResponseEstandar<WorkAreaEntity> response = null;
        if (workAreaEntity.isPresent()) {
            response = new ResponseEstandar<WorkAreaEntity>(HttpStatus.OK.value(), "success", "Work area found", workAreaEntity.get());
            return ResponseEntity.ok(response);
        }
        response = new ResponseEstandar<WorkAreaEntity>(HttpStatus.NOT_FOUND.value(), "failed", "Work area not found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping(path = "/get")
    public @ResponseBody ResponseEntity<ResponseEstandar<Iterable<WorkAreaEntity>>> get()
    {
        Iterable<WorkAreaEntity> workAreas = this.workAreaService.get();
        var response = new ResponseEstandar<Iterable<WorkAreaEntity>>(HttpStatus.OK.value(), "success", "List of work areas", workAreas);
        return ResponseEntity.ok(response);
    }
}
