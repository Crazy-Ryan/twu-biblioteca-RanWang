package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BibliotecaTest {
    private ByteArrayOutputStream outContent;
    private Biblioteca biblioteca;

    @Before
    public void initAll() {
        biblioteca = new Biblioteca();
        biblioteca.init();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void greetTest() {
        biblioteca.greet();
        String expectedOutput = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    public void listBookHandlerTest() {
        String expectedOutput = "Title\t| Author\t| Publication Year\n" +
                "Pride and Prejudice\t| Jane Austen\t| 1813\n" +
                "Gone With the Wind\t| Margaret Mitchell\t| 1936\n";
        biblioteca.listBookHandler();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void invalidOptionMenuTest() {
        String expectedOutput = "Please select a valid option!\n";
        biblioteca.invalidOptionHandler();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void quitHandlerTest() {
        String expectedOutput = "Please select an option\n" +
                "1. List of books\n" +
                "2. Check out a book\n" +
                "3. Return a book\n" +
                "9. Quit\n";
        String str = "9";
        ByteArrayInputStream strIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(strIn);
        biblioteca.selectOptionOnMenu();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void successfullyCheckOutBookHandlerTest() {
        String expectedOutput = "Please enter the name of the book\n" +
                "Thank you! Enjoy the book\n" +
                "Title\t| Author\t| Publication Year\n" +
                "Pride and Prejudice\t| Jane Austen\t| 1813\n";
        String str = "Gone With the Wind\n";
        ByteArrayInputStream strIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(strIn);
        biblioteca.checkOutBookHandler();
        biblioteca.listBookHandler();
        assertEquals(expectedOutput, outContent.toString());
    }
}
