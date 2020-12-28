package iug.project.onlineshoppingappproject.Models;

import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

public class User implements Serializable {

  @PropertyName("username")
  private String username;
  @PropertyName("imageUrl")
  private String imageUrl;
  @PropertyName("cart")
  private String[] cart;
  @PropertyName("myPromos")
  private String[] myPromos;


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String[] getCart() {
    return cart;
  }

  public void setCart(String[] cart) {
    this.cart = cart;
  }

  public String[] getMyPromos() {
    return myPromos;
  }

  public void setMyPromos(String[] myPromos) {
    this.myPromos = myPromos;
  }
}
