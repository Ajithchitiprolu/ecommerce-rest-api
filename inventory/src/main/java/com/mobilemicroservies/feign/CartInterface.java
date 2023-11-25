package com.mobilemicroservies.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("CART-SERVICE")
public interface CartInterface {

}
