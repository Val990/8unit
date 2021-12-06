package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement login = $("[data-test-id='login'] input"); //логин
    private SelenideElement password = $("[data-test-id='password'] input"); //пароль
    private SelenideElement loginButton = $("[data-test-id='action-login']"); // кнопка продолжить

    public LoginPage() {
        loginButton.shouldBe(visible);
        password.shouldBe(visible);
        loginButton.shouldBe(visible);
    }

    public VerificationPage validLogin(DataGenerator.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin(DataGenerator.AuthInfo info) {
        Faker faker = new Faker();
        login.setValue(faker.name().username());
        password.setValue(info.getPassword());
        loginButton.click();
        $(withText("Ошибка")).shouldBe(Condition.visible);
    }

    public void invalidPassword3Times(DataGenerator.AuthInfo info) {
        Faker faker = new Faker();
        login.setValue(info.getLogin());
        password.setValue(faker.internet().password());
        loginButton.click();
        password.sendKeys(Keys.DELETE);
        password.setValue(faker.internet().password());
        loginButton.click();
        password.sendKeys(Keys.DELETE);
        password.setValue(faker.internet().password());
        loginButton.click();
        $(withText("Превышено число попыток")).shouldBe(Condition.visible);
    }

}

