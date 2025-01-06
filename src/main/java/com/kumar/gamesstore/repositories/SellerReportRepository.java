package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.SellerReport;

public interface SellerReportRepository extends JpaRepository<SellerReport,Long> {
    SellerReport findBySellerId(Long sellerId);
}
