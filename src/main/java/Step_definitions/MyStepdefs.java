package Step_definitions;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.RequestBodyServices;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

public class MyStepdefs extends BaseSteps {
    Response responseForGetServiceUrl, responseForGetPostRequest, responseForPostCall, responseForGetCommentRequest, responseForPostCommentCall;
    RequestBodyServices requestBodyServices;
    DocumentContext requestBody;
    @Given("service is up and running")
    public void serviceIsUpAndRunning() {
        setHeadersWithContentType();
        setEndpointPath(serviceUrl);
        getCall();
        responseForGetServiceUrl = getResponse();
        assertThat(responseForGetServiceUrl.statusCode(), is(200));
    }

    @When("i search for {string} of a post with a GET method")
    public void iSearchForOfAPostWithAGETMethod(String id) {

                    setHeadersWithContentType();
            setEndpointPath(makeAPostEndpoint + id);
            getCall();
            responseForGetPostRequest = getResponse();
    }

    @Then("i should get the correct {string}, {string} and {string} returned with status code of {int}")
    public void iShouldGetTheCorrectAndReturnedWithStatusCodeOf(String id, String title, String body, int sCode) {
        assertThat(responseForGetPostRequest.statusCode(), is(sCode));
         assertThat(responseForGetPostRequest.body().jsonPath().get("id").toString().contains(id), is (true));
                   assertThat(responseForGetPostRequest.body().jsonPath().get("id").toString(), is (id));
//                    assertThat(responseForGetPostRequest.body().jsonPath().get("id"), is(id));
                   assertThat(responseForGetPostRequest.body().jsonPath().get("title"), is(title));
                  assertThat(responseForGetPostRequest.body().jsonPath().get("body"), is(body));
                   assertThat(responseForGetPostRequest.body().jsonPath().get("userId").toString(), is(notNullValue()));
//                    Assert.(AssertresponseForGetPostRequest.body().jsonPath().get("userId"));
    }

    @When("I create a new post with the following details {string},{string} and {string},")
    public void iCreateANewPostWithTheFollowingDetailsAnd(String uId, String title, String body) {
        requestBodyServices = new RequestBodyServices();
        setHeadersWithContentType();
        requestBody = loadJsonTemplate(MakeAPostPayload);
        requestBodyServices.setRequestBodyForNewPost(requestBody, uId, title, body);
        setEndpointPath(makeAPostEndpoint);
        getPostCall();
        responseForPostCall = getResponse();
    }


    @Then("i should get the correct {string},{string} and {string} returned and with status code of {int}")
    public void iShouldGetTheCorrectAndReturnedAndWithStatusCodeOf(String uId, String title, String body, int sCode) {
        assertThat(responseForPostCall.statusCode(), is(sCode));
       assertThat(responseForPostCall.body().jsonPath().get("userId"), is(uId));
        assertThat(responseForPostCall.body().jsonPath().get("title"), is(title));
       assertThat(responseForPostCall.body().jsonPath().get("body"), is(body));
        assertNotNull(responseForPostCall.body().jsonPath().get("id"));
    }

    @When("i search for comment with {string} with a GET method")
    public void iSearchForCommentWithWithAGETMethod(String arg0) {
    }

    @Then("i the correct {string}, {string}, {string} and {string} are returned with status code of {int}")
    public void iTheCorrectAndAreReturnedWithStatusCodeOf(String arg0, String arg1, String arg2, String arg3, int arg4) {
    }
}
