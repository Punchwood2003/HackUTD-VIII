package com.hackutd.viii.yahoofinance;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import yahoofinance.Stock;

@Getter
@With
@AllArgsConstructor
public class StockWrapper {
	private final Stock stock;
	private final LocalDateTime lastAccessed;
	
	public StockWrapper(final Stock stock) {
		this.stock = stock;
		this.lastAccessed = LocalDateTime.now();
	}
	
	public Stock getStock() {
		return this.stock;
	}
	
	public LocalDateTime getLastAccessed() {
		return this.lastAccessed;
	}
}
