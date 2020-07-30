package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

}
