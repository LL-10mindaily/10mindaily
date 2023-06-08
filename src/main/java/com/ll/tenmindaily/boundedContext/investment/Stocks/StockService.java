//package com.ll.tenmindaily.boundedContext.investment.Stocks;
//
//import com.ll.tenmindaily.base.rsData.RsData;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.io.IOException;
//
//@Service
//public class StockService {
//
//    private StockRepository stockRepository;
//    private final WebClient webClient;
//
//    public StockService(StockRepository stockRepository) {
//        this.stockRepository = stockRepository;
//        this.webClient = WebClient.create();
//    }
//
//    private final String alphaApiKey = "..";
//    private final String yahooApiBaseUrl = "https://query1.finance.yahoo.com";
//
//    // 알파벤티지 정보
//    public String getAlphaCompanyInfo(String symbol) throws IOException {
//        String url = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + alphaApiKey;
//        return webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//
//    // 알파벤티지 정보
//    public String getAlphaIncomeStatement(String symbol) throws IOException {
//        String url = "https://www.alphavantage.co/query?function=INCOME_STATEMENT&symbol=" + symbol + "&apikey=" + alphaApiKey;
//        return webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//
//    // 알파벤티지 정보
//    public String getAlphaBalanceSheet(String symbol) throws IOException {
//        String url = "https://www.alphavantage.co/query?function=BALANCE_SHEET&symbol=" + symbol + "&apikey=" + alphaApiKey;
//        return webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//
//    // 알파벤티지 정보
//    public String getAlphaCashFlow(String symbol) throws IOException {
//        String url = "https://www.alphavantage.co/query?function=CASH_FLOW&symbol=" + symbol + "&apikey=" + alphaApiKey;
//        return webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//
//    // 야후 파이낸스 api 정보
//    public String getYahooCompanyInfo(String symbol) throws IOException {
//        String endpoint = "/v10/finance/quoteSummary";
//        String modules = "summaryDetail";
//        String url = yahooApiBaseUrl + endpoint + "/" + symbol + "?modules=" + modules;
//
//        return webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//
//    // 컨트롤러에서 받아온 티커를 DB에 저장하는 메서드
//    public RsData<String> saveStockData(String symbol, String companyInfo) {
//        Stock existingStock = stockRepository.findBySymbol(symbol);
//
//        if (existingStock == null) {
//            Stock stock = yahooParseStockData(companyInfo);
//
//            if (stock != null) {
//                stock.setSymbol(symbol);  // 종목 심볼 설정
//                stockRepository.save(stock);
//                return RsData.successOf("종목이 추가되었습니다.");
//            } else {
//                return RsData.failOf("종목 추가에 실패했습니다.");
//            }
//        } else {
//            return RsData.failOf("이미 추가된 종목입니다.");
//        }
//    }
//
//    // 야후 api 파싱 메서드
//    public Stock yahooParseStockData(String companyInfo) {
//        try {
//            JSONObject stockDataObject = new JSONObject(companyInfo);
//            JSONObject quoteSummaryObject = stockDataObject.getJSONObject("quoteSummary");
//            JSONArray resultArray = quoteSummaryObject.getJSONArray("result");
//
//            if (resultArray.length() > 0) {
//                JSONObject summaryDetailObject = resultArray.getJSONObject(0).getJSONObject("summaryDetail");
//
//                // 필요한 데이터 추출
//                double previousClose = summaryDetailObject.getJSONObject("previousClose").getDouble("raw");
//                double open = summaryDetailObject.getJSONObject("open").getDouble("raw");
//
//                // 아래의 배당정보들은 없을 수 도 있으니까 제외할 수 있도록 ==> null값으로 넣을 수 있게
//                // 배당 정보 추출
//                Double dividendRate = summaryDetailObject.getJSONObject("dividendRate").optDouble("raw");
//                Double dividendYield = summaryDetailObject.getJSONObject("dividendYield").optDouble("raw");
//                String exDividendDate = summaryDetailObject.getJSONObject("exDividendDate").optString("fmt");
//
//                double marketCap = summaryDetailObject.getJSONObject("marketCap").getDouble("raw");
//                double forwardPE = summaryDetailObject.getJSONObject("forwardPE").getDouble("raw");
//                double fiftyTwoWeekLow = summaryDetailObject.getJSONObject("fiftyTwoWeekLow").getDouble("raw");
//                double fiftyTwoWeekHigh = summaryDetailObject.getJSONObject("fiftyTwoWeekHigh").getDouble("raw");
//                double fiftyDayAverage = summaryDetailObject.getJSONObject("fiftyDayAverage").getDouble("raw");
//                double priceToSalesTrailing12Months = summaryDetailObject.getJSONObject("priceToSalesTrailing12Months").getDouble("raw");
//                double twoHundredDayAverage = summaryDetailObject.getJSONObject("twoHundredDayAverage").getDouble("raw");
//
//                // Stock 객체 생성
//                Stock stock = new Stock();
//                stock.setPreviousClose(previousClose);
//                stock.setOpen(open);
//                if (Double.isNaN(dividendRate)) {
//                    stock.setDividendRate(null);
//                    stock.setDividendYield(null);
//                    stock.setExDividendDate(null);
//                } else {
//                    stock.setDividendRate(dividendRate);
//                    stock.setDividendYield(dividendYield);
//                    stock.setExDividendDate(exDividendDate);
//                }
//                stock.setMarketCap(marketCap);
//                stock.setForwardPE(forwardPE);
//                stock.setFiftyTwoWeekLow(fiftyTwoWeekLow);
//                stock.setFiftyTwoWeekHigh(fiftyTwoWeekHigh);
//                stock.setFiftyDayAverage(fiftyDayAverage);
//                stock.setPriceToSalesTrailing12Months(priceToSalesTrailing12Months);
//                stock.setTwoHundredDayAverage(twoHundredDayAverage);
//
//                return stock;
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // 스케줄러에서 매일 아침 7시5분에 업데이트 해주는 메서드
//    public RsData<String> updateStock(String symbol, String companyInfo) {
//        Stock existingStock = stockRepository.findBySymbol(symbol);
//
//        if (existingStock != null) {
//            Stock updatedStock = yahooParseStockData(companyInfo);
//
//            if (updatedStock != null) {
//                updatedStock.setId(existingStock.getId());  // 기존 주식 정보의 ID 설정
//                updatedStock.setSymbol(symbol);  // 종목 심볼 설정
//                stockRepository.save(updatedStock);
//                return RsData.successOf("종목이 업데이트되었습니다.");
//            } else {
//                return RsData.failOf("종목 업데이트에 실패했습니다.");
//            }
//        } else {
//            return RsData.failOf("존재하지 않는 종목입니다.");
//        }
//    }
//
//    //원하는 종목을 삭제해주는 메서드
//    public boolean deleteStockData(String symbol) {
//        Stock existingStock = stockRepository.findBySymbol(symbol);
//        if (existingStock == null) {
//            return false;
//        } else {
//            stockRepository.delete(existingStock);
//            return true;
//        }
//    }
//}

package com.ll.tenmindaily.boundedContext.investment.Stocks;

import com.ll.tenmindaily.base.rsData.RsData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class StockService {

    private StockRepository stockRepository;
    private final WebClient webClient;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
        this.webClient = WebClient.create();
    }

    private final String alphaApiKey = "..";
    private final String yahooApiBaseUrl = "https://query1.finance.yahoo.com";

    // 알파벤티지 정보
    public String getAlphaCompanyInfo(String symbol) throws IOException {
        String url = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + alphaApiKey;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // 알파벤티지 정보
    public String getAlphaIncomeStatement(String symbol) throws IOException {
        String url = "https://www.alphavantage.co/query?function=INCOME_STATEMENT&symbol=" + symbol + "&apikey=" + alphaApiKey;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // 알파벤티지 정보
    public String getAlphaBalanceSheet(String symbol) throws IOException {
        String url = "https://www.alphavantage.co/query?function=BALANCE_SHEET&symbol=" + symbol + "&apikey=" + alphaApiKey;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // 알파벤티지 정보
    public String getAlphaCashFlow(String symbol) throws IOException {
        String url = "https://www.alphavantage.co/query?function=CASH_FLOW&symbol=" + symbol + "&apikey=" + alphaApiKey;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // 야후 파이낸스 api 정보
    public String getYahooCompanyInfo(String symbol) throws IOException {
        String endpoint = "/v10/finance/quoteSummary";
        String modules = "summaryDetail";
        String url = yahooApiBaseUrl + endpoint + "/" + symbol + "?modules=" + modules;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // 컨트롤러에서 받아온 티커를 DB에 저장하는 메서드
    public RsData<String> saveStockData(String symbol, String companyInfo) {
        Stock existingStock = stockRepository.findBySymbol(symbol);

        if (existingStock == null) {
            Stock stock = yahooParseStockData(companyInfo);

            if (stock != null) {
                stock.setSymbol(symbol);  // 종목 심볼 설정
                stockRepository.save(stock);
                return RsData.successOf("success");
            } else {
                return RsData.failOf("종목 추가에 실패했습니다.");
            }
        } else {
            return RsData.failOf("already_added");
        }
    }

    // 야후 api 파싱 메서드
    public Stock yahooParseStockData(String companyInfo) {
        try {
            JSONObject stockDataObject = new JSONObject(companyInfo);
            JSONObject quoteSummaryObject = stockDataObject.getJSONObject("quoteSummary");
            JSONArray resultArray = quoteSummaryObject.getJSONArray("result");

            if (resultArray.length() > 0) {
                JSONObject summaryDetailObject = resultArray.getJSONObject(0).getJSONObject("summaryDetail");

                // 필요한 데이터 추출
                double previousClose = summaryDetailObject.getJSONObject("previousClose").getDouble("raw");
                double open = summaryDetailObject.getJSONObject("open").getDouble("raw");

                // 아래의 배당정보들은 없을 수 도 있으니까 제외할 수 있도록 ==> null값으로 넣을 수 있게
                // 배당 정보 추출
                Double dividendRate = summaryDetailObject.getJSONObject("dividendRate").optDouble("raw");
                Double dividendYield = summaryDetailObject.getJSONObject("dividendYield").optDouble("raw");
                String exDividendDate = summaryDetailObject.getJSONObject("exDividendDate").optString("fmt");

                double marketCap = summaryDetailObject.getJSONObject("marketCap").getDouble("raw");
                double forwardPE = summaryDetailObject.getJSONObject("forwardPE").getDouble("raw");
                double fiftyTwoWeekLow = summaryDetailObject.getJSONObject("fiftyTwoWeekLow").getDouble("raw");
                double fiftyTwoWeekHigh = summaryDetailObject.getJSONObject("fiftyTwoWeekHigh").getDouble("raw");
                double fiftyDayAverage = summaryDetailObject.getJSONObject("fiftyDayAverage").getDouble("raw");
                double priceToSalesTrailing12Months = summaryDetailObject.getJSONObject("priceToSalesTrailing12Months").getDouble("raw");
                double twoHundredDayAverage = summaryDetailObject.getJSONObject("twoHundredDayAverage").getDouble("raw");

                // Stock 객체 생성
                Stock stock = new Stock();
                stock.setPreviousClose(previousClose);
                stock.setOpen(open);
                if (Double.isNaN(dividendRate)) {
                    stock.setDividendRate(null);
                    stock.setDividendYield(null);
                    stock.setExDividendDate(null);
                } else {
                    stock.setDividendRate(dividendRate);
                    stock.setDividendYield(dividendYield);
                    stock.setExDividendDate(exDividendDate);
                }
                stock.setMarketCap(marketCap);
                stock.setForwardPE(forwardPE);
                stock.setFiftyTwoWeekLow(fiftyTwoWeekLow);
                stock.setFiftyTwoWeekHigh(fiftyTwoWeekHigh);
                stock.setFiftyDayAverage(fiftyDayAverage);
                stock.setPriceToSalesTrailing12Months(priceToSalesTrailing12Months);
                stock.setTwoHundredDayAverage(twoHundredDayAverage);

                return stock;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 스케줄러에서 매일 아침 7시5분에 업데이트 해주는 메서드
    public RsData<String> updateStock(String symbol, String companyInfo) {
        Stock existingStock = stockRepository.findBySymbol(symbol);

        if (existingStock != null) {
            Stock updatedStock = yahooParseStockData(companyInfo);

            if (updatedStock != null) {
                updatedStock.setId(existingStock.getId());  // 기존 주식 정보의 ID 설정
                updatedStock.setSymbol(symbol);  // 종목 심볼 설정
                stockRepository.save(updatedStock);
                return RsData.successOf("종목이 업데이트되었습니다.");
            } else {
                return RsData.failOf("종목 업데이트에 실패했습니다.");
            }
        } else {
            return RsData.failOf("존재하지 않는 종목입니다.");
        }
    }

    //원하는 종목을 삭제해주는 메서드
    public boolean deleteStockData(String symbol) {
        Stock existingStock = stockRepository.findBySymbol(symbol);
        if (existingStock == null) {
            return false;
        } else {
            stockRepository.delete(existingStock);
            return true;
        }
    }
}
