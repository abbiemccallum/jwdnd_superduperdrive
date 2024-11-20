package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	public String username = "username1";
	public String password = "thisismypassword";
	public  String firstName = "Jane";
	public String lastName ="Smith";



	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */


	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");
		
		// Check if we have been redirected to the log in page.
		assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		
		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}

	private void doLogOut()
	{
		// Log out
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonLogout")));
		WebElement logoutOutButton = driver.findElement(By.id("buttonLogout"));
		logoutOutButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Login"));

	}


	// Write a test that verifies that an unauthorized user can only access the login and signup pages.
	@Test
	public void testUnauthorizedUser() {
		// check unauthorized user can access sign up
		driver.get("http://localhost:" + this.port + "/signup");
		assertEquals("Sign Up", driver.getTitle());

		// check unauthorized user can access login
		driver.get("http://localhost:" + this.port + "/login");
		assertEquals("Login", driver.getTitle());

		// check unauthorized user can access login
		assertNotEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

	}


	// Write a test that signs up a new user, logs in,
	// verifies that the home page is accessible, logs out,
	// and verifies that the home page is no longer accessible.

	public void userSignupLogin() {
		doMockSignUp(firstName, lastName, username, password);
		doLogIn(username, password);
	}

	public void addNewNote() {
		NotesPage notesPage = new NotesPage(driver);
		createNote(notesPage);
		driver.get("http://localhost:" + this.port + "/home");
		notesPage.getNotesTab();
	}

	@Test
	public void testUserSignupLoginAndLogout() {
		userSignupLogin();
		doLogOut();
		// Check if we have been redirected after logging out
		assertNotEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
	}

	public void createNote(NotesPage notesPage){
		notesPage.createNote("Note title","Note description");

	}

	@Test
	public void testAddNewNote(){
		userSignupLogin();
		addNewNote();
		Assertions.assertEquals("Note title",  driver.findElement(By.id("note-title-table")).getAttribute("innerHTML"));

	}

	//Write a test that edits an existing note and verifies that the changes are displayed.
	@Test
	public void testEditNote(){
		userSignupLogin();
		addNewNote();
		NotesPage notesPage = new NotesPage(driver);
		notesPage.editNote("New title", "Note description");
		driver.get("http://localhost:" + this.port + "/home");
		notesPage.getNotesTab();
		Assertions.assertEquals("New title",  driver.findElement(By.id("note-title-table")).getAttribute("innerHTML"));


	}
	//Write a test that deletes a note and verifies that the note is no longer displayed.

	@Test
	public void testDeleteNote(){
		userSignupLogin();
		addNewNote();
		NotesPage notesPage = new NotesPage(driver);
		notesPage.deleteNote();
		driver.get("http://localhost:" + this.port + "/home");
		notesPage.getNotesTab();
		assertThrows(NoSuchElementException.class, notesPage::getNoteTitle);


	}
	//Credentials Test

	public void createCredential(CredentialsPage credentialsPage){
		credentialsPage.createCredentials("https://hello.com","username123", "password123");
	}

	public void addNewCredentials(){
		CredentialsPage credentialsPage = new CredentialsPage(driver);
		createCredential(credentialsPage);
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.getCredentialsTab();
	}


	//Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
	@Test
	public void testAddNewCredentials(){
		userSignupLogin();
		addNewCredentials();
		Assertions.assertEquals("https://hello.com",  driver.findElement(By.id("credential-url-table")).getAttribute("innerHTML"));
		Assertions.assertEquals("username123",  driver.findElement(By.id("credential-username-table")).getAttribute("innerHTML"));
		Assertions.assertNotEquals("password123",  driver.findElement(By.id("credential-password-table")).getAttribute("innerHTML"));
	}

	//Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
	@Test
	public void testEditCredentials(){
		userSignupLogin();
		addNewCredentials();
		CredentialsPage credentialsPage = new CredentialsPage(driver);
		credentialsPage.viewCredentials();
		Assertions.assertNotEquals("password123",  driver.findElement(By.id("credential-password-table")).getAttribute("innerHTML"));
		credentialsPage.editCredentials("https://goodbye.com", "editUsername123", "editPassword123");
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.getCredentialsTab();
		Assertions.assertEquals("https://goodbye.com",  driver.findElement(By.id("credential-url-table")).getAttribute("innerHTML"));

	}

	//Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
	@Test
	public void testDeleteCredential(){
		userSignupLogin();
		addNewCredentials();
		CredentialsPage credentialsPage = new CredentialsPage(driver);
		credentialsPage.deleteCredentials();
		driver.get("http://localhost:" + this.port + "/home");
		credentialsPage.getCredentialsTab();
		assertThrows(NoSuchElementException.class, credentialsPage::getCredentialUrl);
	}

}
