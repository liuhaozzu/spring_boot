package com.liuhaozzu.springboot.readinglist.webtest.selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, value = { "server.port=0" })
public class ServerWebTests {

    private static FirefoxDriver browser;

    @Value("${local.server.port}")
    private int port;

    @BeforeClass
    public static void openBrowser() {
        browser=new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit();
    }

    @Test
    public void addBookToEmptyList() {
        System.err.println("port:>>>" + port);
        String baseUrl = "http://localhost:" + port;
        browser.get(baseUrl);
        assertEquals("you have no books in your book list", browser.findElementByTagName("div").getText());

        browser.findElementByName("title")
                .sendKeys("BOOK TITLE");
        browser.findElementByName("author")
                .sendKeys("BOOK AUTHOR");
        browser.findElementByName("isbn")
                .sendKeys("1234567890");
        browser.findElementByName("description")
                .sendKeys("DESCRIPTION");
        browser.findElementByTagName("form")
                .submit();
        WebElement dl = browser.findElementByCssSelector("dt.bookHeadline");
        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)",
                dl.getText());
        WebElement dt = browser.findElementByCssSelector("dd.bookDescription");
        assertEquals("DESCRIPTION", dt.getText());
    }
}
