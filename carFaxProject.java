package MyAssignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class carFaxProject{
	public static void main(String[] args) throws InterruptedException {
		
	
	// 1. Go  to carfax.com 
	System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\browserDrivers\\chromedriver.exe");
	WebDriver driver = new ChromeDriver(); 
	driver.get("http://www.carfax.com/");
	
	driver.manage().window().maximize();
	
	//2. Click on Find a Used Car
	driver.findElement(By.xpath("//a[contains(text(), 'Find a Used Car')]")).click();
	Thread.sleep(2000);
	
	
	//3. Verify the page title contains the word “Used Cars”
	String expectedTitle = "Used Cars";
	if(driver.getTitle().contains(expectedTitle)) {
		System.out.println("Pass title");
	}else {
		System.out.println("Fail title");
	}
	
	
	
	//4. Choose “Tesla” for  Make.
	driver.findElement(By.xpath("//*[@class=\'form-control search-make\']")).click();
	Thread.sleep(2000);
	Select make = new Select(driver.findElement(By.xpath("//*[@class=\'form-control search-make\']")));
	make.selectByValue("Tesla");
	
	
	
	//5. Verify that the Select Model dropdown box contains 3 current Tesla models - (Model 3, Model S, Model X).
	driver.findElement(By.xpath("//*[@aria-label='Search Model']")).click();
//	driver.findElement(By.xpath("//*[@class=\'current-models\']"));
//	Select  selectDropDownBox= new Select(driver.findElement(By.xpath("//*[@aria-label='Search Model']")));
//	List <WebElement> selectDropDownBoxOptions = selectDropDownBox.getOptions();
//	
//	for(WebElement webElement:selectDropDownBoxOptions)
//	System.out.println(webElement.getText());
	Thread.sleep(2000);
	String t = driver.findElement(By.xpath("//*[@aria-label='Search Model']")).getText();
//	System.out.println(t);
	String expectedOption1 = "Model 3";
	String expectedOption2 = "Model S";
	String expectedOption3 = "Model X";
	
	if(t.contains(expectedOption1)&t.contains(expectedOption2)&t.contains(expectedOption3)) {
		System.out.println("Test case #5 Pass");
	}else System.out.println("Test case #5 Fail");
	Thread.sleep(2000);
//	List <String> expectedOptions = Arrays.asList("Select Model", "Model 3", "Model S", "Model X");
//	boolean pass = false;
//	for(int i=0; i<selectDropDownBoxOptions.size(); i++) {
//		if(selectDropDownBoxOptions.get(i).equals(expectedOption1)){
//			pass=true;}else pass=false;
//			}
//	if (pass)System.out.println("Pass");
//	else System.out.println("Fail test");
		
//	selectDropDownBoxOptions.get(i).equals(expectedOption2)&selectDropDownBoxOptions.get(i).equals(expectedOption3))
	
//	("Model 3", "Model S", "Model X");
//	
	
	
	//6. Choose “Model S” for Model.
	Select make2 = new Select(driver.findElement(By.xpath("//*[@aria-label='Search Model']")));
	make2.selectByIndex(2);
	Thread.sleep(2000);
	
	
	
	//7. Enter the zipcode as 22182 and click Next
	driver.findElement(By.xpath("//input [@type='tel']")).sendKeys("22182");
	driver.findElement(By.xpath("//button [@type='submit']")).click();
	Thread.sleep(2000);
	
	
	
	//8. Verify that the page has “Step 2 – Show me cars with” text
	String textStep2 = driver.findElement(By.xpath("//*[contains(text(), 'Step 2 - Show')]")).getText();
	if(textStep2.contains("Step 2 - Show me cars with")) {
		System.out.println("Test case #8; Page contains \"Step 2 - Show me cars with\" text");
	}else {
		System.out.println("Test case #8: Page DOES NOT contains \"Step 2 - Show me cars with\"");
	}
	Thread.sleep(2000);
	
	
	
	//9. Click on all 4 checkboxes.
	List <WebElement> checkboxes = driver.findElements(By.xpath("//span[@class=\"checkbox-list-item--fancyCbx\"]"));

	for (WebElement webElement : checkboxes) {
		if(!webElement.isSelected()) {
			webElement.click();
			
		}
	}


	
	//10. Save the result of “Show me X Results” button to a variable. In this case it is 6.
	String results = driver.findElement(By.xpath("//span [@class='totalRecordsText']")).getText();
	System.out.println(results);
	
		
		
	//11. Click on “Show me x Results” button. 
	driver.findElement(By.xpath("//button[@class='button large primary-green']")).click();
	
	
	
	//12. On the next page, verify that the results page has the same number of results as indicated in 
	//Step 10 by extracting the number and comparing the result
	String actualResults = driver.findElement(By.xpath("//span [@id='totalResultCount']")).getText();

	System.out.println(actualResults);
	if(results.equals(actualResults)) {
		System.out.println("Test case #12 Pass");
	}else {
		System.out.println("Test case #12 Fail");
	}
	Thread.sleep(2000);
	
	
	
	//13. Verify the results also by getting the actual number of results displayed in the page with
	//the number in the Step 10. For this step get the count the number of WebElements related to each result.
	List <WebElement> searchDisplayedResults = driver.findElements(By.xpath("//article [@class='srp-list-item']"));
	System.out.println(searchDisplayedResults.size());

	String size = "" + searchDisplayedResults.size();
	if(results.equals(size)){
		System.out.println("Test case #13 Pass - Actual number of results displayed in the page is the same as in the previous page");
	}else {
		System.out.println("Test case #13 Fail - Actual number of results displayed in the page is NOT the same as in the previous page");
	}
	Thread.sleep(2000);
	
	
	
	//14. Verify that each result contains String “Tesla Model S”.
		
	List <WebElement> searchDisplayedResultsNew = driver.findElements(By.xpath("//h4 [@class='srp-list-item-basic-info-model']"));
	boolean pass1 = true;
	for (int i=0; i<searchDisplayedResultsNew.size(); i++) {
		String teslaModelS = searchDisplayedResultsNew.get(i).getText();
		System.out.println(teslaModelS);
		
		if(teslaModelS.contains("Tesla Model S")) {
		pass1=true;
	}else {
		pass1 = false;
	}
	}
	if (pass1==true) {
		System.out.println("Test case #14 Pass and each search result contains \"Tesla Model S\"");
			}else {
				System.out.println("Test case #14 FAIL and some search result DOES NOT contain \"Tesla Model S\"");
			}


	//15. Get the price of each result and save them into a list in the order of their appearance.
	List <WebElement> priceOfEachResult = driver.findElements(By.xpath("//span[@class='srp-list-item-price']"));
	for (int i=0; i<priceOfEachResult.size(); i++) {
		String price = priceOfEachResult.get(i).getText();
		System.out.println(price);
	}

		
		
	//16. Choose “Price - High to Low” option from Sort menu
   WebElement k = driver.findElement(By.xpath("//select[@class='srp-header-sort-select']"));
	Thread.sleep(2000);
	Select priceOptionsSearch = new Select(k);
	priceOptionsSearch.selectByVisibleText("Price - High to Low ");
	Thread.sleep(2000);

	
		
	//17. Verify that the results are displayed from high to low price. 
//	String expectedResultsHighToLowPrice = "Price - High to Low ";
	List <WebElement> priceOfEachResult2 = driver.findElements(By.xpath("//span[@class='srp-list-item-price']"));
	
	boolean pass2 = false;
	for (int i=1; i<priceOfEachResult2.size(); i++) {
		String price1 = priceOfEachResult2.get(i-1).getText().substring(8).replace(",", "");
		double pr1 = Double.parseDouble(price1);
		String price2 = priceOfEachResult2.get(i).getText().substring(8).replace(",", "");
		double pr2 = Double.parseDouble(price2);

		if(pr1>pr2) {
	     pass2=true;
	}else {
		pass2 = false;
		break;
	}
	}
	if (pass2==true) {
		System.out.println("Test case #17 Pass hard test");
			}else {
				System.out.println("Test case #17 fail hard test");
			}
	
	    
	
	//18. Choose “Mileage - Low to High” option from Sort menu
	WebElement n = driver.findElement(By.xpath("//select[@class='srp-header-sort-select']"));
	 Thread.sleep(2000);
		Select mileageOptionsSearch = new Select(n);
		mileageOptionsSearch.selectByVisibleText("Mileage - Low to High");
		Thread.sleep(2000);

		
	//19. Verify that the results are displayed from low to high mileage. 

		List<WebElement> spans = driver.findElements(By.xpath("//span"));
	   	List<Integer> mileage = new ArrayList<>();
	   	for(int j=0;j<spans.size();j++) {
	   		if(spans.get(j).getText().contains("Mileage")) {
	   			mileage.add(Integer.parseInt(spans.get(j).getText().substring(9, 15).replace(",", "")));
	   		}
	   	}Thread.sleep(2000);
	   	System.out.println(mileage);
	   	boolean pass3 = false;
	   	for(int i=0; i<mileage.size()-1; i++) {
	   		if(mileage.get(i)<mileage.get(i+1)) {
	   			pass3=true;
	   		}else {
			pass3 = false;
			break;
		}
		}
		if (pass3==true) {
			System.out.println("Test case #19 Pass test");
				}else {
					System.out.println("Test case #19 fail test");
	   	
				}
		Thread.sleep(2000);
	   	
	//20. Choose “Year - New to Old” option from Sort menu
		WebElement j = driver.findElement(By.xpath("//select[@class='srp-header-sort-select']"));
		 Thread.sleep(2000);
			Select yearOptionsSearch = new Select(j);
			yearOptionsSearch.selectByVisibleText("Year - New to Old");
			Thread.sleep(2000);
			
			
	//21. Verify that the results are displayed from new to old year.
			List <WebElement> yearNewToOld = driver.findElements(By.xpath("//h4[@class='srp-list-item-basic-info-model']"));
			Thread.sleep(2000);
			for(int i=0; i<yearNewToOld.size(); i++) {
				String year = yearNewToOld.get(i).getText().substring(0,4);
				System.out.println(year);
				Thread.sleep(1000);
			}
//			OR you can print it this way:
//			
//			List<Integer> years = new ArrayList<>();
//		       for(WebElement w:yearNewToOld) {
//	    	   years.add(Integer.parseInt(w.getText().substring(0, 4)));
//	    	   Thread.sleep(1000);
//	       }
//	       System.out.println(years);
//	       Thread.sleep(1000);
			
			boolean pass4 = false;
			for (int i=1; i<yearNewToOld.size(); i++) {
				String year1 = yearNewToOld.get(i-1).getText().substring(0,4);
				int ye1 = Integer.parseInt(year1);
				String year2 = yearNewToOld.get(i).getText().substring(0,4);
				int ye2 = Integer.parseInt(year2);

				if(ye1>=ye2) {
			     pass4=true;
			}else {
				pass4 = false;
				break;
			}
			}
			if (pass4==true) {
				System.out.println("Test case #21 Pass hard test");
					}else {
						System.out.println("Test case #21 fail hard test");
					}
		       		
	}}
	