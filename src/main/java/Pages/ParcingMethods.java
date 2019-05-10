package Pages;

import Model.CsvFileWriter;
import Model.Setter;
import com.codeborne.selenide.SelenideElement;
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
    private SelenideElement accountClosed = $(By.xpath("//h2"));
    private SelenideElement FirstPicture = $(By.xpath("//div[@class='_9AhH0']"));
    private SelenideElement nickButton = $(By.xpath("//a[@class='FPmhX notranslate nJAzx']"));
    private SelenideElement Description = $(By.xpath("//div[@class='-vDIg']/span"));
    private SelenideElement Followers = $(By.xpath("//*[@class='-nal3 'and contains(@href, 'followers')]/span"));
    private SelenideElement nextAccount = $(By.xpath("//a[text()='Next']"));
    public ArrayList<String> list = new ArrayList<String>();
    private SelenideElement FirstComment = $(By.xpath("//ul[@class='XQXOT']//li[1]"));
    private Map reportData = new HashMap();
    private Setter setter = new Setter();
    private CsvFileWriter csvFileWriter;


    public void closePopup(){
        if(PopUp.isEnabled()){
            PopUp.click();
        }else {
            System.out.println("nixuya");
        }

    }


    public boolean checkKeyWordsIntText(){
        Setter setter = new Setter();
        setter.setKeyWords();
        for(int i=0;i<setter.keyWords.length; i++){
            if(!Description.isDisplayed()){
                System.out.println(" Аккаунт чистый. кек. Блок с описание отсутствует.");
                return false;
            }else {
                if (Description.getText().contains(setter.keyWords[i])) {
                    System.out.println("Аккаунт похож на магазин. Скипаем.");
                    System.out.println("Есть совпадение по слову " + setter.keyWords[i]);
                    return true;

                }
            }
        }
        System.out.println(" Аккаунт чистый. кек");
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
        int followersNumber = Integer.parseInt(Followers.getAttribute("title").replaceAll("[^0-9]", ""));
       // int followingNumber = Integer.parseInt(Following.getText().replaceAll("[^0-9]", ""));
        if(followersNumber < 20){
            System.out.println("Аккаунт не подходит по условию: Подписчик/Подписки");
            return false;

        }else{
            System.out.println("Аккаунт подходит по условию: Подписчик/Подписки");
            return true;
        }
    }

    public int checkLastPostDate() throws InterruptedException {
        Actions newTab = new Actions(WebDriverRunner.getWebDriver());
        newTab.keyDown(Keys.CONTROL).click(FirstPicture).keyUp(Keys.CONTROL).build().perform();
        sleep(500);
        switchTo().window(2);
        String dateFromInst =  $(By.xpath("//div[@class='k_Q0X NnvRN']//time")).getAttribute("datetime").substring(0,10).replaceAll("-",".");
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
            return 0;
        }
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
                }
            }else{
                System.out.println("Аккаунт чистый. кек");
                return false;
            }
        }
        System.out.println("Пост не рекламный. кек");
        return false;
    }

    public ParcingMethods parceGeo(){
        csvFileWriter = new CsvFileWriter();
        FirstPicture.click();
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("--------------------------Проверка пользователя номер "+ i +"--------------------------");
                sleep(1000);

                /**
                 * Проверка на рекламный пост
                 */
                System.out.println("Проверка на рекламный пост:");
                if(checkKeyWordsFirstPost()){
                    nextAccount.click();
                    continue;
                }


                Actions newTab = new Actions(WebDriverRunner.getWebDriver());
                newTab.keyDown(Keys.CONTROL).click(nickButton).keyUp(Keys.CONTROL).build().perform();
                System.out.println("ССылка на пользователя " + WebDriverRunner.getWebDriver().getCurrentUrl());
                sleep(1000);
                switchTo().window(1);


                /**
                 * Закрытый акк или нет
                 */
                if(checkStatusAccount()){
                    WebDriverRunner.getWebDriver().close();
                    switchTo().window(0);
                    nextAccount.click();
                    sleep(1000);
                    continue;
                }

                /**
                 * Проверка на Стоп-слова
                 */
                System.out.println("Проверка на стоп-слова:");
                if(checkKeyWordsIntText()){
                    WebDriverRunner.getWebDriver().close();
                    switchTo().window(0);
                    nextAccount.click();
                    sleep(1000);
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
                reportData.put("Link/ID", WebDriverRunner.getWebDriver().getCurrentUrl());
                list.add(setter.lineToCSV(reportData));
                WebDriverRunner.getWebDriver().close();
                switchTo().window(0);
                nextAccount.click();
                sleep(1000);
            }catch (Exception ex){
                csvFileWriter.writeCsvFile("Instagram_peoples_"+getCurrentTime()+".csv", list, "people");
                System.out.println(ex.getMessage());
            }
        }
        csvFileWriter.writeCsvFile("Instagram_peoples_"+getCurrentTime()+".csv", list, "people");
        return this;
    }




    public ParcingMethods parceHashTag(){
        csvFileWriter = new CsvFileWriter();
        FirstPicture.click();
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("--------------------------Проверка пользователя номер "+ i +"--------------------------");
                sleep(1000);

                /**
                 * Проверка на рекламный пост
                 */
                System.out.println("Проверка на рекламный пост:");
                if(checkKeyWordsFirstPost()){
                    nextAccount.click();
                    continue;
                }


                Actions newTab = new Actions(WebDriverRunner.getWebDriver());
                newTab.keyDown(Keys.CONTROL).click(nickButton).keyUp(Keys.CONTROL).build().perform();
                System.out.println("ССылка на пользователя " + WebDriverRunner.getWebDriver().getCurrentUrl());
                sleep(1000);
                switchTo().window(1);


                /**
                 * Закрытый акк или нет
                 */
                if(checkStatusAccount()){
                    WebDriverRunner.getWebDriver().close();
                    switchTo().window(0);
                    nextAccount.click();
                    sleep(1000);
                    continue;
                }

                /**
                 * Проверка на Стоп-слова
                 */
                System.out.println("Проверка на стоп-слова:");
                if(checkKeyWordsIntText()){
                    WebDriverRunner.getWebDriver().close();
                    switchTo().window(0);
                    nextAccount.click();
                    sleep(1000);
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
                reportData.put("Link/ID", WebDriverRunner.getWebDriver().getCurrentUrl());
                list.add(setter.lineToCSV(reportData));
                WebDriverRunner.getWebDriver().close();
                switchTo().window(0);
                nextAccount.click();
                sleep(1000);
            }catch (Exception ex){
                csvFileWriter.writeCsvFile("Instagram_peoples_"+getCurrentTime()+".csv", list, "people");
                System.out.println(ex.getMessage());
            }
        }
        csvFileWriter.writeCsvFile("Instagram_peoples_"+getCurrentTime()+".csv", list, "people");
        return this;
    }
    public ParcingMethods parceLogin(){

        return this;
    }




}