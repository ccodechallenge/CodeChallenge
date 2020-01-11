package com.challenge.demo.configuration;

import com.challenge.demo.Constants;
import com.challenge.demo.repository.entity.Device;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final String READER_NAME = "deviceItemReader";
    private static final String STEP_NAME = "step1";
    private static final String JOB_NAME = "importDeviceJob";

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public JsonItemReader<Device> reader() {
        return new JsonItemReaderBuilder<Device>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Device.class))
                .resource(new ClassPathResource(Constants.FILE_DEVICES))
                .name(READER_NAME)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Device> writer(final DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Device>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO TBL_DEVICE (BRAND, MODEL, OS, OS_VERSION) VALUES (:brand, :model, :os, :osVersion)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step step(JdbcBatchItemWriter<Device> writer) {
        return stepBuilderFactory.get(STEP_NAME)
                .<Device, Device> chunk(7)
                .reader(reader())
                .writer(writer)
                .build();
    }

    @Bean
    public Job importJob(Step step1) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }
}
