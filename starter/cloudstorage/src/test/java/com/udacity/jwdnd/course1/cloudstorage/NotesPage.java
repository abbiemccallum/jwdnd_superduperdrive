package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {

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
    @FindBy (css="#edit-note-button")
    private WebElement editButton;
    @FindBy(css="#deleteNoteSubmit")
    private WebElement deleteSubmit;

    private WebDriver driver;

    public NotesPage(WebDriver driver) {
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", noteTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + description + "';", noteDescription);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteSubmit);
    }


    //delete note
    public void deleteNote(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteSubmit);
    }

    public void getNoteTitle(){
        getNotesTab();
        driver.findElement(By.id("note-title-table")).getAttribute("innerHTML");
    }
}
