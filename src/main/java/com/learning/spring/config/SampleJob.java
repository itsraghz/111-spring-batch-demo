package com.learning.spring.config;

/*import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SampleJob {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Bean
	public Job firstJob() {
	    return new JobBuilder("firstJob", jobRepository) // ✅ Pass JobRepository explicitly
	            .start(firstStep()) // ✅ Use firstStep bean
	            .build();
	}

    @SuppressWarnings("removal")
    @Bean
    public Step firstStep() {
    	return new StepBuilder("firstStep", jobRepository)
    			.tasklet(firstTask(), transactionManager()) // ✅ Use StepBuilder explicitly
    			.build();
	}
	
    @Bean
    public PlatformTransactionManager transactionManager() { 
        return new ResourcelessTransactionManager(); // ✅ For non-database steps
    }
    
	@Bean
	public Tasklet firstTask() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("This is first tasklet step");
				return RepeatStatus.FINISHED;
			}
		};
	}
}*/
public class SampleJob {

}
