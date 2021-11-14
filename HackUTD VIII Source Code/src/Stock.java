/**
 * This class handles all of the important information about 
 * any given stock. This includes information like the rank
 * of that stock according to Reddit, the stock's ticker, the 
 * full name of the company, the current mentions and the mentions 
 * 24 hours ago, and the current upvotes and the upvotes 24 hours ago.
 * @author MatthewSheldon
 */
public class Stock implements Comparable<Stock> {
	/**
	 * The rank of the Stock
	 */
	private byte rank; 
	
	/**
	 * The ticker of the Stock
	 */
	private String ticker;
	
	/**
	 * The full name of the Stock
	 */
	private String name;
	
	/**
	 * The number of mentions of the Stock
	 */
	private long mentions;
	
	/**
	 * The number of upvotes of the Stock
	 */
	private long upvotes;
	
	/**
	 * The rank of the Stock 24 hours ago
	 */
	private int rank_24h_ago;
	
	/**
	 * The number of mentions of the Stock 24 hours ago
	 */
	private long mentions_24h_ago;
	
	/**
	 * Constructs a Stock object with the passed parameters
	 * @param rank			The rank on Reddit
	 * @param ticker		The ticker
	 * @param name			The full name
	 * @param mentions		The number of mentions on Reddit
	 * @param upvotes		The number of upvotes on Reddit
	 * @param rank24h		The rank on Reddit 24 hours ago
	 * @param mentions24h	The number of mentions on Reddit 24 hours ago
	 */
	public Stock(byte rank, String ticker, String name, long mentions, long upvotes, int rank24h, long mentions24h) {
		this.rank = rank;
		this.ticker = ticker;
		this.name = name;
		this.mentions = mentions;
		this.upvotes = upvotes;
		this.rank_24h_ago = rank24h;
		this.mentions_24h_ago = mentions24h;
	}

	/**
	 * @return the rank
	 */
	public byte getRank() {
		return rank;
	}

	/**
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the mentions
	 */
	public long getMentions() {
		return mentions;
	}

	/**
	 * @return the upvotes
	 */
	public long getUpvotes() {
		return upvotes;
	}

	/**
	 * @return the rank 24 hours ago
	 */
	public int getRank_24h_ago() {
		return rank_24h_ago;
	}

	/**
	 * @return the mentions 24 hours ago
	 */
	public long getMentions_24h_ago() {
		return mentions_24h_ago;
	}
	
	@Override
	public int compareTo(Stock other) {
		return Byte.compare(this.rank, other.rank);
	}
	
	@Override
	public String toString() {
		String output = "";
		output += "Name:\t\t\t" + this.name;
		output += "\nTicker:\t\t\t" + this.ticker;
		output += "\nRank:\t\t\t" + this.rank;
		output += "\nRank 24 Hours Ago:\t" + this.rank_24h_ago;
		output += "\nMentions:\t\t" + this.mentions;
		output += "\nMentions 24 Hours Ago:\t" + this.mentions_24h_ago;
		output += "\nUpvotes:\t\t" + this.upvotes;
		return output;
	}
}
