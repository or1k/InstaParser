package Test;

import BrowserConfig.MyChromeBrowserClass;
import Utils.CsvFileWriter;
import Model.Setter;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class testtest {
    public ArrayList<String> list = new ArrayList<String>();
    private Setter setter = new Setter();
    private CsvFileWriter csvFileWriter;
    private Map reportData = new HashMap();

    @BeforeTest
    public void setup(){
        Configuration.browser = MyChromeBrowserClass.class.getName();
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;

    }

    @Test
    public void Test()  {
        open("http://google.com");
        sleep(50000);
    }

}
