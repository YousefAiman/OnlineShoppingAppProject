package iug.project.onlineshoppingappproject.Models;

import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

  @PropertyName("username")
  private String username;
  @PropertyName("imageUrl")
  private String imageUrl;
  @PropertyName("cart")
  private List<String> cart;
  @PropertyName("myPromos")
  private List<String> myPromos;


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


  public List<String> getCart() {
    return cart;
  }

  public void setCart(List<String> cart) {
    this.cart = cart;
  }

  public List<String> getMyPromos() {
    return myPromos;
  }

  public void setMyPromos(List<String> myPromos) {
    this.myPromos = myPromos;
  }
}
