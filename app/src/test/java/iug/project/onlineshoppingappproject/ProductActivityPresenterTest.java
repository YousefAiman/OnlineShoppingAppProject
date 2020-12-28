package iug.project.onlineshoppingappproject;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import iug.project.onlineshoppingappproject.Views.ProductActivityViewInterface;

@RunWith(MockitoJUnitRunner.class)
public class ProductActivityPresenterTest extends TestCase {
    ProductActivityPresenter productActivityPresenter;

    @Mock
    ProductActivityViewInterface productActivityViewInterface;

    @Before
    public void setUp(){
        productActivityPresenter = new ProductActivityPresenter(productActivityViewInterface);
    }

    @Test
    public void testPresenterShowImages(){
        List<String> images = new ArrayList<>();
        images.add("image1");
        images.add("image2");
        images.add("image3");
        productActivityPresenter.checkImages(images);
//        productActivityViewInterface.createImagesViewPager();
        Mockito.verify(productActivityViewInterface).createImagesViewPager();
    }

    @Test
    public void testPresenterEmptyImages(){
        List<String> images = new ArrayList<>();

        productActivityPresenter.checkImages(images);
//        productActivityViewInterface.printNoImagesError();
        Mockito.verify(productActivityViewInterface).printNoImagesError();

    }

}
