package com.jsp.Agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.entity.Image;
import com.jsp.Agro.repo.ImageRepo;

@Repository
public class ImageDao {
	
	@Autowired
	ImageRepo repo;
	
//	To SAVE The Image
	public Image saveImage(Image image) {
		
			return repo.save(image);
	}
	
//	To Update The Image the Existing Image
	public Image updateImage(Image image) {
		Optional<Image> db = repo.findById(image.getId());
		if(db.isPresent())
			return repo.save(image);
		return null;
	}

//	To DELETE The Image
	public Image deleteImage(int id) {
		Optional<Image> opt = repo.findById(id);
		if(opt.isPresent())
			{
			repo.delete(opt.get());
			return opt.get();
			}
		return null;
	}
	
//	To GET The Image
	public Image getImage(int id) {
		Optional<Image> opt = repo.findById(id);
		if(opt.isPresent())
			return opt.get();
		return null;
	}
}
