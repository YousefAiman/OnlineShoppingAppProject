package iug.project.onlineshoppingappproject;

import iug.project.onlineshoppingappproject.Views.ProductActivityViewInterface;

public class ProductActivityPresenter implements iug.project.onlineshoppingappproject.Presenters.ProductActivityPresenter {
    ProductActivityViewInterface view;

    public ProductActivityPresenter(ProductActivityViewInterface productActivityViewInterface) {
        this.view = productActivityViewInterface;
    }

    @Override
    public void checkImages() {

    }
}
