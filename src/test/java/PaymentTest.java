import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.DataHelper;
import helpers.SqlHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {

    private MainPage mainPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
    }

    @BeforeEach
    void setUp() {
        mainPage = new MainPage();
        mainPage.openPage();

        mainPage.selectPayment();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    @DisplayName("S1: Успешная оплата с валидной APPROVED-карты")
    void shouldSuccessWithApprovedCard() {
        mainPage.fillForm(DataHelper.getApprovedCard());
        mainPage.checkSuccessNotification();
    }

    @Test
    @DisplayName("S2: Отклонение оплаты с валидной DECLINED-карты")
    void shouldDeclineWithDeclinedCard() {
        mainPage.fillForm(DataHelper.getDeclinedCard());
        mainPage.checkErrorNotification();

        String status = SqlHelper.getLastPaymentStatus();
        assertEquals("DECLINED", status);
    }

    @Test
    @DisplayName("S3: Отправка формы с пустым полем 'Номер карты'")
    void shouldShowErrorWithEmptyCard() {
        mainPage.fillForm(DataHelper.getEmptyCardNum());
        mainPage.checkCardNumberError("Неверный формат");
    }

    @Test
    @DisplayName("S4: Отправка формы с неполным номером карты")
    void shouldShowErrorWithShortCard() {
        mainPage.fillForm(DataHelper.getShortCardNum());
        mainPage.checkCardNumberError("Неверный формат");
    }

    @Test
    @DisplayName("S5: Отправка формы с несуществующим номером карты")
    void shouldShowErrorWithInvalidCard() {
        mainPage.fillForm(DataHelper.getInvalidCardNum());
        mainPage.checkErrorNotification();
    }

    @Test
    @DisplayName("S6: Отправка формы с пустым полем 'Месяц'")
    void shouldShowErrorWithEmptyMonth() {
        mainPage.fillForm(DataHelper.getEmptyMonth());
        mainPage.checkMonthError("Неверный формат");
    }

    @Test
    @DisplayName("S7: Отправка формы с невалидным значением месяца")
    void shouldShowErrorWithInvalidMonth() {
        mainPage.fillForm(DataHelper.getInvalidMonth());
        mainPage.checkMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("S8: Отправка формы с истекшим сроком действия карты")
    void shouldShowErrorWithExpiredMonth() {
        mainPage.fillForm(DataHelper.getExpiredMonth());
        mainPage.checkYearError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("S9: Отправка формы с пустым полем 'Год'")
    void shouldShowErrorWithEmptyYear() {
        mainPage.fillForm(DataHelper.getEmptyYear());
        mainPage.checkYearError("Неверный формат");
    }

    @Test
    @DisplayName("S10: Отправка формы с истекшим годом")
    void shouldShowErrorWithExpiredYear() {
        mainPage.fillForm(DataHelper.getExpiredYear());
        mainPage.checkYearError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("S11: Отправка формы с пустым полем 'Владелец'")
    void shouldShowErrorWithEmptyHolder() {
        mainPage.fillForm(DataHelper.getEmptyHolder());
        mainPage.checkHolderError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("S12: Отправка формы с именем владельца на кириллице")
    void shouldShowErrorWithCyrillicHolder() {
        mainPage.fillForm(DataHelper.getCyrillicHolder());
        mainPage.checkHolderError("Неверный формат");
    }

    @Test
    @DisplayName("S13: Отправка формы с цифрами в поле 'Владелец'")
    void shouldShowErrorWithNumericHolder() {
        mainPage.fillForm(DataHelper.getNumericHolder());
        mainPage.checkHolderError("Неверный формат");
    }

    @Test
    @DisplayName("S14: Отправка формы с пустым полем 'CVC/CVV'")
    void shouldShowErrorWithEmptyCvc() {
        mainPage.fillForm(DataHelper.getEmptyCvc());
        mainPage.checkCvcError("Неверный формат");
    }

    @Test
    @DisplayName("S15: Отправка формы с неполным CVC/CVV")
    void shouldShowErrorWithShortCvc() {
        mainPage.fillForm(DataHelper.getShortCvc());
        mainPage.checkCvcError("Неверный формат");
    }

    @Test
    @DisplayName("S16: Проверка корректности записи в БД при успешной оплате")
    void shouldSaveCorrectDataForApprovedPayment() {
        mainPage.fillForm(DataHelper.getApprovedCard());
        mainPage.checkNotification();

        String status = SqlHelper.getLastPaymentStatus();
        assertEquals("APPROVED", status);
    }

    @Test
    @DisplayName("S17: Проверка корректности записи в БД при отклоненной оплате")
    void shouldSaveCorrectDataForDeclinedPayment() {
        mainPage.fillForm(DataHelper.getDeclinedCard());
        mainPage.checkNotification();

        String status = SqlHelper.getLastPaymentStatus();
        assertEquals("DECLINED", status);
    }
}