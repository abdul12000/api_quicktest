package rabdd;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class RestBDD {
    @Test
    public void runRestBDD() {

        //For post request
        HashMap<String, String> postContent = new HashMap<>();
        postContent.put("userId", "10299");
        postContent.put("title", "this is my title");
        postContent.put("body", "This is finally my body");

        given().contentType(ContentType.JSON).log().all().
                with().body(postContent).
                when().post("https://jsonplaceholder.typicode.com/posts/")
                .then().body("title", is ("this is my title")).log().all();


        //For get Request
        given().contentType(ContentType.JSON).log().all().
                when().get("https://jsonplaceholder.typicode.com/comments/2")
                .then().log().all().statusCode(200)
                .body("name", is("quo vero reiciendis velit similique earum"))
                .body("email", is("Jayne_Kuhic@sydney.com")).log().all();

    }
}
