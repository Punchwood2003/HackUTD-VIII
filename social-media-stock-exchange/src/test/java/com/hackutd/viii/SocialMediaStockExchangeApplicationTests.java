package com.hackutd.viii;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hackutd.viii.social.TopTickers;
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
	
	@Test
	void multiple() throws IOException, InterruptedException {
		PrintWriter out = new PrintWriter("TestingOutput.txt");
		TopTickers topTickers = new TopTickers();
		String[] top100StockTickersArr = topTickers.getTopStockTickers();
		ArrayList<String> asList = this.toList(top100StockTickersArr);
		final List<StockWrapper> stocks = stockService.findStocks(asList);
		
		for(StockWrapper currWrapper : stocks) {
			out.println(currWrapper.getStock());
		}
		out.close();
		
	}
	
	public ArrayList<String> toList(String[] arr) {
		ArrayList<String> list = new ArrayList<String>();
		for(String temp : arr) {
			list.add(temp);
		}
		return list;
	}
}
