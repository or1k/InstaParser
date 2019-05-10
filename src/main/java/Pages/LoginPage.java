package Pages;

import View.LoginFrame2;
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



    public ParcingMethods loginPage(){
        $(By.xpath("//*[contains(@href,'login')]")).click();
        $(By.xpath("//div[@class='yOZjD _80tAB']")).shouldNotBe(Condition.visible);
//        $(By.name("username")).val(getPropByKey("Username"));
//        $(By.name("password")).val(getPropByKey("Password"));
        $(By.name("username")).val(String.valueOf(LoginFrame2.userText.getText()));
        $(By.name("password")).val(String.valueOf(LoginFrame2.passwordText.getPassword()));
        $(By.xpath("//button/div")).click();
        return page(ParcingMethods.class);
    }

}
