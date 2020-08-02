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
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.greet();
        String expectedOutput = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testViewingBookList() {
        BookService bookService = new BookService();
        String expectedOutput = "[Book{id=1, title='Pride and Prejudice', author='Jane Austen', publicationYear=1813}, " +
                "Book{id=2, title='Jane Eyre', author='Charlotte Brontë', publicationYear=1847}, " +
                "Book{id=3, title='Gone With the Wind', author='Margaret Mitchell', publicationYear=1936}]";
        List<Book> actualOutput = bookService.getBooks();
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    public void testDisplayingOptionMenu() {
        Biblioteca biblioteca = new Biblioteca();
        String expectedOutput = "Please select an option\n" +
                "1. List of books\n";
        biblioteca.displayOptions();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testOptionMenu() {
        Biblioteca biblioteca = new Biblioteca();
        String expectedOutput = "Title\t| Author\t| Publication Year\n" +
                "Pride and Prejudice\t| Jane Austen\t| 1813\n" +
                "Jane Eyre\t| Charlotte Brontë\t| 1847\n" +
                "Gone With the Wind\t| Margaret Mitchell\t| 1936\n";
        String str = "1";
        ByteArrayInputStream strIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(strIn);
        biblioteca.selectOptionOnMenu();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testInvalidOptionMenu() {
        Biblioteca biblioteca = new Biblioteca();
        String expectedOutput = "Please select a valid option!\n";
        String str = "0";
        ByteArrayInputStream strIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(strIn);
        biblioteca.selectOptionOnMenu();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testQuittingTheApplication() {
        Biblioteca biblioteca = new Biblioteca();
        String expectedOutput = "";
        String str = "9";
        ByteArrayInputStream strIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(strIn);
        biblioteca.selectOptionOnMenu();
        assertEquals(expectedOutput, outContent.toString());
    }

//    @Test
//    public void testCheckingOutABook() {
//        UserInterface userInterface = new UserInterface();
//        String expectedOutput = "Title\t| Author\t| Publication Year\n" +
//                "Pride and Prejudice\t| Jane Austen\t| 1813\n" +
//                "Jane Eyre\t| Charlotte Brontë\t| 1847\n";
//        String str = "1";
//        ByteArrayInputStream strIn = new ByteArrayInputStream(str.getBytes());
//        System.setIn(strIn);
//        userInterface.checkOutABook("Gone With the Wind");
//        userInterface.selectOptionOnMenu();
//        assertEquals(expectedOutput, outContent.toString());
//    }
}
