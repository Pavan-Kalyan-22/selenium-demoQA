package tests;

import org.testng.annotations.Test;

import com.selenium.practice.base.*;
import pages.menu;

public class ElementsTest extends BaseTest{

    @Test
    public void Elements()  {
    	menu menu = new menu(driver);
			
    	menu.tapElement();
    	menu.tapTextBox();
    	
    }
}
