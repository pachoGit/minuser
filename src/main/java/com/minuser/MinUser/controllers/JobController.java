package com.minuser.MinUser.controllers;

import com.minuser.MinUser.entities.JobEntity;
import com.minuser.MinUser.models.ResponseEstandar;
import com.minuser.MinUser.services.JobService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JobController
 */
@RestController
@RequestMapping(path = "/job")
public class JobController {
  private JobService jobService;

  @Autowired
  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @GetMapping(path = "/show")
  public @ResponseBody ResponseEntity<ResponseEstandar<JobEntity>>
  show(@RequestParam Long id) {
    Optional<JobEntity> jobOptional = this.jobService.show(id);
    ResponseEstandar<JobEntity> response = null;
    if (jobOptional.isPresent()) {
      response = new ResponseEstandar<JobEntity>(
          HttpStatus.OK.value(), "success", "Job found", jobOptional.get());
      return ResponseEntity.ok(response);
    }
    response = new ResponseEstandar<JobEntity>(HttpStatus.NOT_FOUND.value(),
                                               "failed", "Job not found", null);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @GetMapping(path = "/get")
  public @ResponseBody ResponseEntity<ResponseEstandar<Iterable<JobEntity>>>
  get() {
    Iterable<JobEntity> jobs = this.jobService.get();
    var response = new ResponseEstandar<Iterable<JobEntity>>(
        HttpStatus.OK.value(), "success", "List of jobs", jobs);
    return ResponseEntity.ok(response);
  }
}
