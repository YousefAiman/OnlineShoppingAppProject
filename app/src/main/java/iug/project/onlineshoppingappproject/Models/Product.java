package iug.project.onlineshoppingappproject.Models;

import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

  @PropertyName("productId")
  private String productId;
  @PropertyName("name")
  private String name;
  @PropertyName("imageUrls")
  private ArrayList<String> imageUrls;
  @PropertyName("price")
  private long price;
  @PropertyName("publishTime")
  private long publishTime;
  @PropertyName("description")
  private String description;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public long getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(long publishTime) {
    this.publishTime = publishTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ArrayList<String> getImageUrls() {
    return imageUrls;
  }

  public void setImageUrls(ArrayList<String> imageUrls) {
    this.imageUrls = imageUrls;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }
}
