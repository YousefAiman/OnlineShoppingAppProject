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
    public void checkUserRegistrationInput(String email, String username, String password, String confirmPassword) {

    }
}
