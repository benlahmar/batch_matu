package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.ProduitListener;
import com.example.demo.entities.Produit;
import com.example.demo.repo.IProduit;


@Configuration
@EnableBatchProcessing
public class BatchConfig01 {

    //Reader class Object
    @Bean
    public FlatFileItemReader<Produit> reader() {

       FlatFileItemReader<Produit> reader= new FlatFileItemReader<>();
       reader.setResource(new ClassPathResource("/Produits.csv"));
       // Reader. setResource(new FileSystemResource("D:/mydata/Produits.csv"));
       // reader.setResource(new UrlResource("https://xyz.com/files/Produits.csv"));
       // reader.setLinesToSkip(1);
       
       reader.setLineMapper(new DefaultLineMapper<Produit>() {{
           setLineTokenizer(new DelimitedLineTokenizer() {{
              setDelimiter(DELIMITER_COMMA);
              setNames("desg","prix","discount");
           }});

           setFieldSetMapper(new BeanWrapperFieldSetMapper<Produit>() {{
              setTargetType(Produit.class);
           }});
       }});


       return reader;
    }

  
    @Autowired
    IProduit repository;

    //Writer class Object
    @Bean
    public ItemWriter<Produit> writer(){
       // return new ProduitItemWriter(); // Using lambda expression code instead of a separate implementation
       return Produits -> {
         System.out.println("Saving Produit Records: " +Produits);
         repository.saveAll(Produits);
       };
    }

    //Processor class Object
    @Bean
    public ItemProcessor<Produit, Produit> processor(){
      // return new ProduitProcessor(); // ou Utilisation de lambda expression 
      return Produit -> {
         Double discount = Produit.getPrix()*(Produit.getDiscount()/100.0);
         Double finalAmount= Produit.getPrix()-discount;
         Produit.setFinalprix(finalAmount);
         return Produit;
      };
    }

    //Listener class Object
    @Bean
    public JobExecutionListener listener() {
       return new ProduitListener();
    }

   
    @Autowired
    private StepBuilderFactory sbf;

    //Step Object
    @Bean
    public Step stepA() {
       return sbf.get("stepA")
               .<Produit,Produit>chunk(2)
               .reader(reader())
               .processor(processor())
               .writer(writer())
               .build() 
       ;
    }

   
    @Autowired
    private JobBuilderFactory jbf;

    //Job Object
    @Bean
    public Job jobA(){
       return jbf.get("jobA")
              .incrementer(new RunIdIncrementer())
              .listener(listener())
              .start(stepA())
           // .next(stepB()) 
           // .next(stepC())
              .build()
       ;
    }

    @Bean
    public Job jobB(){
       return jbf.get("jobB")
              .incrementer(new RunIdIncrementer())
              .listener(listener())
              .start(stepA())
           // .next(stepB()) 
           // .next(stepC())
              .build()
       ;
    }
    
//    @Scheduled(cron = "")
//    public void lancer()
//    {
//    	JobParameters jobParameters =
//                new JobParametersBuilder()
//                  .addLong("time", System.currentTimeMillis())
//                  .toJobParameters();
//
//          jobLauncher.run(jobA, jobParameters);
//          System.out.println("JOB Execution completed!");
//    }
}