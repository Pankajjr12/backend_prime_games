package com.kumar.gamesstore.services;

import java.util.List;

import com.kumar.gamesstore.dto.ReviewChart;

public interface RevenueService {

	
	 List<ReviewChart> getDailyRevenueForChart(int days, Long sellerId);
	    
	 List<ReviewChart> getMonthlyRevenueForChart(int months,Long sellerId);
	    
	 List<ReviewChart> getYearlyRevenueForChart(int years,Long sellerId);
	    
	 List<ReviewChart> getHourlyRevenueForChart(Long sellerId);
	    
	 List<ReviewChart> getRevenueChartByType(String type,Long sellerId);
}
