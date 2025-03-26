package com.learning.spring.batch.lcwd;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfig {
	
	private static final int CHUNK_SIZE = 5;
	
	private static final String JOB_NAME = "lcwd-csvImportJob";
	
	@Autowired 
	private JobNotificationListener listener;
	
	@Bean
	public ProductItemProcessor processor() {
		return new ProductItemProcessor();
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager, Step step) {
		log.info("Starting job: {}", JOB_NAME);
		return new JobBuilder(JOB_NAME, jobRepository)
				.listener(listener)
				.start(step)
				.build();
	}
	
	@Bean
	public Step step(JobRepository jobRepository, 
			PlatformTransactionManager transactionManager, 
			ItemReader<Product> reader,
			ItemProcessor<Product, Product> processor,
			ItemWriter<Product> writer) {
		final String STEP_NAME = "lcwd-csvFileImportStep";
		log.info("Starting Step: {}", STEP_NAME);
		return new StepBuilder(STEP_NAME, jobRepository)
				.<Product,Product>chunk(CHUNK_SIZE, transactionManager)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	@Bean
	public FlatFileItemReader<Product> reader() {
		final String READER = "lcwd-flatFileItemReader";
		log.info("Starting READER: {}", READER);
		return new FlatFileItemReaderBuilder<Product>()
				.name("lcwd-flatFileItemReader")
				.resource(new ClassPathResource("data.csv"))
				.delimited()
				.delimiter(",")
				.names("productId", "title", "description", "price", "discount")
				.linesToSkip(1)
				.targetType(Product.class)
				.build();
	}
	
	@Bean
	public ItemWriter<Product> writer(DataSource dataSource) {
		log.info("Writer is invoked");
		return new JdbcBatchItemWriterBuilder<Product>()
				.sql("INSERT INTO product (productId, title, description, price, discount, discounted_price) VALUES (:productId, :title, :description, :price, :discount, :discountedPrice)")
				.dataSource(dataSource)
				.beanMapped()
				.build();
	}
}
