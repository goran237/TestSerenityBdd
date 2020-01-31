package features;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import request.StaffBuilder;

import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

@RunWith(SerenityRunner.class)
public class JsonPlaceholderResults {
    String baseUrl = "https://jsonplaceholder.typicode.com/";
    String POSTS_ENDPOINT = "posts";
    private Actor user = new Actor("Current user");

    @Before
    public void assignResponsibilityToUser() {
        user.can(CallAnApi.at(baseUrl));
    }

    @Test
    public void userShouldVerifyTheSizeOfReturnedResponse() {
        when(user)
                .attemptsTo(Post.to(POSTS_ENDPOINT)
                        .with(request -> request
                                .contentType(ContentType.JSON)
                                .body(new Gson().toJson(new StaffBuilder()
                                        .withName("John")
                                        .withAge(30)
                                        .withSkills("c++", "python", "java")
                                        .withSalary(1000).build()))
                                .log()
                                .all()));
        then(user)
                .should(
                        seeThatResponse(response -> response
                                .log()
                                .all()
                                .statusCode(201)
                                .body("id", is(101))
                                .body("name", is("John"))
                                .body("age", is(30))
                                .body("salary", is(1000))
                                .body("skills", hasSize(3))));
    }
}
