package ru.netology.bdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.bdd.data.DataHelper.*;

public class DashBoardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private final SelenideElement header = $("[data-test-id=dashboard]");

    public DashBoardPage() {
        header.shouldBe(Condition.visible);
    }

    private SelenideElement getCard(DataHelper.CardInfo cardInfo) {
        return cards.find(Condition.attribute("data-test-id", cardInfo.getTestId()));
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = getCard(cardInfo).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void checkBalanceCardAfterTransfer(DataHelper.CardInfo cardInfo, String balance) {
        getCard(cardInfo).shouldHave(Condition.matchText(balance))
                .shouldBe(Condition.visible);
    }

    public TransferPage selectCard(DataHelper.CardInfo cardInfo) {
        getCard(cardInfo).$("button").click();
        return new TransferPage();
    }

}
