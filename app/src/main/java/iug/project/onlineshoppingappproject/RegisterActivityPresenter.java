package iug.project.onlineshoppingappproject;

import com.google.firebase.auth.FirebaseAuth;

import iug.project.onlineshoppingappproject.Views.RegisterActivityViewInterface;

public class RegisterActivityPresenter implements
        iug.project.onlineshoppingappproject.Presenters.RegisterActivityPresenter {

    private final RegisterActivityViewInterface view;

    public RegisterActivityPresenter(RegisterActivityViewInterface registerActivityViewInterface) {
        this.view = registerActivityViewInterface;
    }

    @Override
    public void checkUserRegistrationInput(String email, String username, String password, String confirmPassword) {
        if(email.trim().isEmpty()){
        view.printErrorMessage("Email field is empty");
        }else if(username.trim().isEmpty()){
        view.printErrorMessage("Username field is empty");
        }else if(password.trim().isEmpty()){
        view.printErrorMessage("Password field is empty");
        }else if(confirmPassword.trim().isEmpty()){
        view.printErrorMessage("Confirm Password field is empty");
        }else{

            if(password.length() < 6){
                view.printErrorMessage("Password is less than six characters");
            }else {
                if (!password.equals(confirmPassword)) {
                    view.printErrorMessage("Password doesn't match");
                } else {
                    view.printSuccessMessage("Registration Success");
                }
            }
        }

    }
}