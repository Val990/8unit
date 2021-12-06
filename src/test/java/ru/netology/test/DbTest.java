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
    void shouldSendRequestHappyPath() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldSendWrongCode() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.invalidVerify(DataGenerator.getInvalidCode().getCode());
    }

    @Test
    void shouldSendWrongLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var invalidLogin = DataGenerator.getInvalidLogin();
        loginPage.invalidLogin(DataGenerator.getInvalidLogin());
    }

    @Test
    void shouldSendWrongPassword3Times() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var invalidPassword = DataGenerator.getInvalidPassword();
        loginPage.invalidPassword3Times(DataGenerator.getInvalidPassword());
    }
}

