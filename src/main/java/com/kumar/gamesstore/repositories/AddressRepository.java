package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
