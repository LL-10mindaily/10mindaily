package com.ll.tenmindaily.boundedContext.investment.Stocks.KoreaData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stocks")
public class KoreaStockCrawlingController {
    private final KoreaStockCrawlingService koreaStockCrawlingService;

    @Autowired
    public KoreaStockCrawlingController(KoreaStockCrawlingService koreaStockCrawlingService) {
        this.koreaStockCrawlingService = koreaStockCrawlingService;
    }

    @GetMapping("/koreaStockRanking")
    public String getKospiRanking(Model model) {
        model.addAttribute("rankings", koreaStockCrawlingService.getKoreaStockCrawling());
        return "usr/Stock/koreaData";
    }
}