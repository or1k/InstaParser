package Pages;

import Model.CsvFileWriter;
import Model.Setter;
import View.TestJTabbed;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;


import static com.codeborne.selenide.Selenide.*;
import static java.lang.Thread.sleep;


public class ParcingMethods {
    private final static SelenideElement PopUp = $(By.xpath("//button[@class='aOOlW   HoLwm ']"));
    private SelenideElement accountClosed = $(By.xpath("//div[@class='_4Kbb_ _54f4m']"));
    private SelenideElement FirstPicture = $(By.xpath("//div[@class='_9AhH0']"));
    private SelenideElement nickButton = $(By.xpath("//a[@class='FPmhX notranslate nJAzx']"));
    private SelenideElement Description = $(By.xpath("//div[@class='-vDIg']/span"));
    private SelenideElement Followers = $(By.xpath("//*[@class='-nal3 'and contains(@href, 'followers')]/span"));
    private SelenideElement nextAccount = $(By.xpath("//a[@class='HBoOv coreSpriteRightPaginationArrow']"));
    //public ArrayList<String> list = new ArrayList<String>();
    private SelenideElement FirstComment = $(By.xpath("//ul[@class='XQXOT']//li[1]"));
    //private Map reportData = new HashMap();
    private HashSet list = new HashSet();
    private SelenideElement timeInPost = $(By.xpath("//div[@class='k_Q0X NnvRN']//time"));
   // private Setter setter = new Setter();
    private CsvFileWriter csvFileWriter;
    private int order = Integer.parseInt(TestJTabbed.order.getText());
    private String startTime = "";


    public void closePopup(){
        if(PopUp.isEnabled()){
            PopUp.click();
        }else {
            System.out.println("nixuya");
        }

    }


//    public boolean checkKeyWordsInDescription(){
//        Setter setter = new Setter();
//        setter.setKeyWords();
//        for(int i=0;i<setter.keyWords.length; i++){
//            if(!Description.isDisplayed()){
//                System.out.println(" Аккаунт чистый. кек. Блок с описание отсутствует.");
//                return false;
//            }else {
//                if (Description.getText().contains(setter.keyWords[i])) {
//                    System.out.println("Аккаунт похож на магазин. Скипаем.");
//                    System.out.println("Есть совпадение по слову " + setter.keyWords[i]);
//                    return true;
//
//                }
//            }
//        }
//        System.out.println(" Аккаунт чистый. кек");
//        return false;
//    }

    public boolean checkKeyWordsInDescription(){
        Setter setter = new Setter();
        setter.setKeyWords();
        for(int i=0;i<setter.keyWords.length; i++){
            if(Description.isDisplayed()) {
                if (Description.getText().contains(setter.keyWords[i])) {
                    System.out.println("Аккаунт похож на магазин. Скипаем.");
                    System.out.println("Есть совпадение по слову " + setter.keyWords[i]);
                    return true;
                }
            }else{
                System.out.println("Аккаунт чистый. кек");
                return false;
            }
        }
        System.out.println("Аккаунт чистый. кек");
        return false;
    }

    private String getCurrentTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH-mm");
        Date currentTime = new Date();
       // System.out.println(dateFormat.format(currentTime)); //12-08
        return dateFormat.format(currentTime);
    }


    private boolean checkStatusAccount(){
        if(accountClosed.isDisplayed()){
            System.out.println("Акк закрыт");
            return true;
        }else{
            System.out.println("Акк открыт");
            return false;
        }
    }

    private boolean checkFollowersAndFollowing(){
        if(Followers.isDisplayed()) {
            try {
                int followersNumber = Integer.parseInt(Followers.getAttribute("title").replaceAll("[^0-9]", ""));
                // int followingNumber = Integer.parseInt(Following.getText().replaceAll("[^0-9]", ""));
                if (followersNumber < Integer.parseInt(TestJTabbed.countSubscribers.getText())) {
                    System.out.println("Аккаунт не подходит по условию: Подписчик/Подписки");
                    return false;

                } else {
                    System.out.println("Аккаунт подходит по условию: Подписчик/Подписки");
                    return true;
                }
            }catch (NumberFormatException e){
                System.out.println("followers not a number");
                return false;
            }
        }else{
            System.out.println("Не отображается кол-ва подписоты.");
            return false;
        }
    }

    public int checkLastPostDate() throws InterruptedException {
//        Actions newTab = new Actions(WebDriverRunner.getWebDriver());
//        newTab.keyDown(Keys.CONTROL).click(FirstPicture).keyUp(Keys.CONTROL).build().perform();
            FirstPicture.click();
            sleep(500);
            //switchTo().window(2);
        if(timeInPost.isDisplayed()) {
            String dateFromInst = timeInPost.getAttribute("datetime").substring(0, 10).replaceAll("-", ".");
            try {
                // создаем формат, в котором будем парсить дату
                Date dateNow = new Date();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                Date date2 = dateFormat.parse(dateFromInst);

//            System.out.println("Первая дата: " + dateFormat.format(dateNow));
//            System.out.println("Вторая дата: " + dateFromInst);

                long milliseconds = dateNow.getTime() - date2.getTime();
                // 24 часа = 1 440 минут = 1 день
                int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
                System.out.println("Разница между датами в днях: " + days);
                return days;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Неполучилось проверять дату поста время.");
        }
        return 0;
    }

    private boolean checkKeyWordsFirstPost(){
        Setter setter = new Setter();
        setter.setKeyWords();
        for(int i=0;i<setter.keyWords.length; i++){
            if(FirstComment.isDisplayed()) {
                if (FirstComment.getText().contains(setter.keyWords[i])) {
                    System.out.println("Рекламный пост. Скипаем.");
                    System.out.println("Есть совпадение по слову " + setter.keyWords[i]);
                    return true;
                }else{
                    System.out.println("Пост не рекламный. кек");
                    return false;
                }
            }else{
                System.out.println("Пост отсутствует.");
                return false;
            }
        }
        return false;
    }

    public ParcingMethods parceGeo(){

        System.out.println("GEO Start");
        csvFileWriter = new CsvFileWriter();
        FirstPicture.click();
        startTime = getCurrentTime();
        int numberFollowerInList = 1;
        System.out.println(startTime);
        for (int i = 0; i < order;) {
            try {
                System.out.println("--------------------------Проверка пользователя номер "+ numberFollowerInList +"--------------------------");
                System.out.println("ССылка на пользователя " + WebDriverRunner.getWebDriver().getCurrentUrl());
                sleep(500);

                /**
                 * Проверка на рекламный пост
                 */
                System.out.println("Проверка на рекламный пост:");
                if(checkKeyWordsFirstPost()){
                    nextAccount.click();
                    numberFollowerInList++;
                    continue;
                }


                /**
                 * Пытаемся перейти в аккаунт
                 */
                if(nickButton.isDisplayed()) {
                    Actions newTab = new Actions(WebDriverRunner.getWebDriver());
                    newTab.keyDown(Keys.CONTROL).click(nickButton).keyUp(Keys.CONTROL).build().perform();
                }else{
                    nextAccount.click();
                    sleep(1000);
                    numberFollowerInList++;
                    continue;
                }

                sleep(1000);
                switchTo().window(1);


                /**
                 * Закрытый акк или нет
                 */

                    if (checkStatusAccount()) {
                        WebDriverRunner.getWebDriver().close();
                        switchTo().window(0);
                        nextAccount.click();
                        sleep(1000);
                        numberFollowerInList++;
                        continue;
                    }

                /**
                 * Проверка на Стоп-слова
                 */
                    System.out.println("Проверка на стоп-слова:");
                    if (checkKeyWordsInDescription()) {
                        WebDriverRunner.getWebDriver().close();
                        switchTo().window(0);
                        nextAccount.click();
                        sleep(1000);
                        numberFollowerInList++;
                        continue;
                    }


                /**
                 * Проверка подписчиков и подписок
                 */
                    if (!(checkFollowersAndFollowing())) {
                        WebDriverRunner.getWebDriver().close();
                        switchTo().window(0);
                        nextAccount.click();
                        sleep(1000);
                        numberFollowerInList++;
                        continue;
                }


//                /**
//                 * Проверка на дату последнего поста
//                 */
//                if(checkLastPostDate() > 30){
//                    System.out.println("Последний пост старше 30 дней");
//                    WebDriverRunner.getWebDriver().close();
//                    switchTo().window(1);
//                    WebDriverRunner.getWebDriver().close();
//                    switchTo().window(0);
//                    nextAccount.click();
//                    sleep(1000);
//                    continue;
//                }else{
//                    System.out.println("Последний пост младше 30 дней");
//                    WebDriverRunner.getWebDriver().close();
//                    switchTo().window(1);
//                    WebDriverRunner.getWebDriver().close();
//                    switchTo().window(0);
//                }

                /**
                 * Добавляемя аккаунт в список
                 */
                list.add(WebDriverRunner.getWebDriver().getCurrentUrl());

                /**
                 * Приравниваем цикл к количеству записей в файле.
                 */
                if(i != list.size()){
                    i=list.size();
                }
                WebDriverRunner.getWebDriver().close();
                switchTo().window(0);
                nextAccount.click();
                sleep(1000);
            }catch (Exception ex){
                csvFileWriter.writeCsvFile("Instagram_peoples_"+ startTime + "_" + order +".csv", list, "people");
                System.out.println(ex.getMessage());
            }

            numberFollowerInList++;
        }
        csvFileWriter.writeCsvFile("Instagram_peoples_"+ startTime + "_" + order +".csv", list, "people");
        return this;
    }




    public ParcingMethods parceHashTag(){
        System.out.println("HashTag Start");
        csvFileWriter = new CsvFileWriter();
        FirstPicture.click();
        startTime = getCurrentTime();
        int numberFollowerInList = 1;
        for (int i = 0; i < order;) {
            try {
                System.out.println("--------------------------Проверка пользователя номер "+ numberFollowerInList +"--------------------------");
                System.out.println("ССылка на пользователя " + WebDriverRunner.getWebDriver().getCurrentUrl());
                sleep(500);

                /**
                 * Проверка на рекламный пост
                 */
                try {
                    System.out.println("************Проверка на рекламный пост************");
                    if (checkKeyWordsFirstPost()) {
                        nextAccount.click();
                        numberFollowerInList++;
                        continue;
                    }
                }catch (Exception ex){
                    nextAccount.click();
                    numberFollowerInList++;
                    continue;
                }


                Actions newTab = new Actions(WebDriverRunner.getWebDriver());
                newTab.keyDown(Keys.CONTROL).click(nickButton).keyUp(Keys.CONTROL).build().perform();

                sleep(500);
                switchTo().window(1);


                /**
                 * Закрытый акк или нет
                 */
                try {
                    if (checkStatusAccount()) {
                        WebDriverRunner.getWebDriver().close();
                        switchTo().window(0);
                        nextAccount.click();
                        sleep(1000);
                        numberFollowerInList++;
                        continue;
                    }
                }catch (Exception ex){
                    WebDriverRunner.getWebDriver().close();
                    switchTo().window(0);
                    nextAccount.click();
                    sleep(1000);
                    numberFollowerInList++;
                    continue;
                }

                /**
                 * Проверка на Стоп-слова
                 */
                try {
                    System.out.println("Проверка на стоп-слова:");
                    if (checkKeyWordsInDescription()) {
                        WebDriverRunner.getWebDriver().close();
                        switchTo().window(0);
                        nextAccount.click();
                        sleep(1000);
                        numberFollowerInList++;
                        continue;
                    }
                }catch (Exception ex){
                    WebDriverRunner.getWebDriver().close();
                    switchTo().window(0);
                    nextAccount.click();
                    sleep(1000);
                    numberFollowerInList++;
                    continue;
                }

                /**
                 * Проверка подписчиков и подписок
                 */
                try {
                    if (!(checkFollowersAndFollowing())) {
                        WebDriverRunner.getWebDriver().close();
                        switchTo().window(0);
                        nextAccount.click();
                        sleep(1000);
                        numberFollowerInList++;
                        continue;
                    }
                }catch (Exception ex){
                    WebDriverRunner.getWebDriver().close();
                    switchTo().window(0);
                    nextAccount.click();
                    sleep(1000);
                    numberFollowerInList++;
                    continue;
                }


//                /**
//                 * Проверка на дату последнего поста
//                 */
//                if(checkLastPostDate() > 30){
//                    System.out.println("Последний пост старше 30 дней");
//                    WebDriverRunner.getWebDriver().close();
//                    switchTo().window(1);
//                    WebDriverRunner.getWebDriver().close();
//                    switchTo().window(0);
//                    nextAccount.click();
//                    sleep(1000);
//                    continue;
//                }else{
//                    System.out.println("Последний пост младше 30 дней");
//                    WebDriverRunner.getWebDriver().close();
//                    switchTo().window(1);
//                    WebDriverRunner.getWebDriver().close();
//                    switchTo().window(0);
//                }

                /**
                 * Добавляемя аккаунт в список
                 */
                list.add(WebDriverRunner.getWebDriver().getCurrentUrl());
                /**
                 * Приравниваем цикл к количеству записей в файле.
                 */
                if(i != list.size()){
                    i=list.size();
                }

                WebDriverRunner.getWebDriver().close();
                switchTo().window(0);
                nextAccount.click();
                sleep(1000);
                i++;
            }catch (Exception ex){
                csvFileWriter.writeCsvFile("Instagram_peoples_"+ startTime + "_" + order +".csv", list, "people");
                System.out.println(ex.getMessage());
            }
            numberFollowerInList++;
        }
        csvFileWriter.writeCsvFile("Instagram_peoples_"+ startTime + "_" + order +".csv", list, "people");
        return this;
    }


    public void parceLogin(){
        System.out.println("By Account Start");
        csvFileWriter = new CsvFileWriter();
        Followers.click();
        int numberFollowerInList = 1;
        System.out.println(order);
        startTime = getCurrentTime();

        for (int i = 0; i < order;) {
            try {
                String LocatorName = "//li[" + numberFollowerInList + "]//a[@class='FPmhX notranslate _0imsa ']";
                SelenideElement loginButton = $(By.xpath(LocatorName));
                executeJavaScript("arguments[0].scrollIntoView(true);", loginButton);

                System.out.println("--------------------------Проверка пользователя номер "+ numberFollowerInList +"--------------------------");
                loginButton.click();
                sleep(1000);
                System.out.println("ССылка на пользователя " + WebDriverRunner.getWebDriver().getCurrentUrl());

                /**
                 * Закрытый акк или нет
                 */
                if(checkStatusAccount()){
                    WebDriverRunner.getWebDriver().navigate().back();
                    sleep(1000);
                    numberFollowerInList++;
                    continue;
                }

//                /**
//                 * Проверка на рекламный пост
//                 */
//                System.out.println("************Проверка на рекламный пост************");
//                if(checkKeyWordsFirstPost()){
//                    nextAccount.click();
//                    continue;
//                }


//                Actions newTab = new Actions(WebDriverRunner.getWebDriver());
//                newTab.keyDown(Keys.CONTROL).click(nickButton).keyUp(Keys.CONTROL).build().perform();
//                System.out.println("ССылка на пользователя " + WebDriverRunner.getWebDriver().getCurrentUrl());
//                sleep(1000);
//                switchTo().window(1);




                /**
                 * Проверка на Стоп-слова
                 */
                System.out.println("Проверка на стоп-слова:");
                if(checkKeyWordsInDescription()){
                    WebDriverRunner.getWebDriver().navigate().back();
                    sleep(1000);
                    numberFollowerInList++;
                    continue;
                }

                /**
                 * Проверка подписчиков и подписок
                 */
                try {
                    if (!(checkFollowersAndFollowing())) {
                        WebDriverRunner.getWebDriver().navigate().back();
                        sleep(1000);
                        numberFollowerInList++;
                        continue;
                    }
                }catch (Exception ex){
                    WebDriverRunner.getWebDriver().navigate().back();
                    sleep(1000);
                    numberFollowerInList++;
                    continue;
                }


                /**
                 * Проверка на дату последнего поста
                 */
                if(FirstPicture.isDisplayed()) {
                    if (checkLastPostDate() > 30) {
                        System.out.println("Последний пост старше 30 дней");
                        back();
                        back();
                        sleep(500);
                        numberFollowerInList++;
                        continue;
                    } else {
                        System.out.println("Последний пост младше 30 дней");
                        WebDriverRunner.getWebDriver().navigate().back();
                    }
                }else{
                    System.out.println("Постов нет.");
                    WebDriverRunner.getWebDriver().navigate().back();
                    sleep(1000);
                    numberFollowerInList++;
                    continue;
                }

                list.add(WebDriverRunner.getWebDriver().getCurrentUrl());

                /**
                 * Приравниваем цикл к количеству записей в файле.
                 */
                if(i != list.size()){
                    i=list.size();
                }

                WebDriverRunner.getWebDriver().navigate().back();
                sleep(1000);
                i++;
            }catch (Exception ex){
                csvFileWriter.writeCsvFile("Instagram_peoples_"+ startTime + "_" + order +".csv", list, "people");
                System.out.println(ex.getMessage());
            }
            numberFollowerInList++;
        }
        csvFileWriter.writeCsvFile("Instagram_peoples_"+ startTime + "_" + order +".csv", list, "people");
    }




}