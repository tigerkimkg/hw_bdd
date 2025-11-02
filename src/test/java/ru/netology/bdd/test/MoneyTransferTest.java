package ru.netology.bdd.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.bdd.page.LoginPageV2;
import ru.netology.bdd.page.TransferPage;

import static ru.netology.bdd.data.DataHelper.*;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var info = getAuthInfo();
        var verificationCode = getVerificationCodeFor(info);
        var cardToTransferInfo = getFirstCardInfo();
        var cardFromTransferInfo = getSecondCardInfo();

        var loginPage = Selenide.open("http://localhost:9999", LoginPageV2.class);
        var verificationPage = loginPage.validLogin(info);
        var dashBoardPage = verificationPage.validVerify(verificationCode);

        var balanceCardFromTransfer = dashBoardPage.getCardBalance(cardFromTransferInfo);
        var balanceCardToTransfer = dashBoardPage.getCardBalance(cardToTransferInfo);
        var amount = Math.abs(balanceCardFromTransfer / 10);
        var balanceCardFromAfterTransfer = String.valueOf(balanceCardFromTransfer - amount);
        var balanceCardToAfterTransfer = String.valueOf(balanceCardToTransfer + amount);

        var transferPage = dashBoardPage.selectCard(cardToTransferInfo);
        var dashBoardPageAfterTransfer = transferPage.validTransfer(amount, cardFromTransferInfo.getNumber());
        dashBoardPageAfterTransfer.checkBalanceCardAfterTransfer(cardFromTransferInfo, balanceCardFromAfterTransfer);
        dashBoardPageAfterTransfer.checkBalanceCardAfterTransfer(cardToTransferInfo, balanceCardToAfterTransfer);

    }
}
