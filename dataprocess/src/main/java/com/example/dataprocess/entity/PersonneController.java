package com.example.dataprocess.entity;

import com.example.dataprocess.util.StorageService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PersonneController {
    private Job job;
    private JobLauncher jobLauncher;
    private StorageService storageService;
    @Autowired
    public PersonneController(Job job,JobLauncher jobLauncher,StorageService storageService){
        this.job = job;
        this.jobLauncher = jobLauncher;
        this.storageService = storageService;
    }
    @GetMapping("/loade")
    public String aecute() throws Exception {
        return "execute";
    }
    @PostMapping("/load")
    public ResponseEntity<String> upload(@RequestBody MultipartFile file) throws IOException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        storageService.uploadFile(file);
        jobLauncher.run(job, new JobParameters());
        return ResponseEntity.ok().body(file.getOriginalFilename());
    }
}
