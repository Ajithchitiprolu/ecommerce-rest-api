package com.mobilemicroservies.controller;

import com.mobilemicroservies.dto.MobileRequest;
import com.mobilemicroservies.dto.MobileResponse;
import com.mobilemicroservies.dto.StockRequest;
import com.mobilemicroservies.model.Mobile;
import com.mobilemicroservies.service.MobileService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/mobile")
@RequiredArgsConstructor
@Slf4j
public class MobileController {
    private final MobileService mobileService;

    @GetMapping
    public ResponseEntity<List<Mobile>> getAllProducts(){
        List<Mobile> mobiles = mobileService.getAllProducts();

        return new ResponseEntity<>(mobiles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createMobile(@RequestBody MobileRequest mobileRequest){
        mobileService.createProduct(mobileRequest);

        return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MobileResponse> getMobile(@PathVariable String id){
        Optional<Mobile> mobileOptional = mobileService.getMobileById(id);

        if(mobileOptional.isPresent()){
            Mobile mobile = mobileOptional.get();
            MobileResponse mobileResponse = MobileResponse.builder()
                    .id(mobile.getId())
                    .name(mobile.getName())
                    .description((mobile.getDescription()))
                    .price(mobile.getPrice())
                    .stock(mobile.getStock())
                    .build();

            return new ResponseEntity<>(mobileResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


    }

    @PostMapping("/{id}")
    public ResponseEntity<Mobile> updateMobile(@PathVariable String id, @RequestBody MobileRequest mobileRequest){
        Optional<Mobile> mobileOptional = mobileService.updateMobileById(id, mobileRequest);

        return mobileOptional.map(mobile -> new ResponseEntity<>(mobile, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMobile(@PathVariable String id){
        boolean deleted = mobileService.deleteMobileById(id);
        if(deleted){
            return ResponseEntity.ok("Delete Successful");
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> addStock(@PathVariable String id, @RequestBody StockRequest stockRequest){
        log.info("New Request for {}", id);
        if(mobileService.addStock(id, stockRequest)){
            return new ResponseEntity<String>("Updated Request Successful", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Failed Request", HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Mobile>> searchMobiles(@RequestParam String search){
        List<Mobile> mobiles = mobileService.searchMobiles(search);

        if(!mobiles.isEmpty()){
            return new ResponseEntity<>(mobiles, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
