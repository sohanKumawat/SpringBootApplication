package com.slk.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slk.demo.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long>{
}
