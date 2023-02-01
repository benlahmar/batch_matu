package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableBatchProcessing
public class BatchdbtocsvApplication implements CommandLineRunner{

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	Job generateCSVReportCard;
	
	public static void main(String[] args) {
		SpringApplication.run(BatchdbtocsvApplication.class, args);
	}

//	@Scheduled(cron = "0 */1 * * * ?")
//	public void perform() throws Exception
//	{
//		JobParameters params = new JobParametersBuilder()
//			.addString("JobID",
//                String.valueOf(System.currentTimeMillis()))
//			.toJobParameters();
//		jobLauncher.run(generateCSVReportCard, params);
//	}

	@Override
	public void run(String... args) throws Exception {
		JobParameters params = new JobParametersBuilder()
				.addString("JobID",
	                String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
			jobLauncher.run(generateCSVReportCard, params);
		
	}
}
