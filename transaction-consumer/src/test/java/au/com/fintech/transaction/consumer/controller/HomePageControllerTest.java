package au.com.fintech.transaction.consumer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomePageControllerTest {

    private HomePageController controller;

    @BeforeEach
    void setUp() {
        controller = new HomePageController();
    }

    @Test
    void shouldRenderHomePage() {
        assertEquals("home", controller.home());
    }
}