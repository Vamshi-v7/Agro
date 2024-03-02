package com.jsp.Agro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.entity.Address;
import com.jsp.Agro.entity.Post;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.repo.UserRepo;

@Repository
public class UserDao {

	@Autowired
	UserRepo repo;
	
	@Autowired
	Addressdao adr;
	
//1	Register User
	public User register(User user) {
		 Optional<User> db = repo.fetchByEmail(user.getEmail());
		if(db.isEmpty()){
//			 adr.saveAddress(user.getAddress());
			return repo.save(user);
			}
		return null;
	}
	
//2	FetchByEmail
	public User fetchByEmail(String email) {
		Optional<User> db = repo.fetchByEmail(email);
		if(db.isPresent())
			return db.get();
		return null;
	}
	
//3	FetchById
	public User fetchById(int id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent())
			return db.get();
		return null;
	}
	
//4	UpdateById 
	public User updateUser(User user) {
		Optional<User> opt = repo.findById(user.getId());
		
		if(opt.isPresent())
		{
			User udb = opt.get();
			
			if(user.getFirstName()!=null)
				udb.setFirstName(user.getFirstName());
			if(user.getLastName()!=null)
				udb.setLastName(user.getLastName());
			if(user.getEmail()!=null)
				udb.setEmail(user.getEmail());
			if(user.getPhone()!=0)
				udb.setPhone(user.getPhone());
			if(user.getPassword()!=null)
				udb.setPassword(user.getPassword());
			if(user.getAddress()!=null) {
				Address addr = user.getAddress();
				Address dbAddr = udb.getAddress();
					if(addr.getHouse()!=null)
						dbAddr.setHouse(addr.getHouse());
					if(addr.getLandMark()!=null)
						dbAddr.setLandMark(addr.getLandMark());
					if(addr.getStreet()!=null)
						dbAddr.setStreet(addr.getStreet());
					if(addr.getMandal()!=null)
						dbAddr.setMandal(addr.getMandal());
					if(addr.getDistrict()!=null)
						dbAddr.setDistrict(addr.getDistrict());
					if(addr.getPinCode()!=0)
						dbAddr.setPinCode(addr.getPinCode());
					if(addr.getState()!=null)
						dbAddr.setState(addr.getState());
				udb.setAddress(dbAddr);
			}
			if(user.getAge()!=0)
				udb.setAge(user.getAge());
			if(user.getType()!=null)
				udb.setType(user.getType());
			if(user.getGender()!=null)
				udb.setGender(user.getGender());
			if(user.getPost()!=null) {
				
				List<Post> post = user.getPost();
				List<Post> dbPost = udb.getPost();
				
				for (Post p : post) {
					Post p1=new Post();
					if(p.getLocation()!=null)
						p1.setLocation(p.getLocation());
					if(p.getCaption()!=null)
						p1.setCaption(p.getCaption());
					dbPost.add(p1);
				}
				
				udb.setPost(dbPost);
			}
			if(user.getImage()!=null)
				udb.setImage(user.getImage());
			
			return repo.save(udb);
		}
		return null;
	}
	
//5	Forgot password: Update Password By Email
	public User modifyUser(String email,String password) {
		Optional<User> db = repo.fetchByEmail(email);
		if(db.isPresent())
		{	User user = db.get();
			user.setPassword(password);
			return repo.save(user);
		}
		return null;
	}
	
//6	DeleteById
	public User deleteUser(int id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent())
		  {
			repo.deleteById(id);
			return db.get();
			}
		return null;
	}
	
//7	DeleteByEmail
	public User removeUser(String email) {
		Optional<User> db = repo.fetchByEmail(email);
		if(db.isPresent())
			repo.deleteByEmail(email);
		return null;
	}
	
//8	FetchAll
	public List<User> fetchAll() {
		List<User> db = repo.findAll();
		return db; 
	}
	
}
