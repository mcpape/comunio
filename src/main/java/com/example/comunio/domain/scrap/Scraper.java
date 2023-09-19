package com.example.comunio.domain.scrap;

import com.example.comunio.domain.ScrapingResult;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Scraper {

    @Value("${comunio.scrap.url}")
    private String comunioUrl;
    @Value("${comunio.scrap.user}")
    private String comunioUser;
    @Value("${comunio.scrap.password}")
    private String comunioPassword;

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public ScrapingResult scrap(String stopDate) {
        initWebdriver();
        callUrl();
        acceptCookies();
        login();
        List<String> newsResult = loadNewsData(stopDate);
        List<String> tableResult = loadTableData();
        closeWebdriver();

        return new ScrapingResult(newsResult, tableResult);
    }

    private List<String> loadTableData() {
        loadTablePage();
        WebElement tableRootElement = loadAllStandings();

        List<WebElement> childTableElements = tableRootElement.findElements(By.xpath(".//*"));
        List<String> currentTable = childTableElements.stream()
                .map(WebElement::getText)
                .toList();

        return currentTable.stream()
                .filter(s -> !s.isBlank())
                .filter(s -> s.contains("\n"))
                .distinct()
                .toList();
    }

    private WebElement loadAllStandings() {
        WebElement tableRootElement = webDriver.findElement(
                By.xpath("/html/body/grid/level[1]/div/div/level/rail[2]/lore/div[2]/div[2]/div[2]/div[2]/div/div/div[1]/div[5]/div[3]"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return tableRootElement;
    }

    private void loadTablePage() {
        webDriverWait.until(d -> d.findElement(
                        By.xpath("/html/body/grid/level[1]/div/div/level/rail[2]/lore/div[2]/header/div/main-navi/div/div/div/nav/div[2]/a[2]")))
                .click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<String> loadNewsData(String stopDate) {
        boolean loadMoreData = true;
        List<WebElement> newsElements = new ArrayList<>();
        while (loadMoreData) {
            newsElements = webDriver.findElements(By.xpath("//*[@id=\"dashboard-left-content\"]/div/div[2]/div"));
            loadMoreElements();
            loadMoreData = loadMoreData(newsElements, stopDate);
        }

        return newsElements.stream()
                .map(WebElement::getText)
                .toList();
    }

    private boolean loadMoreData(List<WebElement> newsElements,
                                 String stopDate) {
        return newsElements.stream()
                .map(WebElement::getText)
                .map(text -> text.split("\\r?\\n")[0])
                .noneMatch(result -> result.equals(stopDate));
    }

    private void loadMoreElements() {
        try {
            webDriverWait.until(d -> d.findElement(By.xpath("//*[@id=\"btn_load_more_news\"]"))).click();
            Thread.sleep(2000);
            webDriverWait.until(d -> d.findElement(By.xpath(
                    "html/body/grid/level[1]/div/div/level/rail[2]/lore/div[2]/div[2]/div[2]/div/div/div/div/div/div[1]/div/div[2]")));
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initWebdriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    private void callUrl() {
        webDriver.get(comunioUrl);
    }

    private void acceptCookies() {
        webDriver.findElement(By.xpath("//*[@id=\"qc-cmp2-ui\"]/div[2]/div/button[2]")).click();
        webDriverWait.until(d -> d.findElement(
                By.xpath("/html/body/grid/level[1]/div/login-page/div/level/rail[2]/lore[4]/div[2]/div[1]/div[2]/a[1]"))).click();
    }

    private void login() {
        WebElement userNameElement = webDriver.findElement(By.xpath("//*[@id=\"input-login\"]"));
        userNameElement.sendKeys(comunioUser);
        WebElement passwordElement = webDriver.findElement(By.xpath("//*[@id=\"input-pass\"]"));
        passwordElement.sendKeys(comunioPassword);

        webDriver.findElement(By.xpath("//*[@id=\"login-btn-modal\"]")).click();

        //wait for login to complete
        webDriverWait.until(d -> d.findElement(By.xpath(
                "html/body/grid/level[1]/div/div/level/rail[2]/lore/div[2]/div[2]/div[2]/div/div/div/div/div/div[1]/div/div[2]")));
    }

    private void closeWebdriver() {
        webDriver.close();
        webDriver.quit();
    }

}
