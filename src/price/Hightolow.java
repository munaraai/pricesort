package price;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Hightolow {
	public WebDriver driver;
@BeforeTest
public void login() {
	WebDriverManager.chromedriver().setup();
	driver= new ChromeDriver();
	driver.get("https://www.saucedemo.com/");
	driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
	driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
}
@Test
public void sorting() throws InterruptedException {
	driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select/option[4]")).click();
	List<WebElement> thePricesList = driver.findElements(By.className("inventory_item_price"));
	List<Double> newEditedList = new ArrayList<>();
	for (int i = 0; i < thePricesList.size(); i++) {
		String price = thePricesList.get(i).getText();
		String editedPrice = price.replace("$", " ");
		double val = Double.parseDouble(editedPrice.trim());
		newEditedList.add(val);
		System.out.print(val);	
	}
	for (int k = 0; k < newEditedList.size(); k++) {
		boolean checkProcess = newEditedList.get(0) > newEditedList.get(newEditedList.size() -1);
		Assert.assertEquals(checkProcess, true);
		}
}
}