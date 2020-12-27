package iug.project.onlineshoppingappproject;

import iug.project.onlineshoppingappproject.Views.LoginActivityViewInterface;

public class LoginActivityPresenter implements iug.project.onlineshoppingappproject.Presenters.LoginActivityPresenter {
    private LoginActivityViewInterface view;

    protected LoginActivityPresenter(LoginActivityViewInterface loginActivityViewInterface) {
        this.view = loginActivityViewInterface;
    }


    @Override
    public void checkLoginInfo(String email, String password) {
        if (email.trim().isEmpty()) {
            view.printErrorMessage("Email field is empty");
        } else if (password.trim().isEmpty()){
            view.printErrorMessage("Password field is empty");
        } else {
            if(password.length() < 6){
                view.printErrorMessage("Password is less than six characters");
            } else {
                view.startHomeActivity();
            }
        }
    }
}
