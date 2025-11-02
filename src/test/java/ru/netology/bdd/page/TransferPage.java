package ru.netology.bdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.data.DataHelper.*;

import static com.codeborne.selenide.Selenide.$;
import static ru.netology.bdd.data.DataHelper.*;

public class TransferPage {
    private final SelenideElement amountField = $("[data-test-id='amount'] input");
    private final SelenideElement fromCardField = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");

    public TransferPage() {
        amountField.should(Condition.visible);
    }

    public DashBoardPage validTransfer(int amount, String number) {
        amountField.setValue(String.valueOf(amount));
        fromCardField.setValue(number);
        transferButton.click();
        return new DashBoardPage();
    }
}
