package com.hackutd.viii.social;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class handles requesting an updated list of the 
 * top 100 Crypto Currencies on Reddit, reading in the data,
 * and initializing a map between a tinker and its respective
 * Crypto object
 * @author MatthewSheldon
 */
public class CryptoReader {
	/**
	 * A HashMap that maps a Cryptocurrency's ticker to its info.
	 * This is where the top 100 cryptocurrencies can be referenced
	 * @author MatthewSheldon
	 */
	private HashMap<String, Crypto> tickerToCryptoInfo;
	
	/**
	 * An array indicating the top 100 tickers
	 * @author MatthewSheldon
	 */
	private String[] top100Tickers;
	
	/**
	 * The file directory to read from
	 * @author MatthewSheldon
	 */
	private String fileName = "top100Cryptos.txt";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new CryptoReader().run();
	}
	
	/**
	 * This function when run will read in from the "top100Cryptos" file
	 * and initialize the tickerSymbols array containing the top 100 crypto
	 * Ticker Symbols.
	 * @throws IOException
	 * @author MatthewSheldon
	 * @throws InterruptedException 
	 */
	public void run() throws IOException, InterruptedException {
		/**
		 * Runs the ScrapeApeWisdom_Cryptos.bat file 
		 * and waits until the file has finished 
		 * executing before continuing to run
		 * @author GabrielaHuerta
		 */
		Process stocksProcess = Runtime.getRuntime().exec("ScrapeApeWisdom_Cryptos.bat");
		stocksProcess.waitFor();
		
		/**
		 * Moving forward...
		 * @author MatthewSheldon
		 */
		// Create a BufferedReader to read from the top100Cryptos.txt file
		BufferedReader file = new BufferedReader(new FileReader(fileName));
		
		// Read in the entire line of input
		String line = file.readLine();
		// Remove unimportant text
		int index1 = line.indexOf('[') + 1;
		int index2 = line.indexOf(']');
		line = line.substring(index1, index2);
		
		// Initialize the HashMap
		tickerToCryptoInfo = new HashMap<String, Crypto>();
		// Initialize the top 100 tinkers
		top100Tickers = new String[100];
		
		// Parse the line into individual cryptos
		String[] rawInfo = line.split("},");
		
		// Special case where the last data point doesn't have a comma after the brace
		rawInfo[99] = rawInfo[99].substring(0, rawInfo[99].length()-1);
		
		// Format each raw crypto
		for(int i = 0; i < rawInfo.length; i++) {
			// Remove the curly braces from the start of the current crypto info
			rawInfo[i] = rawInfo[i].substring(1);
			
			// Parse the info by the comma and assign variables to each "raw" representation of these values
			String[] formattedInfo = rawInfo[i].split(",\"");
			// Formatted Rank
			formattedInfo[0] = formattedInfo[0].replaceAll("\"", "").substring("rank:".length());
			// Formatted Tinker
			formattedInfo[1] = formattedInfo[1].replaceAll("\"", "").substring("ticker:".length());
			// Formatted Name
			formattedInfo[2] = formattedInfo[2].replaceAll("\"", "").substring("name:".length());
			// Formatted mentions
			formattedInfo[3] = formattedInfo[3].replaceAll("\"", "").substring("mentions:".length());
			// Formatted upvotes
			formattedInfo[4] = formattedInfo[4].replaceAll("\"", "").substring("upvotes:".length());
			// Formatted rank 24 hours ago
			formattedInfo[5] = formattedInfo[5].replaceAll("\"", "").substring("rank_24h_ago:".length());
			// Formatted mentions 24 hours ago
			formattedInfo[6] = formattedInfo[6].replaceAll("\"", "").substring("mentions_24h_ago:".length());
			
			// Special case where any of these values are zero (API represents 0 as null)
			for(int j = 0; j <= 6; j++) {
				if(formattedInfo[j].equals("null")) {
					formattedInfo[j] = "0";
				}
			}
			
			// Instantiate all of the variables that will be used to create the current crypto object
			byte currRank;
			String currTicker, currName;
			int currRank24h;
			long currMentions, currUpvotes, currMentions24h;
			
			// Parse all of the info into their respective data types
			currRank = Byte.parseByte(formattedInfo[0]);
			currTicker = formattedInfo[1];
			currName = formattedInfo[2];
			currMentions = Long.parseLong(formattedInfo[3]);
			currUpvotes = Long.parseLong(formattedInfo[4]);
			currRank24h = Integer.parseInt(formattedInfo[5]);
			currMentions24h = Long.parseLong(formattedInfo[6]);			
			
			// Create the current Crypto object
			Crypto currCrypto = new Crypto(currRank, currTicker, currName, currMentions, currUpvotes, currRank24h, currMentions24h);
			
			// Map the current ticker to the current Crypto object
			tickerToCryptoInfo.put(currTicker, currCrypto);
			
			// Assign the ith top 100 ticker
			top100Tickers[i] = currTicker;
		}
		
		// Close the BufferedReader
		file.close();
	}
	
	/**
	 * Returns the HashMap of Ticker to Crypto matchings
	 * @return	A HashMap of ticker to Crypto matchings
	 */
	public HashMap<String, Crypto> getTickerToCryptoInfo() {
		return this.tickerToCryptoInfo;
	}
	
	/**
	 * Returns the top 100 tickers for the 
	 * top 100 Cryptocurencies according to Reddit
	 * @return	A list of the top 100 tickers
	 */
	public String[] getTop100Tickers() {
		return this.top100Tickers;
	}
}
