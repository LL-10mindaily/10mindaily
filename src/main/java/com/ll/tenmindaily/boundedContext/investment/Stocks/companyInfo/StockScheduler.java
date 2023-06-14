package com.ll.tenmindaily.boundedContext.investment.Stocks.companyInfo;

import com.ll.tenmindaily.boundedContext.investment.Stocks.KoreaData.KoreaStockCrawlingData;
import com.ll.tenmindaily.boundedContext.investment.Stocks.KoreaData.KoreaStockCrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class StockScheduler {

    private final KoreaStockCrawlingService crawlingService;
    private final StockService stockService;

    @Autowired
    public StockScheduler(KoreaStockCrawlingService crawlingService, StockService stockService) {
        this.crawlingService = crawlingService;
        this.stockService = stockService;
    }

    @Scheduled(fixedDelay = 10000000) // 10초마다 실행
    public void updateStockData() {
        // 클롤링을 통해 새로운 주식 데이터 가져오기
        List<KoreaStockCrawlingData> crawlingData0 = crawlingService.getKoreaStockCrawling(0); // sosok 값이 0일 때 데이터 가져오기
        List<KoreaStockCrawlingData> crawlingData1 = crawlingService.getKoreaStockCrawling(1); // sosok 값이 1일 때 데이터 가져오기

        updateStockDataWithSosok(crawlingData0, ".ks"); // sosok 값이 0일 때 .ks 접미사 추가
        updateStockDataWithSosok(crawlingData1, ".kq"); // sosok 값이 1일 때 .kq 접미사 추가
    }

    private void updateStockDataWithSosok(List<KoreaStockCrawlingData> crawlingData, String suffix) {
        for (KoreaStockCrawlingData data : crawlingData) {
            String symbol = data.getSymbol();
            String ticker = symbol + suffix;

            // 기존에 저장된 주식 정보 가져오기
            Stock existingStock = stockService.getStockBySymbol(symbol);

            // 주식 정보 업데이트하기
            try {
                String companyInfo = stockService.getYahooCompanyInfo(ticker);
                String targetInfo = stockService.getYahooFinancialData(ticker);
                String annInfo = stockService.getYahooAnalystPredictions(ticker);

                if (companyInfo != null && targetInfo != null && annInfo != null) {
                    if (existingStock != null) {
                        stockService.updateStock(symbol, companyInfo, targetInfo, annInfo);
                    } else {
                        stockService.saveStockCompanyData(symbol, companyInfo, targetInfo, annInfo);
                    }
                } else {
                    System.out.println("Skipping stock with symbol: " + symbol);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
