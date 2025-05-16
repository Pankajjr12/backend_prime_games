package com.kumar.gamesstore.serviceImpl;

import org.springframework.stereotype.Service;

import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.modals.SellerReport;
import com.kumar.gamesstore.repositories.SellerReportRepository;
import com.kumar.gamesstore.services.SellerReportService;

@Service
public class SellerReportServiceImpl implements SellerReportService {

    private final SellerReportRepository sellerReportRepository;

    public SellerReportServiceImpl(SellerReportRepository sellerReportRepository) {
        this.sellerReportRepository = sellerReportRepository;
    }

    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport report = sellerReportRepository.findBySellerId(seller.getId());
        if (report == null) {
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }
        return report;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {

        return sellerReportRepository.save(sellerReport);
    }

}
