package com.stockmarket.stockexchange.Controller;

import com.stockmarket.stockexchange.DTO.AddStockExchangeDTO;
import com.stockmarket.stockexchange.DTO.StockExchangeIdsDTO;
import com.stockmarket.stockexchange.Service.StockExchangeService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stock-exchange")
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;

    public StockExchangeController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addStockExchange(@Valid @RequestBody AddStockExchangeDTO addStockExchangeDTO){
        return stockExchangeService.addStockExchange(addStockExchangeDTO).getResponse();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getStockExchangeById(@PathVariable("id") Long id){
        return stockExchangeService.getStockExchangeById(id).getResponse();
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllStockExchanges(){
        return stockExchangeService.getAllStockExchanges().getResponse();
    }

    @PostMapping("/doExist/multiple")
    public ResponseEntity<Object> doStockExchangeIdsExist(@RequestBody StockExchangeIdsDTO stockExchangeIdsDTO){
        return stockExchangeService.doExist(stockExchangeIdsDTO).getResponse();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)//.map( fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.joining(" , ")));
    }

}
