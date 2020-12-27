package iug.project.onlineshoppingappproject;

import iug.project.onlineshoppingappproject.Views.LoginActivityViewInterface;

public class LoginActivityPresenter implements iug.project.onlineshoppingappproject.Presenters.LoginActivityPresenter {
    private LoginActivityViewInterface view;

    protected LoginActivityPresenter(LoginActivityViewInterface loginActivityViewInterface) {
        this.view = loginActivityViewInterface;
    }

    @Override
    public void loginUser(String email, String password) {

    }
}
