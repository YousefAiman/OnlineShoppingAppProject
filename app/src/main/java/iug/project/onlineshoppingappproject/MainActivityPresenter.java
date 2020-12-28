package iug.project.onlineshoppingappproject;

import android.content.Context;
import android.content.SharedPreferences;

import iug.project.onlineshoppingappproject.Views.LoginActivityViewInterface;
import iug.project.onlineshoppingappproject.Views.MainActivityViewInterface;

public class MainActivityPresenter implements iug.project.onlineshoppingappproject.Presenters.MainActivityPresenter {
    private final MainActivityViewInterface view;
    private final SharedPreferences sharedPreferences;
    protected MainActivityPresenter(MainActivityViewInterface mainActivityViewInterface,SharedPreferences sharedPreferences) {
        this.view = mainActivityViewInterface;
        this.sharedPreferences = sharedPreferences;
    }


    @Override
    public void checkFirstTimeVisitor() {
        if(sharedPreferences.getBoolean("firstTime",false)){
            view.startWelcomeScreen();
        }else{
            view.startLoginScreen();
        }

    }
}
