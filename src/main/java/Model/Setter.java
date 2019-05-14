package Model;

import Pages.ParcingMethods;

import java.util.HashSet;
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
                "nail",
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
                "лучшее качество",
                "fashion",
                "podarki",
                "toys",
                "byketa",
                "byket",
                "buket",
                "makeup",
                "beauty",
                "shoez",
                "studio",
                "kids",
                "lazer",
                "moda",
                "avtorunok",
                "detki",
                "media",
                "atelier",
                "Каталог",
                "Пижамы",
                "бельё",
                "shop1",
                "best",
                "ОТЗЫВЫ",
                "ВЫГОДНОЙ ПОКУПКИ",
                "шубы",
                "шуба",
                "дубленка",
                "шубы",


                "возврата нет"


        };
        return page(ParcingMethods.class);
    }

//    public String lineToCSV(Map reportData){
//        String linetoCSV;
//        linetoCSV = reportData.get("Link/ID").toString();
//        return  linetoCSV;
//    }


}
