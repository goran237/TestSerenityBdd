package user_interface;
import net.serenitybdd.screenplay.targets.Target;

public class GoogleHomePage {
    public static Target GOOGLE_SEARCH_INPUT_FIELD = Target.the("Google search input field").locatedBy("//input[@name='q']");
    public static Target GOOGLE_SEARCH_BUTTON = Target.the("Google search button").locatedBy("//input[contains(@value,Google) and @type='submit' and @name='btnK']");
    public static Target FIRST_RESULT_LINK = Target.the("Top result's link").locatedBy("//div[@class='srg']/div[1]/div/div/div/a");
}
