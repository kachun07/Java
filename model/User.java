package model;

import dao.CartDAO;

public class User {
	private int id;
	private String name;
	private String password;
	private String address;
	private String email;
	private Cart cart;

	public User(int id, String name, String password, String address,
			String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
	}

	public User(String name, String password, String address, String email) {
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cart getCart() {
		Cart loadedCart = null;
		if (cart != null) {
			return cart;
		} else {
			loadedCart = CartDAO.instance.getFromUserId(this.id);
		}
		return loadedCart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
