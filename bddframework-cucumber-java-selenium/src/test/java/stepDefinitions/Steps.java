package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;  // import all keywords using *
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;


public class Steps extends BaseClass{
	
	// Hooks only execute one time before starting the test cases
	@Before
	public void setup() throws IOException {
		
		logger=Logger.getLogger("bddframework-cucumber-java-selenium");
		PropertyConfigurator.configure("log4j.properties");
		
		//Reading properties files
		configProp=new Properties();	// Create configProp object using Properties Class
		FileInputStream configPropfile =  new FileInputStream("config.properties");	// open config.properties file in input  mode
		configProp.load(configPropfile); 	// load config.properties file using load
		
		String br=configProp.getProperty("browser");
		
		if (br.equals("chrome")){
		System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
		driver=new ChromeDriver();
		}
		else if (br.equals("edge")) {
		System.setProperty("webdriver.edge.driver",configProp.getProperty("edgepath"));
		driver=new EdgeDriver();
		}
		else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
			driver=new FirefoxDriver();
		}

		driver.manage().window().maximize();
		logger.info("********** Launching browser **********");
	}
	
	
	// Launch Chrome Browser
	@Given("User Launch Chrome Browser")
	public void user_Launch_Chrome_Browser() {
		
		
		
		lp = new LoginPage(driver);
	}

	// Go to URL
	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		logger.info("********** Opening URL **********");
		driver.get(url);
	}

	// Enter Email and Password
	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		logger.info("********** Providing login details **********");
		lp.setEmail(email);
		lp.setPassword(password);
	}

	// Click Login
	@When("Click on Login")
	public void click_on_Login() throws InterruptedException {
		logger.info("********** Started login **********");
		lp.clickLogin();
		Thread.sleep(3000);
	}	

	// Verify page title
	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) throws InterruptedException {
		
		if(driver.getPageSource().contains(title)){
			Assert.assertTrue(true);
			logger.info("********** Page title verified **********");
			}
		else{
			driver.close();
			logger.error("********** Page title verification failed **********");
			logger.error("********** Page title should be \"" + title +"\" **********");
			Assert.assertEquals(title, driver.getTitle());
			}
		Thread.sleep(3000);
		
	}

	// Click Logout
	@When("User Click on Log out link")
	public void user_Click_on_Log_out_link() throws InterruptedException {
		logger.info("********** Click on logout link **********");
		lp.clickLogout();
	    Thread.sleep(3000);
	}

	
	// Close browser
	@Then("Close browser")
	public void Close_browser() {
		logger.info("********** Logout successful **********");
		logger.info("********** Closing browser **********");
		driver.quit();
	}
	
	// -------------------- Customer Feature Step Definitions ----------------------------------------------
	
	
	// View Dashboard
	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {
		addCust=new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	// Click Customers menu
	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu();
	}

	// Click Customer menu item
	@When("Click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	}

	// Click Add new button
	@When("Click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {
		addCust.clickOnAddnew();
		Thread.sleep(2000);
	}

	// View Add new customer page
	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	// Enter customer info
	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("********** Adding new customer **********");
		logger.info("********** Providing customer details **********");
		String email = randomString() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		addCust.setCustomerRoles("Guest");
		Thread.sleep(3000);
		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Juan");
		addCust.setLastName("Cruz");
		addCust.setDob("1/1/2000"); // Format: D/MM/YYY
		addCust.setCompanyName("ABC");
		addCust.setAdminComment("This is for testing.........");
	}

	// Click Save button
	@When("Click on Save button")
	public void click_on_Save_button() throws InterruptedException {
		logger.info("********** Saving customer data **********");
		addCust.clickOnSave();
		Thread.sleep(2000);
	}

	// View Confirmation message
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));
	}
	
	// ----------------- Steps for Searching Customer by Email ---------------
	
	@When("Enter customer Email")
	public void enter_customer_Email() {
		logger.info("********** Searching customer by email **********");
		searchCust=new SearchCustomerPage(driver);
//		searchCust.clickSearchRow();
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
		logger.info("********** Email entered **********");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		logger.info("********** Searching button clicked **********");
		Thread.sleep(3000);
	}

	@Then("User Email should be found in the Search table")
	public void user_should_found_Email_in_the_Search_table() {
		boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
		logger.info("********** Searching customer by email passed **********");
	}
	
	// ----------------- Steps for Searching Customer by Name ---------------
	
	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		logger.info("********** Searching customer by name **********");
		searchCust=new SearchCustomerPage(driver);
//		searchCust.clickSearchRow();
		searchCust.setFirstName("Victoria");
		logger.info("********** First name entered **********");
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		searchCust.setLastName("Terces");
		logger.info("********** Last name entered **********");
	}

	@Then("User Name should be found in the Search table")
	public void user_should_found_Name_in_the_Search_table() {
		boolean status=searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
		logger.info("********** Searching customer by name passed **********");
	}
	

}
