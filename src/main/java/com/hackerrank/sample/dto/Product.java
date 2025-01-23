package com.hackerrank.sample.dto;

public class Product {
  private String item;
  private int price;
  private int discount;
  private int available;
  private String category;
  private String barcode;

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
}
