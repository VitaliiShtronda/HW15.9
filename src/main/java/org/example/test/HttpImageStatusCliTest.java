package org.example.test;
import org.example.main.HttpImageStatusCli;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.concurrent.Executor;


import static org.example.main.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

class HttpImageStatusCliTest {
    static HttpImageStatusCli CLI;

    @BeforeAll
    static void beforeAll() {
        CLI = new HttpImageStatusCli();
    }
    @Test
    public void testAskStatusWithValidStatusCode() {
        String input = "200\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HttpImageStatusCli httpImageStatusCli = new HttpImageStatusCli();
        httpImageStatusCli.askStatus();

        String expectedOutput = "Image downloaded successfully!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testAskStatusWithInvalidStatusCode() {
        String input = "test\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HttpImageStatusCli httpImageStatusCli = new HttpImageStatusCli();
        httpImageStatusCli.askStatus();

        String expectedOutput = "Please enter a valid number.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}

