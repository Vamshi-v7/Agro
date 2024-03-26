package com.jsp.Agro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agro.dao.RentalDao;
import com.jsp.Agro.dao.TransactionHistoryDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Rental;
import com.jsp.Agro.entity.TransactionHistory;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exceptions.IdNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class TransactionService {
	@Autowired
	TransactionHistoryDao tDao;
	
	@Autowired
	RentalDao rDao;
	
	@Autowired
	UserDao uDao;
	
	public ResponseEntity<ResponseStructure<TransactionHistory>> saveTransaction(int uId,String modeOfPay){
		ResponseStructure<TransactionHistory> rs=new ResponseStructure<>();
		User user = uDao.fetchById(uId);
		if(user!=null) {
			String msg="Bill Generated Successfully..[*-*]\nHeyy "+user.getFirstName()+", Here's Your Bill :";
			double amount=0;
			
			List<Rental> rents = rDao.fetchByUser(user);
			msg+="\n--------------------------------------------------------------------------\n";
			msg+="Item \t\t\t Qty \tTime(hr) \tCostP/hr \tamount";
			msg+="\n--------------------------------------------------------------------------\n";
			for (Rental rental : rents) {
				int hour= rental.getEnd().getHour()-rental.getStart().getHour();
				
				double min= rental.getEnd().getMinute()-rental.getStart().getMinute();
				
				double costPerHr=rental.getEquipment().getCostPHour();
				
				double cost=(hour+(min/60))*costPerHr*rental.getQuantity();
				int hrs=(int)(hour*60+min)/60;
				int mins=(int)(hour*60+min)%60;
				amount=amount+cost;
				msg+=rental.getEquipment().getName()+" \t\t "+rental.getQuantity()+"\t"+hrs+":"+mins+"min\t\t"+costPerHr+"\t\t"+cost+"\n";
			}
			msg+="\n--------------------------------------------------------------------------\n";
			msg+="TOTAL \t\t\t\t:\t\t\t\t"+amount;
			msg+="\n--------------------------------------------------------------------------\n";
			System.out.println(msg);
			TransactionHistory tr=new TransactionHistory();
			tr.setModeOfPay(modeOfPay);
			tr.setRents(rents);
			tr.setAmount(amount);
			
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg(msg);
			rs.setData(tDao.save(tr));
			
			return new ResponseEntity<ResponseStructure<TransactionHistory>>(rs,HttpStatus.CREATED);
			
		}
		throw new IdNotFoundException("User Not Found with ID:"+uId);
	}
	
	public ResponseEntity<ResponseStructure<TransactionHistory>> fetchById(int id){
		ResponseStructure<TransactionHistory> rs=new ResponseStructure<>();
		TransactionHistory transaction = tDao.fetchById(id);
	   if(transaction!=null) {
		   
			rs.setStatus(HttpStatus.FOUND.value());
			rs.setMsg("Here's Your Bill...!");
			rs.setData(transaction);
			
			return new ResponseEntity<ResponseStructure<TransactionHistory>>(rs,HttpStatus.CREATED);
			
		}
		throw new IdNotFoundException("Transaction Not Found with ID:"+id);
	}


}
