package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Pages.ProfilePage;
import FunctionalTests.Testing.ChangeMailTest;
import UsedByAll.Config;
import UsedByAll.GmailMessager;
import UsedByAll.TestUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/25/2014.
 */
public class ChangeMailSingleTest extends ChangeMailTest{
    @Test
    public void changeMailSingleTest(TestUser testUser) throws IOException, MessagingException {
        GmailMessager gmailMessager = new GmailMessager();
        LogInPage loginPage = new LogInPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        String confirmOldMailLink = Config.getConfig().getScheme() + "users/email/checkOldConfirmation/";
        String confirmNewMailLink = Config.getConfig().getScheme() + "users/email/checkNewConfirmation/";

        String currentMessageTime = "";
        String newMessageTime = "";



        loginPage.open();
        assertTrue("Page not opened", loginPage.isOpened());

        loginPage.goLogin(testUser);
        Assert.assertEquals(loginPage.getTitle(), "KairosNet"); // логінимось

        profilePage.goProfilePage(); // заходимо на сторінку профіля
        currentMessageTime = gmailMessager.getLastMessageTime(testUser, testUser.getEmail()); //дата останнього листа
        profilePage.addNewEmail(testUser);  //додаємо другу пошту
        assertTrue("Second mail is not added", profilePage.isOpened());
        int count = 1;
        do{ //стара пошта
            newMessageTime = gmailMessager.getLastMessageTime(testUser, testUser.getEmail());
            count++;
            if(count == 30) break;
        }while(currentMessageTime.equals(newMessageTime)); // обновляемо пошту до моменту, коли не прийде лист, або до оверфлова лічильника
        String changeConfirmationLink = gmailMessager.openAndReturnLink(testUser, testUser.getEmail(), "Email change confirmation", confirmOldMailLink, " "); //витягуємо з листа лінк

        currentMessageTime = gmailMessager.getLastMessageTime(testUser, testUser.getNewEmail()); //заходимо на нову пошту, дата останнього листа;

        assertEquals(profilePage.newMailConfirmation(changeConfirmationLink), "Check your new email"); //перейшли по лінку з листа, повинен відправитись лист на нову пошту
        count = 1;
        do{ //нова пошта
            newMessageTime = gmailMessager.getLastMessageTime(testUser, testUser.getNewEmail());
            count++;
            if(count == 30) break;
        }while(currentMessageTime.equals(newMessageTime)); // обновляемо пошту до моменту, коли не прийде лист, або до оверфлова лічильника

        String newMailConfirmLink = gmailMessager.openAndReturnLink(testUser, testUser.getNewEmail(), "Main Email confirmation", confirmNewMailLink, " "); //витягуємо лінк з листа
        assertEquals(profilePage.newChangedEmail(newMailConfirmLink), "Main E-mail has been changed"); //переход по лінку з листа і перевірка

        //перевіряємо зміну пошт
        profilePage.goProfilePage();
        assertEquals(profilePage.getEmail(), testUser.getNewEmail());
        assertEquals(profilePage.getSecondEmail(), testUser.getEmail());
        System.out.println("Почта успешно изменена из " +testUser.getEmail() +" на "+testUser.getNewEmail());

    }
    @After
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }
}