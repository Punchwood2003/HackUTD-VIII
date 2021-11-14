package com.hackutd.viii.yahoofinance;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.stereotype.Service;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class RefreshService {
	private final Map<StockWrapper, Boolean> stocksToRefresh;
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static final Duration refreshPeriod = Duration.ofSeconds(15);
	
	public RefreshService() {
		this.stocksToRefresh = new HashMap<StockWrapper, Boolean>();
		this.setRefreshEveryMinute();
	}
	
	public boolean shouldRefresh(final StockWrapper stock) {
		if(!stocksToRefresh.containsKey(stock)) {
			stocksToRefresh.put(stock, false);
			return true;
		}
		return stocksToRefresh.get(stock);
	}
	
	public void setRefreshEveryMinute() {
		scheduler.scheduleAtFixedRate(() ->
			stocksToRefresh.forEach((stock, value) -> {
				if(stock.getLastAccessed().isBefore(LocalDateTime.now().minus(this.refreshPeriod))) {
					System.out.println("Setting should refresh " + stock.getStock().getSymbol());
					stocksToRefresh.remove(stock);
					stocksToRefresh.put(stock.withLastAccessed(LocalDateTime.now()), true);
				}
			}), 0, 15, SECONDS);
	}
}
