package com.mobilemicroservies.cart.feign;

import com.mobilemicroservies.cart.dto.MobileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("INVENTORY-SERVICE")
public interface InventoryInterface {
    @GetMapping("/api/mobile/{id}")
    ResponseEntity<MobileResponse> getMobile(@PathVariable String id);
}
