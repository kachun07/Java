package model;

public class Product {
	private int id;
	private String name;
	private String artist;
	private int year;
	private String genre;
	private String origin;
	private double price;

	public Product(int id, String artist, String name, int year, String genre,
			String origin, double price) {
		this.id = id;
		this.artist = artist;
		this.name = name;
		this.year = year;
		this.genre = genre;
		this.origin = origin;
		this.price = price;
	}

	public Product(String artist, String name, int year, String genre,
			String origin, double price) {
		this.artist = artist;
		this.name = name;
		this.year = year;
		this.genre = genre;
		this.origin = origin;
		this.price = price;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
