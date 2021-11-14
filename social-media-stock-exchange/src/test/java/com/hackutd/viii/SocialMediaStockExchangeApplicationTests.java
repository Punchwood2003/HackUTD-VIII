package com.hackutd.viii;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

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
	
//	@Test
//	void multiple() throws IOException, InterruptedException {
//		PrintWriter out = new PrintWriter("TestingOutput.txt");
//		out.printf("%-4s | %-30s | %-5s | %-14s%n", "Rank", "Stock Name", "Ticker", "Current Price");
//		TopTickers topTickers = new TopTickers();
//		ArrayList<String> asList = this.toList(topTickers.getTopStockTickers());
//		final List<StockWrapper> stocks = stockService.findStocks(asList);
//		for(int i = 0; i < asList.size(); i++) {
//			String currTicker = asList.get(i);
//			String infoForStock = topTickers.getInfoForStock(currTicker);
//			out.print(infoForStock);
//			try {
//				BigDecimal price = stockService.findPrice(stocks.get(i));
//				double priceAsDouble = price.doubleValue();
//				String priceAsString = String.format("$%.2f", priceAsDouble);
//				String formattedPrice = String.format("%14s | ", priceAsString);
//				out.print(price == null ? String.format("", "[N/A]") : formattedPrice);
//				out.println();
//			} catch(NullPointerException e) {
//				out.print("[N/A] | ");
//				out.println();
//			} catch(FileNotFoundException e) {
//				out.print("[N/A] | ");
//				out.println();
//			}
//		}
//		out.close();
//		
//	}
	
	public ArrayList<String> toList(String[] arr) {
		ArrayList<String> list = new ArrayList<String>();
		for(String temp : arr) {
			list.add(temp);
		}
		return list;
	}
}
