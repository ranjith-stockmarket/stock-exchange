package com.stockmarket.stockexchange.Service;

import com.stockmarket.stockexchange.DAO.StockExchange;
import com.stockmarket.stockexchange.DTO.AddStockExchangeDTO;
import com.stockmarket.stockexchange.DTO.StockExchangeDTO;
import com.stockmarket.stockexchange.DTO.StockExchangeIdsDTO;
import com.stockmarket.stockexchange.Helper.ServiceResponse;
import com.stockmarket.stockexchange.Respository.StockExchangeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockExchangeService{

    private final StockExchangeRepository stockExchangeRepository;
    private final ModelMapper modelMapper;

    public StockExchangeService(StockExchangeRepository stockExchangeRepository, ModelMapper modelMapper) {
        this.stockExchangeRepository = stockExchangeRepository;
        this.modelMapper = modelMapper;
    }

    public ServiceResponse addStockExchange(AddStockExchangeDTO addStockExchangeDTO){
        if(stockExchangeRepository.existsByName(addStockExchangeDTO.getName())){
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "StockExchange already Exists");
        }
        StockExchange savedStockExchange = stockExchangeRepository.save(modelMapper.map(addStockExchangeDTO, StockExchange.class));
        if(stockExchangeRepository.existsById(savedStockExchange.getId())){
            return new ServiceResponse(HttpStatus.CREATED,
                    modelMapper.map(savedStockExchange, StockExchangeDTO.class));
        }
        return new ServiceResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Please try again later!");
    }

    public ServiceResponse getStockExchangeById(Long id){
        Optional<StockExchange> stockExchange = stockExchangeRepository.findById(id);
        if(stockExchange.isPresent())
            return new ServiceResponse(HttpStatus.OK, modelMapper.map(stockExchange.get(), StockExchangeDTO.class));
        return new ServiceResponse(HttpStatus.NOT_FOUND,"StockExchange ID does not Exist");
    }

    public ServiceResponse getAllStockExchanges(){
        return new ServiceResponse(HttpStatus.OK,
                stockExchangeRepository
                        .getAll()
                        .stream()
                        .map( stockExchange -> modelMapper.map(stockExchange, StockExchangeDTO.class ))
                        .collect(Collectors.toList())
            );
    }

    public ServiceResponse doExist(StockExchangeIdsDTO stockExchangeIdsDTO) {
        for(Long id : stockExchangeIdsDTO.getStockExchangeIds()){
            if(!stockExchangeRepository.existsById(id)){
                return new ServiceResponse(HttpStatus.OK, Boolean.FALSE);
            }
        }
        return new ServiceResponse(HttpStatus.OK, Boolean.TRUE);
    }
}
