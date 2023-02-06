package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public WebDriver ldriver;
	public WaitHelper waithelper;
	
	// constructor
	// initiate driver
	public SearchCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		waithelper=new WaitHelper(ldriver);
	}
	
	// 	Locators
	//	By txtEmail_id = By.id("SearchEmail");
	//  By txtFirstName_id=By.id("SearchFirstName");
	//  By txtLastName_id=By.id("SearchLastName");
	//  By btnSearch_id=By.id("search-customers");
	//  By tblSearchResults_xpath=By.xpath("//table[@role='grid']");
	//  By table_xpath=By.xpath("//table[@id='customers-grid']");
	//  By tableRows_xpath=By.xpath("//table[@id='customers-grid']//tbody/tr");
	//  By tableColumns_xpath=By.xpath("//table[@id='customers-grid']//tbody/tr/td");
	
    @FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail_id;
    
    
	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName_id;

	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName_id;

	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch_id;

	@FindBy(how = How.XPATH, using = "//table[@role='grid']")
	@CacheLookup
	WebElement tblSearchResults_xpath;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table_xpath;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows_xpath;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> tableColumns_xpath;
    
    
    // Action Methods
    
	public void setEmail(String email) {
		waithelper.WaitForElement(txtEmail_id, 30);
		txtEmail_id.clear();
		txtEmail_id.sendKeys(email);
	}
	
	
	public void setFirstName(String fname) {
		waithelper.WaitForElement(txtFirstName_id, 30);
		txtFirstName_id.clear();
		txtFirstName_id.sendKeys(fname);
	}

	public void setLastName(String lname) {
		waithelper.WaitForElement(txtLastName_id, 30);
		txtLastName_id.clear();
		txtLastName_id.sendKeys(lname);
	}

	public void clickSearch() {
		btnSearch_id.click();
		waithelper.WaitForElement(btnSearch_id, 30);
	}
	
	public int getNoOfRows() {
		return (tableRows_xpath.size());
	}

	public int getNoOfColumns() {
		return (tableColumns_xpath.size());
	}
	
	public boolean searchCustomerByEmail(String email) {
		boolean flag = false;

		for (int i = 1; i <= getNoOfRows(); i++) {
			String emailid = table_xpath.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]"))
					.getText();

			System.out.println(emailid);

			if (emailid.equals(email)) {
				flag = true;
				break;
			}
		}
		return flag;

	}
	
	public boolean searchCustomerByName(String Name) {
		boolean flag = false;

		for (int i = 1; i <= getNoOfRows(); i++) {
			String name = table_xpath.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]"))
					.getText();

			
			if (Name.equals(name)) {
				flag = true;
				break;
			}
		}

		return flag;

	}
	
	
    
	
}
