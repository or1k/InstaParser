package Test;

import Pages.ParcingMethods;
import Pages.LoginPage;
import View.TestJTabbed;
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

        switch(option){
            case ("geo"):
                open(TestJTabbed.linkText.getText());
                parcingMethods.parceGeo();
                break;
            case  ("hashtag"):
                open(TestJTabbed.linkText.getText());
                parcingMethods.parceHashTag();
                break;
            case ("account"):
                open(TestJTabbed.linkText.getText());
                parcingMethods.parceLogin();
                break;
        }

    }

}
//geo
//"https://www.instagram.com/explore/locations/749889026/dnipro/"

//hash
//"https://www.instagram.com/explore/tags/dnepr/"

//acc
//"https://www.instagram.com/valeria_cherepkova/"

//https://www.instagram.com/?hl=ru#reactivated

//https://www.instagram.com/tkachuk_nails_/