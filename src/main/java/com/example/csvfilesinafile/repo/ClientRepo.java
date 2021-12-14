package com.example.csvfilesinafile.repo;

import com.example.csvfilesinafile.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {
}
