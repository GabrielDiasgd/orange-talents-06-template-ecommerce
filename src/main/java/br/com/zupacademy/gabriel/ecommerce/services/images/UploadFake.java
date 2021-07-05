package br.com.zupacademy.gabriel.ecommerce.services.images;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/*
 * Poderia anotar essa classe com @profile e informar dev
 * e em outra classe e informar prod, dependedo do perfil seria
 * seleciona uma das implementações
 */

@Component
public class UploadFake implements Uploader {
	
	@Override
	public Set<String> send(List<MultipartFile> images) {
	  Set<String> list = new HashSet<>();
		list = images.stream()
			 .map(image -> ("https://AmazonS3.com.br/" + image.getOriginalFilename()))
			 .collect(Collectors.toSet());
		return list;
	}
	
	
	
	

}
