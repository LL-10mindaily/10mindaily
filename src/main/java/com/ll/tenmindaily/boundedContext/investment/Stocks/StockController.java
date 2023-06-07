package com.ll.tenmindaily.boundedContext.investment.Stocks;

import com.ll.tenmindaily.base.rsData.RsData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/stocks")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/")
    public String showMain() {
        return "usr/Stock/stockHome";
    }

    //회사 정보(알파벤티지)
    @GetMapping("/alpha/company/{symbol}")
    public ResponseEntity<String> getAlphaCompanyInfo(@PathVariable String symbol) {
        try {
            String companyInfo = stockService.getAlphaCompanyInfo(symbol);
            return ResponseEntity.ok("alpha: " + companyInfo);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //야후 파이낸스 회사정보 볼 수 있도록
    @GetMapping("/yahoo/company/{symbol}")
    public ResponseEntity<String> getYahooCompanyInfo(@PathVariable String symbol) {
        try {
            String companyInfo = stockService.getYahooCompanyInfo(symbol);
            return ResponseEntity.ok(companyInfo);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //yahoo fiance api db 저장 메서드
    @PostMapping("/add/{symbol}")
    public ResponseEntity<String> saveStockData(@PathVariable String symbol) {
        try {
            String companyInfo = stockService.getYahooCompanyInfo(symbol);
            RsData<String> result = stockService.saveStockData(symbol, companyInfo);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result.getData());
            } else {
                return ResponseEntity.badRequest().body(result.getData());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DB에 저장된 티커가 있다면 삭제
    @PostMapping("/delete/{symbol}")
    public ResponseEntity<String> deleteStockData(@PathVariable String symbol) {
        boolean success = stockService.deleteStockData(symbol);
        if (success) {
            return ResponseEntity.ok("주식 데이터 삭제 성공");
        } else {
            return ResponseEntity.badRequest().body("주식 데이터 삭제 실패");
        }
    }

}
