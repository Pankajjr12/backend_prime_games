package com.kumar.gamesstore.services;

import java.util.List;

import com.kumar.gamesstore.modals.Deal;

public interface DealService  {

	   Deal createDeal(Deal deal);
//	    List<Deal> createDeals(List<Deal> deals);
	    List<Deal> getDeals();
	    Deal updateDeal(Deal deal,Long id) throws Exception;
	    void deleteDeal(Long id) throws Exception;
}
