package com.example.dataprocess.config;

import com.example.dataprocess.entity.Personne;
import com.example.dataprocess.entity.PersonneRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

    private final PersonneRepository personneRepository;
    public SpringBatchConfiguration(PersonneRepository personneRepository){
        this.personneRepository = personneRepository;
    }

    @Bean
    public Step myStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step2", jobRepository)
                .<Personne, Personne>chunk(2, transactionManager)
                .reader(flatFileItemReader())
                .processor(new  PersonneItemProcessor())
                .writer(new  PersonneItemWriter(personneRepository))
                .build();
    }
    @Bean
    public Job myJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("myJob12", jobRepository)
                .start(step)
                .build();
    }


   @Bean
    public ItemReader<Personne> flatFileItemReader() {
        FlatFileItemReader<Personne> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("file.csv"));

        LineMapper<Personne> lineMapper = createLineMapper();
        reader.setLineMapper(lineMapper);

        return reader;
    }

    private LineMapper<Personne> createLineMapper() {
        DefaultLineMapper<Personne> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("ID", "prenom","nom", "age");

        BeanWrapperFieldSetMapper<Personne> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Personne.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
    @Bean
    public ItemWriter<Personne> itemWriter(){
        return new PersonneItemWriter(personneRepository);
    }
    @Bean
    public ItemProcessor<Personne,Personne> itemProcessor(){
        return new PersonneItemProcessor();
    }
}
