package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Attribute;
import net.serenitybdd.screenplay.targets.Target;

public class GoogleSearchResultsQuestion {

    public static Question<String> hyperlinkOfTheElement(Target targetElement){
        return new Question<String>() {
            private Target target_ = targetElement;
            @Subject("Hyperlink of the target element #target_")
            @Override
            public String answeredBy(Actor actor) {
                return Attribute.of(targetElement).named("href").viewedBy(actor).resolve().toString();
            }
        };
    }
}
