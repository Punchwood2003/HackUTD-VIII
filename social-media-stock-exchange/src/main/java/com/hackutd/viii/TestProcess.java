package com.hackutd.viii;

import java.io.IOException;

public class TestProcess
{
	@SuppressWarnings("unused")
	public static void main(String[]args) throws IOException
	{
		
		
		
	}
	
	// functions for 8 pillars
	
	public double getPE(double[] pricesPerShare, double[] earningsPerShare)
	{
		double sumOfShares = 0;
		double sumOfEarnings = 0;
		for(double share : pricesPerShare)
			sumOfShares += share;
		sumOfShares /= 5;
		for(double earning : earningsPerShare)
			sumOfEarnings += earning;
		sumOfEarnings /= 5;
		return sumOfShares/sumOfEarnings;
	}
	
	public double getROIC(double income, double dividends, double totalCapital)
	{
		return (income-dividends)/totalCapital;
	}
	
	public boolean hasRevGrow(double currentRev, double fiveYearsAgoRev)
	{
		if(currentRev > fiveYearsAgoRev)
			return true;
		return false;
	}
	
	public boolean hasNetIncomeGrow(double currentRev, double currentExp, double fiveYearsAgoRev, double fiveYearsAgoExp)
	{
		if((currentRev-currentExp) > (fiveYearsAgoRev - fiveYearsAgoExp))
			return true;
		return false;
	}
	
	public boolean getSharesOutstanding(double currentShares, double fiveYearsAgoShares)
	{
		if(currentShares > fiveYearsAgoShares)
			return true;
		return false;
	}
	
	public double getLTL(double totalLongtermLiabilities, double[] freeCashFlow)
	{
		double sumOfFCF = 0;
		for(double fcf : freeCashFlow)
			sumOfFCF += fcf;
		return totalLongtermLiabilities/sumOfFCF;
	}
	
	public boolean hasCashFlowGrow(double currentCashFlow, double fiveYearsAgoCashFlow)
	{
		if(currentCashFlow > fiveYearsAgoCashFlow)
			return true;
		return false;
	}
	
	public double getPriceToCashFlow(double[] priceShares, double[] cashFlows)
	{
		double sumOfShares = 0;
		double sumOfCash = 0;
		for(double share : priceShares)
			sumOfShares += share;
		sumOfShares /= 5;
		for(double cash : cashFlows)
			sumOfCash += cash;
		sumOfCash /= 5;
		return sumOfShares/sumOfCash;
	}
	
}
