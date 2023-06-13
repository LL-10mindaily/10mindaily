package com.ll.tenmindaily.boundedContext.investment.Stocks.companyInfo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockScheduler {

    private final StockService stockService;
    private final StockRepository stockRepository;

    public StockScheduler(StockService stockService, StockRepository stockRepository) {
        this.stockService = stockService;
        this.stockRepository = stockRepository;
    }

    @Scheduled(cron = "0 10 7 * * *") // 매일 오전 7시 10분에 실행
    public void updateStockData() {
        updateStock();
    }

    public void updateStock() {
        // 업데이트할 종목 심볼 목록 가져오기
        List<Stock> symbols = stockRepository.findAll();

        for (Stock symbol : symbols) {
            String companyInfo;
            String targetInfo;
            String annInfo;

            try {
                companyInfo = stockService.getYahooCompanyInfo(symbol.getTicker());
                targetInfo = stockService.getYahooFinancialData(symbol.getTicker());
                annInfo = stockService.getYahooAnalystPredictions(symbol.getTicker());
                stockService.updateStock(symbol.getTicker(), companyInfo, targetInfo, annInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
