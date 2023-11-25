package com.mobilemicroservies.repository;

import com.mobilemicroservies.model.Mobile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MobileRepository extends MongoRepository<Mobile, String> {
    List<Mobile> findByNameContainingIgnoreCase(String name);
}
