package trivago;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;

public class TesteTrivago {
	
	private static ChromeDriver driver;
	
	@BeforeClass
    public static void setUpTest(){
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
		
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://www.trivago.com.br");
    }
	
	@Test
    public void testeTrivago() {
		WebElement pesquisa = driver.findElement(By.id("querytext"));
		pesquisa.sendKeys("Natal");
		
		pesquisa = driver.findElement(By.id("suggestion-59274/200"));
		pesquisa.click();
		
		WebElement botaoPesquisa = driver.findElement(By.xpath("//button[@class='btn btn--primary btn--regular search-button js-search-button']")); 
		botaoPesquisa.click();
		
		pesquisa = driver.findElement(By.xpath("//button[@class='dealform-button dealform-button--guests js-dealform-button-guests']"));
		pesquisa.click();
		
		List<WebElement> botesQuarto = driver.findElements(By.xpath("//button[@class='roomtype-btn']"));
		botesQuarto.get(0).click();
		
		Select ordem = new Select(driver.findElement(By.id("mf-select-sortby")));
		ordem.selectByIndex(6);
	
	    List<WebElement> listaHoteis = driver.findElements(By.xpath("//li[@class='hotel-item item-order__list-item js_co_item']"));
	    WebElement hotel = listaHoteis.get(1);
	    
	    System.out.println("Informações do hotel desejado");
	    
		WebElement nomeDoHotel = hotel.findElement(By.tagName("h3"));
		System.out.println("Nome: " + nomeDoHotel.getText());
		
		WebElement site = hotel.findElement(By.cssSelector("span[data-qa='recommended-price-partner']"));
		
		System.out.print("​Oferta da empresa​: "+ site.getText());
		
		WebElement valor = hotel.findElement(By.cssSelector("strong[data-qa='recommended-price']"));
		
		System.out.print(" ​Preço: "+ valor.getText());
		
		WebElement localizacao = hotel.findElement(By.cssSelector("div.item-link"));
		localizacao.click();
		
		WebElement divBotaoComodidades = hotel.findElement(By.className("expand-amenities"));
		
		WebElement botaoComodidades = divBotaoComodidades.findElement(By.tagName("button"));
		botaoComodidades.click();
		
		WebElement divPrincipalComodidades = hotel.findElement(By.cssSelector("div.sl-box__block"));
		
		WebElement divComodidades = divPrincipalComodidades.findElements(By.cssSelector("div[class='all-amenities__group mb-gutter']")).get(1);
		
		WebElement comodidades = divComodidades.findElement(By.tagName("ul"));
		
		
        System.out.println("\nComodidades do quarto​: " + comodidades.getText().replace("\n", ","));
			
	}
	

}
