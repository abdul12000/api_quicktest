package Step_definitions;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseSteps {

    public String serviceUrl;
    public String MakeAPostPayload, MakeACommentPayload;
    public String makeAPostEndpoint, makeCommentEndpoint;
    public Headers headers;
    private String endpointPath;
    private Response response;
    public DocumentContext requestBodyJson;

    public BaseSteps(){
        MakeACommentPayload = "/templates/CommentOnAPost.json";
        MakeAPostPayload = "/templates/MakeApost.json";
        serviceUrl = "https://jsonplaceholder.typicode.com/";
        makeAPostEndpoint = serviceUrl + "posts/";
        makeCommentEndpoint = serviceUrl + "comments/";
    }

    public void setHeaders(Headers value){
        restHeaders();
        headers = value;
    }

    private void restHeaders() {
        headers = null;
    }

    public void setHeadersWithContentType(){
        headers = new Headers(
                new Header("Content-Type", "application/json"));
        setHeaders(headers);
    }

    //    public void setHeadersWithManyHeaders(){
//        headers = new Headers(
//                new Header("Content-Type", "application/json"),
//                new Header("Authorisation", "89289789jkjkhjkhu9njnknkjh98"),
//                new Header("Token", "token Value"));
//        setHeaders(headers);
//    }
    protected URL getURL(){
        try {
            return new URL(endpointPath);
        } catch(MalformedURLException e){
            throw new RuntimeException();
        }
    }
    public Response getCall(){
        response = RestAssured.given()
//                .log().all()
                .relaxedHTTPSValidation()
                .headers(headers)
                .when()
                .get(getURL())
                .then()
                .log().all()
                .extract()
                .response();
        return response;
    }
    public Response getPostCall(){
        response = RestAssured.given()
//                .log().all()
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(requestBodyJson.jsonString())
                .when()
                .post(getURL())
                .then()
                .log().all()
                .extract()
                .response();
        return response;
    }

    public void setEndpointPath(String endpointPath){
        this.endpointPath = endpointPath;
    }
    public Response getResponse(){
        return response;
    }
    //public void setRequestBody(DocumentContext requestBody){
//        this.requestBodyJson= requestBody;
//}
    public DocumentContext loadJsonTemplate(String path){
        requestBodyJson = JsonPath.parse(this.getClass().getResourceAsStream(path));
        return requestBodyJson;
    }
}
