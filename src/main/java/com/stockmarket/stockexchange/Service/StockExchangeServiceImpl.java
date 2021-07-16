package com.stockmarket.stockexchange.Service;

import com.stockmarket.stockexchange.DAO.StockExchange;
import com.stockmarket.stockexchange.DTO.AddStockExchangeDTO;
import com.stockmarket.stockexchange.DTO.StockExchangeDTO;
import com.stockmarket.stockexchange.DTO.StockExchangeIdsDTO;
import com.stockmarket.stockexchange.Helper.ServiceResponse;
import com.stockmarket.stockexchange.Respository.StockExchangeInfo;
import com.stockmarket.stockexchange.Respository.StockExchangeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StockExchangeServiceImpl implements StockExchangeService{

    private final StockExchangeRepository stockExchangeRepository;
    private final ModelMapper modelMapper;

    public StockExchangeServiceImpl(StockExchangeRepository stockExchangeRepository, ModelMapper modelMapper) {
        this.stockExchangeRepository = stockExchangeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
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

    @Override
    public ServiceResponse getStockExchangeById(Long id){
        StockExchangeInfo stockExchangeInfo = stockExchangeRepository.getStockExchangeById(id);
        if(stockExchangeInfo != null)
            return new ServiceResponse(HttpStatus.OK, stockExchangeInfo);
        return new ServiceResponse(HttpStatus.NOT_FOUND,"StockExchange ID does not Exist");
    }

    @Override
    public ServiceResponse getAllStockExchanges(){
        return new ServiceResponse(HttpStatus.OK, stockExchangeRepository.getAll());
                /*stockExchangeRepository.findAll()
                        .stream()
                        .map( stockExchange -> modelMapper.map(stockExchange, StockExchangeDTO.class))
                        .collect(Collectors.toList())
                    );*/
    }

    @Override
    public ServiceResponse doExist(StockExchangeIdsDTO stockExchangeIdsDTO) {
        for(Long id : stockExchangeIdsDTO.getStockExchangeIds()){
            if(!stockExchangeRepository.existsById(id)){
                return new ServiceResponse(HttpStatus.OK, Boolean.FALSE);
            }
        }
        return new ServiceResponse(HttpStatus.OK, Boolean.TRUE);
    }
}
