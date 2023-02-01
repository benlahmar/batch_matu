package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
		
//	@Autowired
//	MyCustomReader myCustomReader;
//	
//	@Autowired
//	MyCustomWriter myCustomWriter;
//	StepBuilderFactory sb;
//	@Bean
//	public Job createJob() {
//		return jobBuilderFactory.get("MyJob")
//				.incrementer(new RunIdIncrementer())
//				.flow(createStep()).end().build();
//	}

//	@Bean
//	public Step createStep() {
//		return sb.get("MyStep").chunk(1)
//				
//				.reader(myCustomReader)
//				.writer(myCustomWriter)
//				.build();
//	}
}