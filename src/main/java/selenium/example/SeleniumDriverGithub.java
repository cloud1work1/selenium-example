package selenium.example;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumDriverGithub {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://github.com/");
		WebElement element = driver.findElement(By.cssSelector("[name='q']"));
		
		String searchTerm = "Selenium";
		element.sendKeys(searchTerm);
		element.sendKeys(Keys.ENTER);
		List<String> repoNameList = driver.findElements(By.cssSelector(".repo-list-item")).stream()
				.map(e -> e.getText().toLowerCase())
				.collect(Collectors.toList());
		Assert.assertTrue(repoNameList.stream().allMatch(name ->name.toLowerCase().contains(searchTerm.toLowerCase())));
		
		List<String> filteredList = repoNameList.stream().filter(name -> name.toLowerCase().contains(searchTerm.toLowerCase()))
				.collect(Collectors.toList());
		Assert.assertEquals(repoNameList, filteredList);
		driver.quit();
	}
}
