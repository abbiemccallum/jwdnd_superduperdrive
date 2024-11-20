package com.udacity.jwdnd.course1.cloudstorage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CredentialsPage {

    // Credentials elements
    @FindBy(css = "#nav-credentials-tab")
    private WebElement credentialsTab;
    @FindBy (css="#add-credential-button")
    private WebElement credentialButton;
    @FindBy(css = "#note-id")
    private WebElement noteId;
    @FindBy(css = "#credential-url")
    private WebElement credentialurl;
    @FindBy(css="#credential-username")
    private WebElement credentialusername;
    @FindBy(css="#credential-password")
    private WebElement credentialpassword;
    @FindBy(css="#credentialSubmit")
    private WebElement credentialSubmit;
    @FindBy (css="#edit-credentials-button")
    private WebElement editCredentialsButton;
    @FindBy(css="#deleteCredentialSubmit")
    private WebElement deleteCredentialSubmit;
    private WebDriver driver;

    public CredentialsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void getCredentialsTab(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.credentialsTab);
    }

    public void createCredentials(String  url, String username, String password){
        getCredentialsTab();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.credentialButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", credentialurl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", credentialusername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", credentialpassword);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialSubmit);
    }

    public void editCredentials(String  url, String username, String password){
        getCredentialsTab();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editCredentialsButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", credentialurl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", credentialusername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", credentialpassword);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialSubmit);
    }

    public void deleteCredentials(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteCredentialSubmit);
    }

    public void getCredentialUrl(){
        getCredentialsTab();
        driver.findElement(By.id("credential-url-table")).getAttribute("innerHTML");
    }

    public void viewCredentials(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editCredentialsButton);
    }

}
