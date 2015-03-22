package com.rstyle.maxmoto1702;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleTest {

    @Test
    public void testMethod() {
        assertThat(new Main().method(), is(0));
    }

    @Test
    public void testMethod2() {
        WebDriver driver = new PhantomJSDriver();
        driver.get("https://m.hh.ru/oauth/authorize?response_type=code&client_id=SRP5CUGB7ICSDHSGD74KAMV84HKLD934B401MBAPHKFCDU7P4UI6LSJBAIVPF5TP");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("maxmotos@live.ru");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("17021989MaxMoto");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        System.out.println(driver.getCurrentUrl());
    }
}