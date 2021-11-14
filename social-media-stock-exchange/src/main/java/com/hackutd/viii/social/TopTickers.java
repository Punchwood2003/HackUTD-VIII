package com.hackutd.viii.social;

import java.io.IOException;

/**
 * This class handles integrating the list of 
 * tickers to check with the Yahoo Finance data
 * @author MatthewSheldon
 */
public class TopTickers {
	/**
	 * The current running CryptoReader object
	 */
	private CryptoReader cryptoReader;
	
	/**
	 * The current running StockReader object
	 */
	private StockReader stockReader;
	
	/**
	 * Constructs a TopTickers object and initializes the reader objects
	 */
	public TopTickers() {
		this.initializeReaders();
	}
	
	/**
	 * Initializes the reader objects
	 */
	public void initializeReaders() {
		// Instantiate the two reader objects
		cryptoReader = new CryptoReader();
		stockReader = new StockReader();
		
		// Attempt to perform the live update request for crypto
		try {
			cryptoReader.run();
		} catch (IOException e) {
			// This exception gets thrown when one of the files is missing
			System.out.println("Could not find a required dependency");
			System.out.println("Please ensure that the following files are icluded...");
			System.out.println("\tScrapeApeWisdom_Cryptos.bat");
			System.out.println("\ttop100Cryptos.txt");
			e.printStackTrace();
			return;
		} catch (InterruptedException e) {
			// This exception gets thrown when the scrape process gets interrupted
			System.out.println("The following process was interrupted and forced the execution to terminate:");
			System.out.println("\tScrapeApeWisom_Cryptos.bat");
			e.printStackTrace();
			return;
		}
		
		// Attempt to perform the live update request for stocks
		try {
			stockReader.run();
		} catch (IOException e) {
			// This exception gets thrown when one of the files is missing
			System.out.println("Could not find a required dependency");
			System.out.println("Please ensure that the following files are icluded...");
			System.out.println("\tScrapeApeWisdom_Stocks.bat");
			System.out.println("\ttop100Stocks.txt");
			e.printStackTrace();
			System.exit(0);
		} catch (InterruptedException e) {
			// This exception gets thrown when the scrape process gets interrupted
			System.out.println("The following process was interrupted and forced the execution to terminate:");
			System.out.println("\tScrapeApeWisom_Stocks.bat");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * Returns a list of the top 100 tickers for cryptocurencies
	 * @return	the top 100 tickers for cryptocurencies
	 */
	public String[] getTopCryptoTickers() {
		return this.cryptoReader.getTop100Tickers();
	}
	
	/**
	 * Returns a list of the top 100 tickers for stocks
	 * @return	the top 100 tickers for stocks
	 */
	public String[] getTopStockTickers() {
		return this.stockReader.getTop100Tickers();
	}
}
