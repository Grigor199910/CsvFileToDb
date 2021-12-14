package com.example.csvfilesinafile.config.rw;

import com.example.csvfilesinafile.model.Client;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Component;

@Component
public class Fs extends FlatFileItemReader<Client> implements ItemReader<Client> {

    public Fs() {
        setLinesToSkip(1);
        setLineMapper(getDefaultLineMapper());
    }

    public DefaultLineMapper<Client> getDefaultLineMapper() {
        DefaultLineMapper<Client> defaultLineMapper = new DefaultLineMapper<Client>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(new String[]{"first_name", "last_name", "date"});
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        BeanWrapperFieldSetMapper<Client> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<Client>();
        beanWrapperFieldSetMapper.setTargetType(Client.class);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        return defaultLineMapper;
    }
}