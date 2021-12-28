import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendAForm {

    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        System.setProperty("web-driver.chrome.driver", "./driver/chromedriver");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

     @Test
    public void shouldTypeCorrectName(){
         driver.findElement(By.cssSelector("[type='text']")).sendKeys("Смирнова Елена");
         driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79998887766");
         driver.findElement(By.cssSelector(".checkbox__box")).click();
         driver.findElement(By.cssSelector("button")).click();
         String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
         String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
         assertEquals(expected, text);
     }
    @Test
    public void shouldTypeOnlyName() {
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Елена");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79998887766");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, text);
    }
}