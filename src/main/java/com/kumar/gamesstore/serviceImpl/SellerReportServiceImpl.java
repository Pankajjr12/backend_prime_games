package com.kumar.gamesstore.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.modals.SellerReport;
import com.kumar.gamesstore.repositories.SellerReportRepository;
import com.kumar.gamesstore.services.SellerReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService{
	
	@Autowired
	private final SellerReportRepository sellerReportRepository;

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
