package Pages;

import View.LoginFrame2;
import View.TestJTabbed;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {


    public static String getPropByKey(String key) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src\\main\\resources\\config.properties");
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {

            props.load(isr);

        } catch (FileNotFoundException e) {
            //log
            e.printStackTrace();
        } catch (IOException e) {
            //log
            e.printStackTrace();
        }
        return props.getProperty(key);

    }



    public ParcingMethods loginPage(String option){
        switch(option){
            case ("geo"):
                $(By.xpath("//*[contains(@href,'login')]")).click();
                $(By.xpath("//div[@class='yOZjD _80tAB']")).shouldNotBe(Condition.visible);
                $(By.name("username")).val(String.valueOf(TestJTabbed.userTextGeo.getText()));
                $(By.name("password")).val(String.valueOf(TestJTabbed.passwordTextGeo.getPassword()));
                $(By.xpath("//button/div")).click();
                break;
            case  ("hashtag"):
                $(By.xpath("//*[contains(@href,'login')]")).click();
                $(By.xpath("//div[@class='yOZjD _80tAB']")).shouldNotBe(Condition.visible);
                $(By.name("username")).val(String.valueOf(TestJTabbed.userTextHashTag.getText()));
                $(By.name("password")).val(String.valueOf(TestJTabbed.passwordTexthashTag.getPassword()));
                $(By.xpath("//button/div")).click();
            break;
            case ("account"):
                $(By.xpath("//*[contains(@href,'login')]")).click();
                $(By.xpath("//div[@class='yOZjD _80tAB']")).shouldNotBe(Condition.visible);
                $(By.name("username")).val(String.valueOf(TestJTabbed.userTextAccount.getText()));
                $(By.name("password")).val(String.valueOf(TestJTabbed.passwordTextAccount.getPassword()));
                $(By.xpath("//button/div")).click();
            break;
        }
//        $(By.xpath("//*[contains(@href,'login')]")).click();
//        $(By.xpath("//div[@class='yOZjD _80tAB']")).shouldNotBe(Condition.visible);
//        $(By.name("username")).val(String.valueOf(LoginFrame2.userText.getText()));
//        $(By.name("password")).val(String.valueOf(LoginFrame2.passwordText.getPassword()));
//        $(By.xpath("//button/div")).click();
        return page(ParcingMethods.class);
    }

}
