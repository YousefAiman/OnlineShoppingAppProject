package iug.project.onlineshoppingappproject;

import iug.project.onlineshoppingappproject.Views.AddProductViewInterface;

public class AddProductPresenter implements iug.project.onlineshoppingappproject.Presenters.AddProductPresenter {
    private final AddProductViewInterface addProductView;

    public AddProductPresenter(AddProductViewInterface addProductView) {
        this.addProductView=addProductView;
    }

    @Override
    public void checkProductName(String productName) {

        if(productName.trim().isEmpty()){
            addProductView.printErrorMessage("Name field is empty");
        }

    }

    @Override
    public void checkProductDescription(String productDescription) {
        if(productDescription.trim().isEmpty()){
            addProductView.printErrorMessage("discription field is empty");
        }


    }

    @Override
    public void checkNameAndDiscriptionInput(String productName, String productDescription) {
        if (!(productName.trim().isEmpty()) && !(productDescription.trim().isEmpty()) ){
            addProductView.printSuccessMessage("Add Success");
        }

    }


}
