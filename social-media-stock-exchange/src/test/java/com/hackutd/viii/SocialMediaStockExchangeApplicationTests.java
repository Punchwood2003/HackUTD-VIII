package com.hackutd.viii;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hackutd.viii.yahoofinance.StockService;
import com.hackutd.viii.yahoofinance.StockWrapper;

@SpringBootTest
class SocialMediaStockExchangeApplicationTests {
	@Autowired
	private StockService stockService;
	
	@Test
	void invoke() throws IOException {
		final StockWrapper stock = stockService.findStock("UU.L");
		System.out.println(stock.getStock());
		
		final BigDecimal price = stockService.findPrice(stock);
		System.out.println(price);
		
		final BigDecimal change = stockService.findLastChangePercent(stock);
		System.out.println(change);
		
		final BigDecimal mean20DayPercent = stockService.findChangeFrom20MeanPercent(stock);
		System.out.println(mean20DayPercent);
	}
}
