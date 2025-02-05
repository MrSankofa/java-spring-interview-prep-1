package com.hackerrank.sample.dto;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class Product {
  private String item;
  private int price;
  private int discount;

  private String id;



  private int available;
  private String category;
  private String barcode;


  public URI getLocationURI() {
    return locationURI;
  }

  public void setLocationURI(URI locationURI) {
    this.locationURI = locationURI;
  }

  private URI locationURI;

  public Product(String item, int price, int discount, int available, String category, String barcode) {
    this.item = item;
    this.price = price;
    this.available = available;
    this.discount = discount;
    this.category = category;
    this.barcode = barcode;
  }

  public Product() {}

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getDiscount() {
    return discount;
  }

  public void setDiscount(int discount) {
    this.discount = discount;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }

  public void setAvailable(int available) {
    this.available = available;
  }

  public int getAvailable() {
    return available;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void generateLocationURI() {
    try {
      locationURI = new URI(
          ServletUriComponentsBuilder.fromCurrentContextPath()
              .path("/product/")
              .path(getId())
              .toUriString()
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
