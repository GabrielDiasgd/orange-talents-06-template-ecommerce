package br.com.zupacademy.gabriel.ecommerce.services.images;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {

	public Set<String> send (List<MultipartFile> images);
}
