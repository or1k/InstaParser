package Test;

import Model.CsvFileWriter;
import Model.Setter;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class testtest {
    public ArrayList<String> list = new ArrayList<String>();
    private Setter setter = new Setter();
    private CsvFileWriter csvFileWriter;
    private Map reportData = new HashMap();

    @BeforeTest
    public void setup(){
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;

    }

    @Test
    public void Test()  {



    }

}
