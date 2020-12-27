package iug.project.onlineshoppingappproject;

import com.google.firebase.auth.FirebaseAuth;

import iug.project.onlineshoppingappproject.Views.RegisterActivityViewInterface;

public class RegisterActivityPresenter implements
        iug.project.onlineshoppingappproject.Presenters.RegisterActivityPresenter {

    private RegisterActivityViewInterface view;

    protected RegisterActivityPresenter(RegisterActivityViewInterface registerActivityViewInterface) {
        this.view = registerActivityViewInterface;
    }

    @Override
    public void registerUser(String email, String username, String password, String confirmPassword) {
    if(email.trim().isEmpty()){
//      view.printErrorMessage("Email field is empty");
      view.printErrorMessage("Registration Error");
    }else if(username.trim().isEmpty()){
//      view.printErrorMessage("Username field is empty");
      view.printErrorMessage("Registration Error");
    }else if(password.trim().isEmpty()){
//      view.printErrorMessage("Password field is empty");
      view.printErrorMessage("Registration Error");
    }else if(confirmPassword.trim().isEmpty()){
//      view.printErrorMessage("Confirm Password field is empty");
      view.printErrorMessage("Registration Error");
    }else{
      if(!password.equals(confirmPassword)){
//        view.printErrorMessage("Password doesn't match");
        view.printErrorMessage("Registration Error");
      }else{
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(authResult ->
                        view.startHomeActivity())
                .addOnFailureListener(e ->
//                        view.printErrorMessage("Registration Failed: "+e.getMessage())
                        view.printErrorMessage("Registration Error")
                );
      }
    }

    }
}
