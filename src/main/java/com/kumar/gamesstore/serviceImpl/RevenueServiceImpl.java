package com.kumar.gamesstore.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kumar.gamesstore.dto.ReviewChart;
import com.kumar.gamesstore.modals.Order;
import com.kumar.gamesstore.repositories.OrderRepository;
import com.kumar.gamesstore.services.RevenueService;

@Service

public class RevenueServiceImpl implements RevenueService {

    private final OrderRepository orderRepository;

    public RevenueServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<ReviewChart> getDailyRevenueForChart(int days, Long sellerId) {
        List<ReviewChart> revenueData = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        for (int i = days - 1; i >= 0; i--) {
            LocalDate date = currentDate.minusDays(i);
            double dailyRevenue = orderRepository
                    .findBySellerIdAndOrderDateBetween(sellerId, date.atStartOfDay(), date.plusDays(1).atStartOfDay())
                    .stream()
                    .mapToDouble(Order::getTotalSellingPrice)
                    .sum();

            ReviewChart revenueChart = new ReviewChart();
            revenueChart.setRevenue(dailyRevenue);
            revenueChart.setDate(date.toString());

            revenueData.add(revenueChart);
        }

        return revenueData;
    }

    @Override
    public List<ReviewChart> getMonthlyRevenueForChart(int months, Long sellerId) {
        List<ReviewChart> revenueData = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (int i = months - 1; i >= 0; i--) {
            LocalDate date = currentDate.minusMonths(i);
            LocalDate startOfMonth = date.withDayOfMonth(1);
            LocalDate startOfNextMonth = startOfMonth.plusMonths(1);

            double monthlyRevenue = orderRepository
                    .findBySellerIdAndOrderDateBetween(sellerId, startOfMonth.atStartOfDay(), startOfNextMonth.atStartOfDay())
                    .stream()
                    .mapToDouble(Order::getTotalSellingPrice)
                    .sum();

            ReviewChart revenueChart = new ReviewChart();
            revenueChart.setRevenue(monthlyRevenue);
            revenueChart.setDate(date.getYear() + "-" + String.format("%02d", date.getMonthValue()));

            revenueData.add(revenueChart);
        }
        return revenueData;
    }

    @Override
    public List<ReviewChart> getYearlyRevenueForChart(int years, Long sellerId) {

        List<ReviewChart> revenueData = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (int i = years - 1; i >= 0; i--) {
            LocalDate startOfYear = currentDate.minusYears(i).withDayOfYear(1);
            LocalDate startOfNextYear = startOfYear.plusYears(1);

            double yearlyRevenue = orderRepository
                    .findBySellerIdAndOrderDateBetween(sellerId, startOfYear.atStartOfDay(), startOfNextYear.atStartOfDay())
                    .stream()
                    .mapToDouble(Order::getTotalSellingPrice)
                    .sum();

            ReviewChart revenueChart = new ReviewChart();
            revenueChart.setRevenue(yearlyRevenue);
            revenueChart.setDate(String.valueOf(startOfYear.getYear()));
            revenueData.add(revenueChart);
        }

        return revenueData;
    }

    @Override
    public List<ReviewChart> getHourlyRevenueForChart(Long sellerId) {

        List<ReviewChart> revenueData = new ArrayList<>();

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the start of the day (12 AM, or 00:00) and loop through 24 hours
        LocalDateTime startOfDay = currentDate.atStartOfDay(); // 12 AM (00:00) of the current day

        // Loop through each hour of the day from 12 AM to 11:59 PM
        for (int i = 0; i < 24; i++) {
            LocalDateTime startOfHour = startOfDay.plusHours(i);
            LocalDateTime startOfNextHour = startOfHour.plusHours(1); // Next hour boundary

            // Calculate revenue for this hour (from startOfHour to startOfNextHour)
            double hourlyRevenue = orderRepository
                    .findBySellerIdAndOrderDateBetween(sellerId, startOfHour, startOfNextHour)
                    .stream()
                    .mapToDouble(Order::getTotalSellingPrice)
                    .sum();

            // Prepare the data for this hour
            ReviewChart revenueChart = new ReviewChart();
            revenueChart.setRevenue(hourlyRevenue);
            revenueChart.setDate(startOfHour.getHour() + ":00");  // Format as "HH:00" (e.g., "00:00", "01:00")

            // Add the hourly revenue data to the list
            revenueData.add(revenueChart);
        }

        return revenueData;
    }

    @Override
    public List<ReviewChart> getRevenueChartByType(String type, Long sellerId) {

        if (type.equals("monthly")) {
            return this.getMonthlyRevenueForChart(12, sellerId);
        } else if (type.equals("daily")) {
            return this.getDailyRevenueForChart(30, sellerId);
        } else {
            return this.getHourlyRevenueForChart(sellerId);
        }
    }

}
