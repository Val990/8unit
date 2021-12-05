package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");

    public VerificationPage() { //новый конструктор verificationpage, обращается к codefield
        codeField.shouldBe(visible);
    }
    public DashboardPage validVerify(DataGenerator.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());  // заполняем смс,
        verifyButton.click();  // нажимаем продолжить
        return new DashboardPage();
    }

    public void invalidVerify(String verificationCode) {
        codeField.setValue(verificationCode);  // заполняем смс,
        verifyButton.click();  // нажимаем продолжить
        $(withText("Ошибка")).shouldBe(visible);
    }

}