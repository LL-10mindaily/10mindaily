//package com.ll.tenmindaily.boundedContext.investment.Stocks.KoreaData;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class KoreaStockCrawlingService {
//    public List<KoreaStockCrawlingData> getKoreaStockCrawling() {
//        List<KoreaStockCrawlingData> rankings = new ArrayList<>();
//
//        try {
//            for (int i = 0; i < 20; i++) {
//                int sosok = i % 2; // sosok 값을 0과 1로 번갈아가며 설정
//                int page = (i / 2) + 1; // 페이지 번호 계산
//
//                String url = "https://finance.naver.com/sise/sise_market_sum.nhn?sosok=" + sosok + "&page=" + page;
//                Document doc = Jsoup.connect(url).get();
//
//                Elements boxes = doc.select("div.box_type_l");
//
//                // 첫 번째 box를 선택하고, 그 안에서 필요한 데이터를 추출합니다.
//                Element firstBox = boxes.first();
//
//                Elements rows = firstBox.select("tr");
//                for (Element row : rows) {
//                    Element tickerElement = row.select("a.tltle").first();
//                    if (tickerElement != null) {
//                        String ticker = tickerElement.attr("href").split("code=")[1];
//                        String companyName = tickerElement.text();
//                        rankings.add(new KoreaStockCrawlingData(companyName, ticker));
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return rankings;
//    }
//
//}

package com.ll.tenmindaily.boundedContext.investment.Stocks.KoreaData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KoreaStockCrawlingService {
    public List<KoreaStockCrawlingData> getKoreaStockCrawling() {
        List<KoreaStockCrawlingData> rankings = new ArrayList<>();

        try {
            int sosok = 0; // sosok 값을 0으로 고정

            for (int page = 1; page <= 10; page++) { // 페이지 번호를 1부터 10까지 반복
                String url = "https://finance.naver.com/sise/sise_market_sum.nhn?sosok=" + sosok + "&page=" + page;
                Document doc = Jsoup.connect(url).get();

                Elements boxes = doc.select("div.box_type_l");

                // 첫 번째 box를 선택하고, 그 안에서 필요한 데이터를 추출합니다.
                Element firstBox = boxes.first();

                Elements rows = firstBox.select("tr");
                for (Element row : rows) {
                    Element tickerElement = row.select("a.tltle").first();
                    if (tickerElement != null) {
                        String ticker = tickerElement.attr("href").split("code=")[1];
                        String companyName = tickerElement.text();
                        rankings.add(new KoreaStockCrawlingData(companyName, ticker, sosok));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rankings;
    }
}
