package com.example.xPrice.repository;

import com.example.xPrice.domain.Website;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepository extends CrudRepository<Website, Integer> {

}
