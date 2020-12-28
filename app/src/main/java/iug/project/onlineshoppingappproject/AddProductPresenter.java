package iug.project.onlineshoppingappproject;

import iug.project.onlineshoppingappproject.Views.AddProductViewInterface;

public class AddProductPresenter implements iug.project.onlineshoppingappproject.Presenters.AddProductPresenter {
    private AddProductViewInterface addProductView;

    public AddProductPresenter(AddProductViewInterface addProductView) {
        this.addProductView=addProductView;
    }

    @Override
    public void checkProductName(String productName) {

    }

    @Override
    public void checkProductDescription(String ProductDescription) {

    }


}
