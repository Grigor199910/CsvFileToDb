package com.example.csvfilesinafile.config.rw;

import com.example.csvfilesinafile.model.Client;
import com.example.csvfilesinafile.repo.ClientRepo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public
class F implements ItemWriter<Client> {

    @Autowired
    private ClientRepo clientRepo;


    @Override
    public void write(List<? extends Client> list) throws Exception {
        for (Client data : list) {
            clientRepo.save(data);
            System.out.println("MyCustomWriter    : Writing data    : " + data);
        }
    }
}