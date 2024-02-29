package com.jsp.Agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agro.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
