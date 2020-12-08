/*
 * (C) Copyright 2016 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.github.bonigarcia.wdm.test;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 * Test with Chrome.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class SpotifyTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSpotifyJobs() {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.get("https://www.spotify.com/br/");

        By searchJobsButton = By.linkText("Empregos");
        driver.findElement(searchJobsButton).click();

        wait.until(elementToBeClickable(By.linkText("Explore all jobs")));

        wait.until(textToBePresentInElementLocated(By.tagName("body"),
                "Join the band"));
    }

    @Test
    public void testSpotifyIncorrectLogin() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();

        driver.get("https://www.spotify.com/br/");

        By loginMenuItem = By.linkText("Entrar");
        driver.findElement(loginMenuItem).click();

        wait.until(elementToBeClickable(By.id("login-username")));

        By inputName = By.id("login-username");
        By inputPassword = By.id("login-password");

        driver.findElement(inputName).sendKeys("Teste");
        driver.findElement(inputPassword).sendKeys("Teste");

        By loginButton = By.id("login-button");
        driver.findElement(loginButton).click();

        wait.until(textToBePresentInElementLocated(By.tagName("body"),
                "Nome de usuário ou senha incorretos."));
    }

    @Test
    public void testSpotifyIncorrectSignUp() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();

        driver.get("https://www.spotify.com/br/");

        By loginMenuItem = By.linkText("Inscrever-se");
        driver.findElement(loginMenuItem).click();

        wait.until(elementToBeClickable(By.xpath("//button[contains(text(),'Inscrever-se')]")));

        By signUpButton = By.xpath("//button[contains(text(),'Inscrever-se')]");
        driver.findElement(signUpButton).click();

        wait.until(textToBePresentInElementLocated(By.tagName("body"),
                "Você deve inserir seu e-mail."));
    }
}
