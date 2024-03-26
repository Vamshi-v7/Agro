package com.jsp.Agro.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro.dao.ImageDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Image;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exceptions.IdNotFoundException;
import com.jsp.Agro.exceptions.ImageNotFound;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class ImageService {
	
	@Autowired
	ImageDao iDao;
	
	@Autowired
	UserDao uDao;
	
	public ResponseEntity<ResponseStructure<Image>> editImage(MultipartFile file,int id) throws IOException{
		ResponseStructure<Image> rs=new ResponseStructure<>();
		User user = uDao.fetchById(id);
		if(user!=null) {
			Image uImg = user.getImage();

			if(uImg!=null) {
				uImg.setName(file.getOriginalFilename());
				uImg.setImgData(file.getBytes());
				iDao.updateImage(uImg);
				
				rs.setStatus(HttpStatus.FOUND.value());
				rs.setMsg("Image updated Successfully....:)");
				rs.setData(uImg);
				
				return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.FOUND);
			}
			else {
			Image img=new Image();
				img.setName(file.getOriginalFilename());
				img.setImgData(file.getBytes());
			Image dbImg = iDao.saveImage(img);
					rs.setStatus(HttpStatus.CREATED.value());
					rs.setMsg("Image Created Successfully....:)");
					rs.setData(dbImg);
			
					user.setImage(img);
					uDao.updateUser(user);
				
				return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.CREATED);
			}
		}
		throw new IdNotFoundException("User Doesn't Exist with given Id : "+id);
	}
	
	public ResponseEntity<byte[]> fetchImage(int id){
		
		 Image img = iDao.getImage(id);
		if(img!=null) {
			
		        byte[] imageBytes = img.getImgData();
		        
		      // Set appropriate content type (e.g., image/jpeg)
		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.IMAGE_JPEG);
		        
			return new ResponseEntity<byte[]>(imageBytes,headers,HttpStatus.FOUND);
		}
		throw new ImageNotFound("Image Doesn't Exist with given Id : "+id);
	}
	
	public ResponseEntity<ResponseStructure<Image>> deleteImage(int id){
		ResponseStructure<Image> rs=new ResponseStructure<>();
		Image img = iDao.getImage(id);
		if(img!=null) {
			User user = uDao.fetchByImage(img);
			if(user!=null) {
				user.setImage(null);
			}
			
			rs.setStatus(HttpStatus.OK.value());
			rs.setMsg("Image Deleted Successfully....:)");
			rs.setData(iDao.deleteImage(id));
			
			return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.OK);
		}
		throw new ImageNotFound("Image Doesn't Exist with given Id : "+id);
	}
	
	
	
}
