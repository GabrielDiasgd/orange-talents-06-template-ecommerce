package br.com.zupacademy.gabriel.ecommerce.products.image;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImageRequest {
	
	@Size(min = 1)
	@NotNull
	private List<MultipartFile> images = new ArrayList<>();
	
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

}
