package com.learning.spring.batch.lcwd;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobNotificationListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {		
		//JobExecutionListener.super.beforeJob(jobExecution);
		log.info("Job Execution started at: {}", jobExecution.getStartTime());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		//JobExecutionListener.super.afterJob(jobExecution);
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
	        log.info("Job Execution completed successfully at: {}", jobExecution.getEndTime());
	    } else {
	        log.error("Job Execution failed with status: {}", jobExecution.getStatus());
	    }		
	}

}
