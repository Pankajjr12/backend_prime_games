package com.kumar.gamesstore.services;

import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.modals.SellerReport;

public interface SellerReportService {

	
	 SellerReport getSellerReport(Seller seller);
	 SellerReport updateSellerReport(SellerReport sellerReport);
	
}
