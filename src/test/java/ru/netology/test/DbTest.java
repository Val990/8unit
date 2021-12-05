package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class DbTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @AfterAll
    static void tearDown() {
        DataGenerator.cleanData();
    }

    @Test
     void shouldSendRequestMultipleTimes() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        verificationPage.validVerify(verificationCode);

    }



}


