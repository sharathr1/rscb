package com.ge.dt.digitaltwin;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("JUnit 5 Example")
class JUnit5ExampleTest {
 
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test methods");
    }
 
    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test method");
    }
 
    @AfterEach
    void afterEach() {
        System.out.println("After each test method");
    }
 
    @AfterAll
    static void afterAll() {
        System.out.println("After all test methods");
    }
 
    @Test
    @DisplayName("First test")
    void firstTest() {
        System.out.println("First test method");
    }
 
    @Test
    @DisplayName("Second test")
    void secondTest() {
        System.out.println("Second test method");
    }
}
