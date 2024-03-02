package com.jsp.Agro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.entity.Address;
import com.jsp.Agro.repo.AddressRepo;

@Repository
public class Addressdao {

	@Autowired
	AddressRepo repo;
	
	//1	Register User
		public Address saveAddress(Address ad) {
				return repo.save(ad);
		}
		
		
	//2	FetchById
		public Address fetchById(int id) {
			Optional<Address> db = repo.findById(id);
			if(db.isPresent())
				return db.get();
			return null;
		}
		
	//3	UpdateById 
		public Address updateAddress(Address ad) {
			Optional<Address> db = repo.findById(ad.getId());
			Address uad = db.get();
			if(db.isPresent())
			{
				if(ad.getHouse()!=null)
					uad.setHouse(ad.getHouse());
				if(ad.getLandMark()!=null)
					uad.setLandMark(ad.getLandMark());
				if(ad.getStreet()!=null)
					uad.setStreet(ad.getStreet());
				if(ad.getMandal()!=null)
					uad.setMandal(ad.getMandal());
				if(ad.getDistrict()!=null)
					uad.setDistrict(ad.getDistrict());
				if(ad.getPinCode()!=0)
					uad.setPinCode(ad.getPinCode());
				if(ad.getState()!=null)
					uad.setState(ad.getState());
				
				return repo.save(uad);
			}
			return null;
		}
		
		
	//4	DeleteById
		public Address deleteAddress(int id) {
			Optional<Address> db = repo.findById(id);
			if(db.isPresent())
				repo.deleteById(id);
			return null;
		}
		
		
	//5	FetchAll
		public List<Address> fetchAll() {
			List<Address> db = repo.findAll();
			return db; 
		}
		
	}
