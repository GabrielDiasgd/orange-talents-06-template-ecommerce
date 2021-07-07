package br.com.zupacademy.gabriel.ecommerce.products.review;

public class ReviewResponse {

	private Integer rating;
	private String title;
	private String description;
	
	
	public ReviewResponse(Review review) {
		this.rating = review.getRating();
		this.title = review.getTitle();
		this.description = review.getDescription();
	}
	public Integer getRating() {
		return rating;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}

	
	
}
