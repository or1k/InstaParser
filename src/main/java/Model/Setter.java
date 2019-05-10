package Model;

import Pages.ParcingMethods;

import java.util.Map;

import static com.codeborne.selenide.Selenide.page;

public class Setter {

    public String[] keyWords;


    public ParcingMethods setKeyWords(){
        keyWords = new String[]{
                "магазин",
                "грн",
                "#гельлакднепр",
                "маникюр",
                "ногти",
                "цена",
                "Direct",
                "brand",
                "заказ",
                "доставка",
                "одежда",
                "записаться",
                "заказа",
                "nails",
                "nails",
                "nails",
                "подарок",
                "косметика",
                "аксессуар",
                "Jewelery",
                "Designer",
                "работанасебя",
                "подписывайся",
                "франшиза",
                "#піжами",
                "#Українськийбренд",
                "заробітку",
                "онлайн",
                "shop",
                "Shoes",
                "boutique",
                "отправка",
                "лучшее качество"
        };
        return page(ParcingMethods.class);
    }

    public String lineToCSV(Map reportData){
        String linetoCSV;
        linetoCSV = reportData.get("Link/ID").toString();

        return  linetoCSV;
    }


}
