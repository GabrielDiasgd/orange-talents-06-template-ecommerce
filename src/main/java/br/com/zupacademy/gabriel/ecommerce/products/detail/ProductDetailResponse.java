package br.com.zupacademy.gabriel.ecommerce.products.detail;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.products.characteristics.CharacteristicResponse;
import br.com.zupacademy.gabriel.ecommerce.products.review.ReviewResponse;

public class ProductDetailResponse {
	
	private String name;
	private BigDecimal price;
	private String description;
	private Integer avaliableStock;
	private Set<CharacteristicResponse> characteristics = new HashSet<>();
	private Set<String> imageLinks = new HashSet<>();
	private Set<String> questions = new HashSet<>();
	private Set<ReviewResponse> reviews = new HashSet<>();
	private Double averageRating;
	private Long totalReviews;
	
	public ProductDetailResponse(Product product) {
		this.name = product.getName();
		this.price = product.getPrice();
		this.description = product.getDescription();
		this.avaliableStock = product.getQuantity();
		this.characteristics = product.getCharacteristics().stream().map(c -> new CharacteristicResponse(c)).collect(Collectors.toSet());
		this.imageLinks = product.getImages().stream().map(link -> link.getLink()).collect(Collectors.toSet());
		this.questions = product.getQuestions().stream().map(question -> question.getTitle()).collect(Collectors.toSet());
		this.reviews = product.getReviews().stream().map(review -> new ReviewResponse(review)).collect(Collectors.toSet());
		this.averageRating = product.produtcAverageRating();
		this.totalReviews = product.totalNumberRating();
	}
		
		public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	
	public Integer getAvaliableStock() {
		return avaliableStock;
	}
	
	public Set<CharacteristicResponse> getCharacteristics() {
		return characteristics;
	}

	public Set<String> getImageLinks() {
		return imageLinks;
	}
	
	public Set<String> getQuestions() {
		return questions;
	}
	
	 public Set<ReviewResponse> getReviews() {
		return reviews;
	}
	
	public Double getAverageRating() {
		return averageRating;
	}
	 public Long getTotalReviews() {
		return totalReviews;
	}
}
