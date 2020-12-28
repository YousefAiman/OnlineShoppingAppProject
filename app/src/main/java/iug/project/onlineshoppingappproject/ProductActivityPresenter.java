package iug.project.onlineshoppingappproject;

import java.util.List;

import iug.project.onlineshoppingappproject.Views.ProductActivityViewInterface;

public class ProductActivityPresenter implements iug.project.onlineshoppingappproject.Presenters.ProductActivityPresenter {
    ProductActivityViewInterface view;

    public ProductActivityPresenter(ProductActivityViewInterface productActivityViewInterface) {
        this.view = productActivityViewInterface;
    }

    @Override
    public void checkImages(List<String> images) {
        if (images != null){
            if (images.isEmpty()){
                view.printNoImagesError();
            } else {
                view.createImagesViewPager();
            }
        } else {
            view.printNoImagesError();
        }
    }
}
