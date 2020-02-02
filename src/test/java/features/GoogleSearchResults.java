package features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.page.PageTitleQuestion;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static org.hamcrest.core.Is.is;
import static questions.GoogleSearchResultsQuestion.hyperlinkOfTheElement;
import static user_interface.GoogleHomePage.*;

@RunWith(SerenityRunner.class)
public class GoogleSearchResults {
    private Actor user = new Actor("Current user");
    private final String GOOGLE_URL = "http://www.google.com";
    private final String GOOGLE_PLAY_STORE_LINK = "https://menu.app/de/";

    @Managed(driver = "chrome", uniqueSession = true)
    private WebDriver hisBrowser;

    @Before
    public void assignActorsResopnsibility(){
        user.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void userShouldSeeTheCorrectHyperlinkForTheTopResultInGoogleSearch() throws InterruptedException {
        when(user)
                .attemptsTo(Open.url(GOOGLE_URL));
        and(user)
                .attemptsTo(
                        WaitUntil.the(GOOGLE_SEARCH_INPUT_FIELD,isEnabled()).forNoMoreThan(10).seconds(),
                        Enter.theValue("MENU APP").into(GOOGLE_SEARCH_INPUT_FIELD).thenHit(Keys.ESCAPE),
                        Click.on(GOOGLE_SEARCH_BUTTON));
        then(user)
                .should(
                        seeThat(new PageTitleQuestion(),is("MENU APP - Google претрага")),
                        seeThat(hyperlinkOfTheElement(FIRST_RESULT_LINK), is(GOOGLE_PLAY_STORE_LINK)));
    }
}
