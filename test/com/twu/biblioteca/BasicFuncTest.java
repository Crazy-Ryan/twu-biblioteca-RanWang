package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BasicFuncTest {

    private ByteArrayOutputStream outContent;

    @Before
    public void initAll() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testGreeting() {
        UserInterface userInterface = new UserInterface();
        userInterface.greet();
        String expectedOutput = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testViewingBookList() {
        BookService bookService = new BookService();
        String expectedOutput = "[Book{id=1, title='Pride and Prejudice', author='Jane Austen', publicationYear=1813}, " +
                "Book{id=2, title='Jane Eyre', author='Charlotte Brontë', publicationYear=1847}, " +
                "Book{id=3, title='Gone With the Wind', author='Margaret Mitchell', publicationYear=1936}]";
        List<Book> actualOutput = bookService.getBookData();
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    public void testOptionMenu() {
        UserInterface userInterface = new UserInterface();
        String expectedOutput = "Please select an option\n" +
                "1. List of books\n"+
                "Title\t| Author\t| Publication Year\n" +
                "Pride and Prejudice\t| Jane Austen\t| 1813\n" +
                "Jane Eyre\t| Charlotte Brontë\t| 1847\n" +
                "Gone With the Wind\t| Margaret Mitchell\t| 1936\n";
        String str = "1";
        ByteArrayInputStream strIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(strIn);
        userInterface.selectOptionOnMenu();
        assertEquals(expectedOutput, outContent.toString());
    }
    @Test
    public void testInvalidOptionMenu() {
        UserInterface userInterface = new UserInterface();
        String expectedOutput = "Please select an option\n" +
                "1. List of books\n"+
                "Please select a valid option!\n";
        String str = "0";
        ByteArrayInputStream strIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(strIn);
        userInterface.selectOptionOnMenu();
        assertEquals(expectedOutput, outContent.toString());
    }
}
