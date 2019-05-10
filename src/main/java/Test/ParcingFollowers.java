package Test;

import Pages.ParcingMethods;
import Pages.LoginPage;

import View.LoginFrame2;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ParcingFollowers {


    ParcingMethods parcingMethods;
    @BeforeTest
    public void setup(){
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        parcingMethods = new ParcingMethods();
    }


    @Test()
    public void Test(String option) throws InterruptedException {


        LoginPage loginPage = open("https://www.instagram.com/?hl=ru", LoginPage.class);
        loginPage.loginPage();
        sleep(5000);
        ParcingMethods parcingMethods = new ParcingMethods();
        parcingMethods.closePopup();

        //todo открываем гео, акк или хештег
        open(LoginFrame2.linkText.getText());
        parcingMethods.parceGeo();

        switch(option){
            case ("geo"):
                parcingMethods.parceGeo();
                break;
            case  ("hashtag"):
                parcingMethods.parceHashTag();
                break;
            case ("login"):
                parcingMethods.parceLogin();
                break;
        }

    }

}
//"https://www.instagram.com/explore/locations/213664707/lviv-ukraine/"