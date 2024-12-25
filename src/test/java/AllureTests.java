import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class AllureTests extends TestBase{
    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-button.placeholder").click();
        $("#query-builder-test").setValue("Darya04031996/Allure_Reports").pressEnter();


        $(linkText("Darya04031996/Allure_Reports")).click();
        $("#issues-tab").click();
        $(".container-md").shouldHave(Condition.text("Welcome to issues!"));
    }
    @Test
    void lambdaSteps() {

        step("Открыта стартовая страница", () -> {
            open("https://github.com/");
        });
        step("Поиск репозитория " + TestBase.REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue("Darya04031996/Allure_Reports").pressEnter();
        });
        step("Переход в репозиторий" + TestBase.REPOSITORY, () -> {
            $(linkText("Darya04031996/Allure_Reports")).click();
        });
        step("Переход на вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверка заголовка при переходе на  Issues " + TITLE, () -> {
            $(".container-md").shouldHave(Condition.text("Welcome to issues!"));
        });

    }
    @Test
    public void testIssueSearchWithAnnotatedSteps() {

        openGitHubHomePage();
        searchRepository("Darya04031996/Allure_Reports");
        openRepository("Darya04031996/Allure_Reports");
        openIssuesTab();
        checkIssuesWelcomeText();
    }

    @Step("Открываем главную страницу GitHub")
    public void openGitHubHomePage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repoName}")
    public void searchRepository(String repoName) {
        $(".header-search-button").click();
        $("#query-builder-test").setValue(repoName).pressEnter();
    }

    @Step("Переходим на страницу репозитория {repoName}")
    public void openRepository(String repoName) {
        $(linkText(repoName)).click();
    }

    @Step("Открываем вкладку Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие текста 'Welcome to issues!'")
    public void checkIssuesWelcomeText() {
        $(".container-md").shouldHave(Condition.text("Welcome to issues!"));
    }

}