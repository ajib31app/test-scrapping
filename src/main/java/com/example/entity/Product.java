package com.example.entity;

public class Product {

    private String name;
    private String description;
    private String imageLink;
    private String price;
    private String rating;
    private String merchant;

    public Product(String name, String description, String imageLink, String price, String rating, String merchant) {
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.price = price;
        this.rating = rating;
        this.merchant = merchant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String toString(){
        return "name : " + name + ", description : " + description + ", imageLink : " + imageLink + ", price : " + price + ", rating : " + rating + ", merchant : " + merchant;
    }
}
