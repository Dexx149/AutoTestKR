package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.DataHelper.CardInfo;
import helpers.DataHelper;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {


    private SelenideElement buyButton = $$("button").findBy(Condition.exactText("Купить"));

    private SelenideElement cardNumberField = $x("//span[text()='Номер карты']/following::input[1]");
    private SelenideElement monthField = $x("//span[text()='Месяц']/following::input[1]");
    private SelenideElement yearField = $x("//span[text()='Год']/following::input[1]");
    private SelenideElement holderField = $x("//span[text()='Владелец']/following::input[1]");
    private SelenideElement cvcField = $x("//span[text()='CVC/CVV']/following::input[1]");
    private SelenideElement continueButton = $$("button").findBy(Condition.exactText("Продолжить"));

    private SelenideElement notification = $(".notification");
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");

    private SelenideElement cardNumberError = $x("//span[text()='Номер карты']/following::span[contains(@class, 'input__sub')]");
    private SelenideElement monthError = $x("//span[text()='Месяц']/following::span[contains(@class, 'input__sub')]");
    private SelenideElement yearError = $x("//span[text()='Год']/following::span[contains(@class, 'input__sub')]");
    private SelenideElement holderError = $x("//span[text()='Владелец']/following::span[contains(@class, 'input__sub')]");
    private SelenideElement cvcError = $x("//span[text()='CVC/CVV']/following::span[contains(@class, 'input__sub')]");

    public MainPage openPage() {
        com.codeborne.selenide.Selenide.open("http://localhost:8080");
        return this;
    }

    public MainPage selectPayment() {
        buyButton.click();
        return this;
    }

    public void fillForm(DataHelper.CardInfo card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        holderField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public void checkNotification() {
        notification.shouldBe(Condition.visible);
    }

    public void checkSuccessNotification() {
        successNotification.shouldBe(Condition.visible);
    }

    public void checkErrorNotification() {
        errorNotification.shouldBe(Condition.visible);
    }

    public void checkCardNumberError(String expectedError) {
        cardNumberError.shouldHave(Condition.exactText(expectedError));
    }

    public void checkMonthError(String expectedError) {
        monthError.shouldHave(Condition.exactText(expectedError));
    }

    public void checkYearError(String expectedError) {
        yearError.shouldHave(Condition.exactText(expectedError));
    }

    public void checkHolderError(String expectedError) {
        holderError.shouldHave(Condition.exactText(expectedError));
    }

    public void checkCvcError(String expectedError) {
        cvcError.shouldHave(Condition.exactText(expectedError));
    }
}