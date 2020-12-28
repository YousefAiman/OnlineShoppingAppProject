package iug.project.onlineshoppingappproject;

import android.widget.ImageView;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void testPresenterShowImage(){
        ImageView i;
    }

}
