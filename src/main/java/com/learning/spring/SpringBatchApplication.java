package com.learning.spring;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.learning.spring")
public class SpringBatchApplication implements CommandLineRunner {

	@Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;
    
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        System.out.println("Starting the Batch Job...");

        // Create job parameters with a timestamp to prevent duplicate job instances
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(job, jobParameters);

        System.out.println("Batch Job has been invoked!");
    }

}
