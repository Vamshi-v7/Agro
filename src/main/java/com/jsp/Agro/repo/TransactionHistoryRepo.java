package com.jsp.Agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agro.entity.TransactionHistory;

public interface TransactionHistoryRepo extends JpaRepository<TransactionHistory, Integer> {

}
