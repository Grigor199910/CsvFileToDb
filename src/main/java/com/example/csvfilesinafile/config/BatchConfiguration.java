package com.example.csvfilesinafile.config;


import com.example.csvfilesinafile.model.Client;
import com.example.csvfilesinafile.config.rw.Fs;
import com.example.csvfilesinafile.config.rw.F;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private F writer;

    @Autowired
    private Fs reader;

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("MyJob")
                .incrementer(new RunIdIncrementer())
                .flow(createStep()).end().build();
    }

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("MyStep")
                .<Client, Client> chunk(1)
                .reader(reader())
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<Client> reader() {
        Resource[] resources = null;
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
//            /Users/grigormartirosyan/IdeaProjects/csvFilesInAFile/data
//            resources = patternResolver.getResources("file:./data/*.csv");
            resources = patternResolver.getResources("file:./data/*.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MultiResourceItemReader<Client> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(this.reader);
        return reader;
    }
}
