package com.example.demo.config;


import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.example.demo.config.items.Process1;
import com.example.demo.entities.Produit;
import com.example.demo.mapper.ProduitMapper;
@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ProduitMapper produitMapper;

    @Autowired
    private DataSource dataSource;
    
	@Bean
	  public JdbcCursorItemReader<Produit> jdbcCursorItemReader(){
	      JdbcCursorItemReader<Produit> jdbcCursorItemReader = 
	        	new JdbcCursorItemReader<>();
	      jdbcCursorItemReader.setDataSource(dataSource);
	      jdbcCursorItemReader.setSql("SELECT * FROM produit");
	      jdbcCursorItemReader.setRowMapper(produitMapper);
	      return jdbcCursorItemReader;
	    }
	
	
	
	@Bean(name = "generateCSVReportCard")
    public Job generateCSVReportCard() {
        return jobBuilderFactory
                .get("generateReportCard")
                .incrementer(new RunIdIncrementer())
                .start(processStudentReport()).build();
    }

	@Bean
	public ItemProcessor<Produit,Produit> process()
	{
		return new Process1();
	}
	
	
    @Bean
    public Step processStudentReport() {
        return stepBuilderFactory.get("processProduitReport")
                .<Produit,Produit>chunk(1)
                .reader(jdbcCursorItemReader())
                .processor(process())
                
                .writer(flatFileItemWriter())
                .build();
    }

  

    @Bean
    public FlatFileItemWriter<Produit> flatFileItemWriter(){
        FlatFileItemWriter<Produit> flatFileItemWriter
                = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource
          (new FileSystemResource
           ("d://produits.csv"));
        flatFileItemWriter.setLineAggregator(new DelimitedLineAggregator<Produit>() 
           {{
             setDelimiter(",");
             setFieldExtractor(new BeanWrapperFieldExtractor<Produit>() 
             {{
             	setNames(new String[] { "id", "desg","prix"});
             }});
            }});
        flatFileItemWriter.setHeaderCallback
                (writer -> writer.write("Id,Desg,Prix"));
        return flatFileItemWriter;
    }
}
