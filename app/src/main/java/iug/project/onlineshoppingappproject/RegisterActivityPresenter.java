package iug.project.onlineshoppingappproject;

public class RegisterActivityPresenter implements
        iug.project.onlineshoppingappproject.Presenters.RegisterActivityPresenter {

  private RegisterActivityViewInterface view;

  protected RegisterActivityPresenter(RegisterActivityViewInterface registerActivityViewInterface){
    this.view = registerActivityViewInterface;
  }

  @Override
  public void registerUser(String email, String username, String password, String confirmPassword) {



  }
}
