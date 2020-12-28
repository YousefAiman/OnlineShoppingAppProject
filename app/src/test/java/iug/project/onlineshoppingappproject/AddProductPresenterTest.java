package iug.project.onlineshoppingappproject;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import iug.project.onlineshoppingappproject.Views.AddProductViewInterface;
import iug.project.onlineshoppingappproject.Views.RegisterActivityViewInterface;

@RunWith(MockitoJUnitRunner.class)
public class AddProductPresenterTest {
    AddProductPresenter addProductPresenter;
    @Mock
    AddProductViewInterface addProductView;


    @Before
    public void setUp() {
        addProductPresenter = new AddProductPresenter(addProductView);
    }

    @Test
    public void testPresenterSuccessTest(){

        String name = "Car";
        String description = "KIA car 2020 Red";


        //when
        addProductPresenter.checkProductName(name);
        addProductPresenter.checkProductDescription(description);

        //then
        Mockito.verify(addProductView).printSuccessMessage("Add Success");

    }

    @Test
    public void testPresenterNameEmptyError(){

        String name = "";
        String description = "KIA car 2020 Red ";

        //when
        addProductPresenter.checkProductName(name);
        addProductPresenter.checkProductDescription(description);

        //then
        Mockito.verify(addProductView).printErrorMessage("Name field is empty");

    }


    @Test
    public void testPresenterDescriptionEmptyError(){

        String name = "KIA CAR";
        String discription = "";

        //when
        addProductPresenter.checkProductName(name);
        addProductPresenter.checkProductDescription(discription);
        //then
        Mockito.verify(addProductView).printErrorMessage("discription field is empty");

    }


}
