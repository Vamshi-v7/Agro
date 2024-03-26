package com.jsp.Agro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agro.dao.EquipmentDao;
import com.jsp.Agro.dao.RentalDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Equipment;
import com.jsp.Agro.entity.Rental;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exceptions.IdNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class RentalService {
	
	@Autowired
	RentalDao rdao;
	
	@Autowired
	EquipmentDao edao;
	
	@Autowired
	UserDao uDao;
	
	public ResponseEntity<ResponseStructure<Rental>> save(Rental rent,int uId,int eId){
		ResponseStructure<Rental> rs=new ResponseStructure<>();
		User user = uDao.fetchById(uId);
		if(user!=null) {
			rent.setUser(user);
			
			Equipment eq = edao.fetchById(eId);
			if(eq!=null) {
				rent.setEquipment(eq);
				if(eq.getQuantity()>=rent.getQuantity())
				  {
					int Qty=eq.getQuantity()-rent.getQuantity();
					eq.setQuantity(Qty);
					edao.upadte(eq);
				  }
				else{
					throw new IdNotFoundException("Unavailable Quantity...");
				}
			}
			else
				throw new IdNotFoundException("Equipmnet doesnt Exist with ID:"+eId);
			
			
			
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg("Rental Object created Successfully");
			rs.setData(rdao.save(rent));
			
			
			return new ResponseEntity<ResponseStructure<Rental>>(rs,HttpStatus.CREATED);
		}
		throw new IdNotFoundException("User Doesn't exists with ID:"+uId);
	}
	
	public ResponseEntity<ResponseStructure<Rental>> fetchById(int id){
		ResponseStructure<Rental> rs=new ResponseStructure<>();
		 Rental rent = rdao.fetchById(id);
		if(rent!=null) {
			
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setMsg("Rental Object created Successfully");
			rs.setData(rent);
			
			return new ResponseEntity<ResponseStructure<Rental>>(rs,HttpStatus.CREATED);
		}
		throw new IdNotFoundException("Rental Doesn't exists with ID:"+id);
	}
	
}
