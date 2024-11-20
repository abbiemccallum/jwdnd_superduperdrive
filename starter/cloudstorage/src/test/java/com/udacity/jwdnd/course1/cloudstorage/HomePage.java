package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    // Note elements
    @FindBy(css = "#nav-notes-tab")
    private WebElement notesTab;
    @FindBy (css="#add-note-button")
    private WebElement addButton;
    @FindBy(css = "#note-id")
    private WebElement noteId;
    @FindBy(css = "#note-title")
    private WebElement noteTitle;
    @FindBy(css="#note-description")
    private WebElement noteDescription;
    @FindBy(css="#noteSubmit")
    private WebElement noteSubmit;
    @FindBy (css="#add-note-button")
    private WebElement editButton;
    @FindBy(css = "#edit-note-id")
    private WebElement editNoteId;
    @FindBy(css = "#edit-note-title")
    private WebElement editTitle;
    @FindBy(css="#edit-note-description")
    private WebElement editDescription;
    @FindBy(css="#editNoteSubmit")
    private WebElement editSubmit;
    @FindBy(css = "#delete-note-id")
    private WebElement deleteNoteId;
    @FindBy(css="#deleteNoteSubmit")
    private WebElement deleteSubmit;

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    // get notes tab
    public void getNotesTab(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.notesTab);
    }
    // create note
    public void createNote(String  title, String description){
        getNotesTab();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.addButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", noteTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + description + "';", noteDescription);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteSubmit);
    }

    //edit note
    public void editNote(String title, String description){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", editTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + description + "';", editDescription);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editSubmit);
    }


    //delete note
    public void deleteNote(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteSubmit);
    }
}
