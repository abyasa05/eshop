package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isSuccessful_andProductDisplayed(ChromeDriver driver) {
        // Navigate to product creation page
        driver.get(baseUrl + "/product/create");

        // Fill in the product details
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        String testProductName = "Produk baru";
        String testProductQuantity = "10";

        nameInput.sendKeys(testProductName);
        quantityInput.sendKeys(testProductQuantity);
        submitButton.click();

        // Navigate to product list page
        driver.get(baseUrl + "/product/list");

        // Verify if the product is displayed in the list
        WebElement productTable = driver.findElement(By.tagName("table"));
        String pageSource = productTable.getText();

        assertTrue(pageSource.contains(testProductName));
        assertTrue(pageSource.contains(testProductQuantity));
    }
}
