package com.jsp.Agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.entity.TransactionHistory;
import com.jsp.Agro.repo.TransactionHistoryRepo;

@Repository
public class TransactionHistoryDao {
	
	@Autowired
	TransactionHistoryRepo repo;
	
	public TransactionHistory save(TransactionHistory history) {
		return repo.save(history);
	}
	public TransactionHistory fetchById(int id) {
		Optional<TransactionHistory> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	
}
