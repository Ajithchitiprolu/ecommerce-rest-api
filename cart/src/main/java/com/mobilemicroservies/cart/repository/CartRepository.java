package com.mobilemicroservies.cart.repository;

import com.mobilemicroservies.cart.model.CartMobile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<CartMobile, String> {
}
