package com.mobilemicroservies.service;

import com.mobilemicroservies.dto.MobileRequest;
import com.mobilemicroservies.dto.StockRequest;
import com.mobilemicroservies.model.Mobile;
import com.mobilemicroservies.repository.MobileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MobileService {
    private final MobileRepository mobileRepository;

    public void createProduct(MobileRequest mobileRequest){
        Mobile mobile = Mobile.builder()
                .name(mobileRequest.getName())
                .description(mobileRequest.getDescription())
                .price(mobileRequest.getPrice())
                .stock(mobileRequest.getStock())
                .build();

        mobileRepository.save(mobile);
        log.info("New Mobile : {} is added", mobile.getId());

    }

    public Boolean addStock(String id, StockRequest stockRequest){
        Optional<Mobile> mobileOptional = mobileRepository.findById(id);


        if(mobileOptional.isPresent()){
            Mobile mobile = mobileOptional.get();
            mobile.setStock(mobile.getStock() + stockRequest.getStock());

            mobileRepository.save(mobile);

            return true;
        }
        return false;


    }
    public List<Mobile> getAllProducts(){
        return mobileRepository.findAll();
    }

    public Optional<Mobile> getMobileById(String id){

        return mobileRepository.findById(id);

    }
    public Optional<Mobile> updateMobileById(String id, MobileRequest mobileRequest){
        Optional<Mobile> mobileOptional = mobileRepository.findById(id);
        if(mobileOptional.isPresent()) {
            Mobile mobile = mobileOptional.get();

            mobile.setName(mobileRequest.getName());
            mobile.setDescription(mobileRequest.getDescription());
            mobile.setPrice(mobileRequest.getPrice());

            mobileRepository.save(mobile);

        }
        return mobileOptional;
    }
    public boolean deleteMobileById(String id){
        Optional<Mobile> mobileOptional = mobileRepository.findById(id);
        if(mobileOptional.isPresent()){
            Mobile mobile = mobileOptional.get();

            mobileRepository.delete(mobile);
            return true;
        }
        return false;
    }

    public List<Mobile> searchMobiles(String search_string){
        return mobileRepository.findByNameContainingIgnoreCase(search_string);
    }
}
