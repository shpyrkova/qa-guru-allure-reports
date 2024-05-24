package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.WebSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class RepositoryIssuesTests extends TestBase {

    @Test
    @DisplayName("Тест с Selenide Listener")
    void testWithSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
        open("/junit-team/junit5");
        $("#issues-tab").click();
        $(linkText("Version reported as null on module path")).click();
        $(".js-issue-title.markdown-title").shouldHave(text("Version reported as null on module path"));
    }

    @Test
    @DisplayName("Тест с Lambda шагами")
    void testWithLambdaSteps() {
        step("Открыть репозиторий junit5", () -> {
            open("/junit-team/junit5");
        });
        step("Кликнуть на вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Кликнуть на задачу 'Version reported as null on module path'", () -> {
            $(linkText("Version reported as null on module path")).click();
        });
        step("Убедиться, что на странице задачи заголовок задачи верный", () -> {
            $(".js-issue-title.markdown-title").shouldHave(text("Version reported as null on module path"));
        });
    }

    @Test
    @DisplayName("Тест с аннотацией Step")
    void testWithAnnotations() {
        WebSteps steps = new WebSteps();

        steps.openRepository("/junit-team/junit5");
        steps.openIssuesTab();
        steps.openIssue("Version reported as null on module path");
        steps.shouldSeeIssueName("Version reported as null on module path");
    }
}
