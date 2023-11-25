package com.mobilemicroservies.cart.service;

import com.mobilemicroservies.cart.dto.MobileRequest;
import com.mobilemicroservies.cart.dto.MobileResponse;
import com.mobilemicroservies.cart.event.cartEvent;
import com.mobilemicroservies.cart.feign.InventoryInterface;
import com.mobilemicroservies.cart.model.CartMobile;
import com.mobilemicroservies.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
//    @Autowired
//    private final InventoryInterface inventoryInterface;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    InventoryInterface inventoryInterface;

    @Autowired
    KafkaTemplate<String, cartEvent> kafkaTemplate;

    public Boolean addToCart(MobileRequest mobileRequest){
        MobileResponse mobileResponse = inventoryInterface.getMobile(mobileRequest.getMobileId()).getBody();

        if(mobileResponse == null || mobileResponse.getStock() < mobileRequest.getQuantity()){
            return false;
        }
        CartMobile mobile = CartMobile.builder()
                .mobileId(mobileRequest.getMobileId())
                .name(mobileResponse.getName())
                .description(mobileResponse.getDescription())
                .price(mobileResponse.getPrice())
                .quantity(mobileRequest.getQuantity())
                .build();
        kafkaTemplate.send("notificationTopic", new cartEvent(mobile.getMobileId()));

        cartRepository.save(mobile);

        return true;

    }
}
