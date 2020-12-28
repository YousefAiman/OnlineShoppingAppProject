package iug.project.onlineshoppingappproject.Presenters;

public interface AddProductPresenter {
    void checkProductName(String productName);

    void checkProductDescription(String productDescription);

    void checkNameAndDiscriptionInput(String productName , String productDescription);
}
