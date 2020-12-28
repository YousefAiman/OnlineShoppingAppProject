package iug.project.onlineshoppingappproject;

import iug.project.onlineshoppingappproject.Views.LoginActivityViewInterface;
import iug.project.onlineshoppingappproject.Views.MainActivityViewInterface;

public class MainActivityPresenter implements iug.project.onlineshoppingappproject.Presenters.MainActivityPresenter {
    private MainActivityViewInterface view;

    protected MainActivityPresenter(MainActivityViewInterface mainActivityViewInterface) {
        this.view = mainActivityViewInterface;
    }


    @Override
    public void checkFirstTimeVisitor() {

    }
}
