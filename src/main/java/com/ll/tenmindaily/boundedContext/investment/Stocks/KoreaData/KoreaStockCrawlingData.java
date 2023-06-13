package com.ll.tenmindaily.boundedContext.investment.Stocks.KoreaData;

import lombok.Getter;

@Getter
public class KoreaStockCrawlingData {
    private String companyName;
    private String ticker;

    public KoreaStockCrawlingData(String companyName, String ticker) {
        this.companyName = companyName;
        this.ticker = ticker;
    }
}