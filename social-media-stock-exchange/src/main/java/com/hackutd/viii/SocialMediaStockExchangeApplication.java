package com.hackutd.viii;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hackutd.viii.social.TopTickers;
import com.hackutd.viii.yahoofinance.StockService;
import com.hackutd.viii.yahoofinance.StockWrapper;

import yahoofinance.Stock;

@SpringBootApplication
public class SocialMediaStockExchangeApplication {
	@Autowired
	private StockService stockService;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SocialMediaStockExchangeApplication.class, args);
		new SocialMediaStockExchangeApplication().run();
	}
	
	private void run() throws IOException {
		stockService = new StockService();
		
		PrintWriter out = new PrintWriter("SocialMediaStockExchangeOutput.txt");
		out.printf("| %-4s | %-30s | %-5s | %-13s | %8s |%n", "Rank", "Stock Name", "Ticker", "Current Price", "Reliable");
		
		TopTickers topTickers = new TopTickers();
		ArrayList<String> asList = toList(topTickers.getTopStockTickers());
		List<StockWrapper> stocks = getStocks(asList);
		
		for(int i = 0; i < stocks.size(); i++) {
			String currTicker = asList.get(i);
			String infoForStock = topTickers.getInfoForStock(currTicker);
			out.print(infoForStock);
			try {
				BigDecimal price = stockService.findPrice(stocks.get(i));
				double priceAsDouble = price.doubleValue();
				String priceAsString = String.format("$%.2f", priceAsDouble);
				String formattedPrice = String.format("%13s | ", priceAsString);
				out.print(price == null ? String.format("%13s | ", "[N/A]") : formattedPrice);
			} catch(NullPointerException e) {
				out.print(String.format("%13s | ", "[N/A]"));
			} catch(FileNotFoundException e) {
				out.print(String.format("%13s | ", "[N/A]"));
			}
			try {
				Stock currStock = stocks.get(i).getStock();
				BigDecimal pe = currStock.getStats().getPe();
				double peAsDouble = pe.doubleValue();
				if(peAsDouble < 22.5) {
					out.print(String.format("%8s |", "Yes"));
					out.println();
				}
				else {
					out.print(String.format("%8s |", "No"));
					out.println();
				}
			} catch(NullPointerException e) {
				out.print(String.format("%8s |", "[N/A]"));
				out.println();
			}
		}
		out.close();
	}
	
	/**
	 * This converts a String array into an ArrayList of Strings
	 * @param arr	The String array
	 * @return		The String ArrayList
	 */
	public ArrayList<String> toList(String[] arr) {
		ArrayList<String> list = new ArrayList<String>();
		for(String temp : arr) {
			list.add(temp);
		}
		return list;
	}
	
	/**
	 * Gets the StockWrappper objects that are needed
	 * @param tickersAsList	An ArrayList of Strings that indicate 
	 * 						the tickers that need to be checked
	 * @return				The list of StockWrapper objects associated 
	 * 						with the list of tickers passed
	 */
	public ArrayList<StockWrapper> getStocks(ArrayList<String> tickersAsList) {
		ArrayList<StockWrapper> stockList = new ArrayList<StockWrapper>();
		
		for(String currTicker : tickersAsList) {
			try {
				final StockWrapper sw = stockService.findStock(currTicker);
				stockList.add(sw);
			} catch(NullPointerException e) {
				System.out.println("Null Pointer : " + currTicker);
			}
		}
		return stockList;
	}







}

