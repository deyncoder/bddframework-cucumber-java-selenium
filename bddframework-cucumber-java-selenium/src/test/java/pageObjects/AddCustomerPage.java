package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

	public WebDriver ldriver;
	
	// constructor
	// initiate driver
	public AddCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	// Locators
	By lnkCustomers_menu_xpath = By.xpath("//a[@href='#' and @class='nav-link']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuitem_xpath = By.xpath("//a[@href='/Admin/Customer/List' and @class='nav-link']");
    By btnAddnew_xpath = By.xpath("//a[@href='/Admin/Customer/Create' and @class='btn btn-primary']");
    By txtEmail_xpath = By.xpath("//input[@id='Email']");
    By txtPassword_xpath = By.xpath("//input[@id='Password']");
    By txtFirstName_xpath = By.xpath("//input[@id='FirstName']");
    By txtLastName_xpath = By.xpath("//input[@id='LastName']");
    By rdMaleGender_id = By.id("Gender_Male");
    By rdFemaleGender_id = By.id("Gender_Female");
    By txtDob_xpath = By.xpath("//input[@id='DateOfBirth']");
    By txtCompanyName_xpath = By.xpath("//input[@id='Company']");
    By txtcustomerRoles_xpath = By.xpath("(//div[@class='k-multiselect-wrap k-floatwrap'])[2]");
    By lstitemAdministrators_xpath = By.xpath("//li[contains(text(),'Administrators')]");
    By lstitemRegistered_xpath = By.xpath("//li[contains(text(),'Registered')]");
    By lstitemGuests_xpath = By.xpath("//li[contains(text(),'Guests')]");
    By lstitemVendors_xpath = By.xpath("//li[contains(text(),'Vendors')]");
    By drpManagerOfVendor_xpath = By.xpath("//*[@id='VendorId']");
    By txtAdminComment_xpath = By.xpath("//textarea[@id='AdminComment']");
    By btnSave_xpath = By.xpath("//button[@name='save']");
    
    // Action Methods
    
    public String getPageTitle() {
		return ldriver.getTitle();
	}
    
    public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu_xpath).click();
	}

	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCustomers_menuitem_xpath).click();
	}
	
	public void clickOnAddnew() {
		ldriver.findElement(btnAddnew_xpath).click();
	}
	
	public void setEmail(String email)
	{
		ldriver.findElement(txtEmail_xpath).sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		ldriver.findElement(txtPassword_xpath).sendKeys(password);
	}
	
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstName_xpath).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastName_xpath).sendKeys(lname);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender_id).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFemaleGender_id).click();
		}
		else
		{
			ldriver.findElement(rdMaleGender_id).click();//Default
		}
		
	}
	
	public void setDob(String dob)
	{
		ldriver.findElement(txtDob_xpath).sendKeys(dob);
	}
	
	public void setCompanyName(String comname)
	{
		ldriver.findElement(txtCompanyName_xpath).sendKeys(comname);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException 
		{
			if(!role.equals("Vendors")) //If role is vendors should not delete Register as per req.
			{
			ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
			}
			
			ldriver.findElement(txtcustomerRoles_xpath).click();
						
			WebElement listitem;
			
			Thread.sleep(3000);
						
			if(role.equals("Administrators"))
			{
				listitem=ldriver.findElement(lstitemAdministrators_xpath); 
			}
			else if(role.equals("Guests"))
			{
				listitem=ldriver.findElement(lstitemGuests_xpath);
			}
			else if(role.equals("Registered"))
			{
				listitem=ldriver.findElement(lstitemRegistered_xpath);
			}
			else if(role.equals("Vendors"))
			{
				listitem=ldriver.findElement(lstitemVendors_xpath);
			}
			else
			{
				listitem=ldriver.findElement(lstitemGuests_xpath);
			}
						
			//listitem.click();
			//Thread.sleep(3000);
			
			// alternative if listitem.click() don't work
			JavascriptExecutor js = (JavascriptExecutor)ldriver;
			js.executeScript("arguments[0].click();", listitem);
			
	}

	public void setManagerOfVendor(String value)
	{
		Select drp=new Select(ldriver.findElement(drpManagerOfVendor_xpath));
		drp.selectByVisibleText(value);
	}
	
	public void setAdminComment(String comment)
	{
		ldriver.findElement(drpManagerOfVendor_xpath).sendKeys(comment);
	}
	
	public void clickOnSave()
	{
		ldriver.findElement(btnSave_xpath).click();
	}
    

}
