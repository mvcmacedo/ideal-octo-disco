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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 * Test with Chrome.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class GolangTest {

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
    public void testGolangWebsiteCodeExecution() {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.get("https://golang.org");

        By searchButton = By.className("js-playgroundRunEl");

        wait.until(elementToBeClickable(searchButton));

        driver.findElement(searchButton).click();

        wait.until(textToBePresentInElementLocated(By.tagName("body"),
                "Hello, 世界"));
    }

    @Test
    public void testGolangPlaygroundExecution() {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.get("https://play.golang.org/");

        By textArea = By.id("code");
        driver.findElement(textArea).clear();
        driver.findElement(textArea).sendKeys("package main\n" +
                "\n" +
                "import (\n" +
                "\t\"fmt\"\n" +
                ")\n" +
                "\n" +
                "func main() {\n" +
                "\tfmt.Println(\"This is only a test!\")\n" +
                "}\n");

        By runCodeButton = By.id("run");
        driver.findElement(runCodeButton).click();

        wait.until(textToBePresentInElementLocated(By.tagName("body"),
                "This is only a test!"));
    }
}
