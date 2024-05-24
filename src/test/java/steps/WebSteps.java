package steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открыть репозиторий {repo}")
    public void openRepository(String repo) {
        open(repo);
    }

    @Step("Открыть вкладку Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Кликнуть на задачу {issueName}")
    public void openIssue(String issueName) {
        $(linkText(issueName)).click();
    }

    @Step("Убедиться, что на странице задачи заголовок задачи {issueName}")
    public void shouldSeeIssueName(String issueName) {
        $(".js-issue-title.markdown-title").shouldHave(text(issueName));
    }

}
