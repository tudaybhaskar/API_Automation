package exampleTests;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.JsonReader;
import utils.PropertyReader;
import utils.SoftAssertionUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FirstRestTest {


    @Test
    public void getUserData() {
        given()
                .when().get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void getUserData_Validate() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        given()
                .when()
                .get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(emptyString()))
                .body("title", equalTo("delectus aut autem"))
                .body("userId", equalTo(1));
    }

    @Test
    public void validateResponse_HasItems() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        Response response = given()
                .when()
                .get("/posts")
                .then()
                .extract()
                .response();
        // Use Hamcrest Matchers to verify the response body contains sepcific items
        assertThat(response.jsonPath().getList("title"), hasItems("eum et est occaecati", "qui est esse"));

        SoftAssertionUtil.assertEquals(response.getStatusCode(), StatusCode.NO_CONTENT.code,"Unable to get results");
        SoftAssertionUtil.assertAll();
    }

    @Test
    public void validateResponse_HasSize() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        Response response = given()
                .when()
                .get("/comments")
                .then()
                .extract()
                .response();
        // Use Hamcrest Matchers to verify the response body contains sepcific items
        assertThat(response.jsonPath().getList(""), hasSize(500));
    }

    @Test
    public void validateResponse_Contains() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        Response response = given()
                .when()
                .get("/comments?postId=1")
                .then()
                .extract()
                .response();
        // Use Hamcrest Matchers to verify the response body contains sepcific items
        List<String> expEmails = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz",
                "Lew@alysha.tv", "Hayden@althea.biz");

        assertThat(response.jsonPath().getList("email"), contains(expEmails.toArray(new String[0])));
    }

    @Test
    public void validateResponse_Values_Is(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        Response response = given()
                .queryParam("page",2)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract()
                .response();

        response.then().body("data", hasSize(6));
        //
        response.then().body("data[0].id", is(7));
        response.then().body("data[0].first_name", is("Michael"));
    }

    @Test
    public void validateResponse_Header(){

        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(StatusCode.SUCCESS.code)
                .extract().response();

        Headers resp_Headers = response.getHeaders();
        for(Header h : resp_Headers ){
            System.out.println(h.getName() + " : " + h.getValue() ) ;
        }
    }

    @Test
    public void delete_Request(){
        given()
                .when()
                .delete("https://reqres.in/api/users?page=2")
                .then().statusCode(StatusCode.NO_CONTENT.code);

    }

    @Test
    public void readJsonTestData() throws Exception {
        System.out.println("UserName from Json : " + JsonReader.getTestData("username"));
    }

    @Test
    public void readPropertyDataFromPropertiesFile(){
        System.out.println("property value of server:" + PropertyReader.getPropertyValue("server"));
    }

    @Test(dataProvider = "TestingData")
    @Parameters({"id","name"})
    public void validateDateProvider(String id, String name){

    }

    @DataProvider(name = "TestingData")
    public Object[][] testDataProvider(){
        return new Object[][] {
                {"1,", "John"},
                {"2", "Jane"},
                {"3", "Bob"}
        };
    }

    @Test
    public void automateRequestUsingJsonFile() throws Exception {
        Response response = given()
                .body(IOUtils.toString(
                        new FileInputStream(new File(System.getProperty("user.dir")+
                                "/resources/TestData/postUser.json")
                        ),"UTF-8")
                )
                        .when()
                                .post("https://reqres.in/api/users/2");

        System.out.println(response.body().asString());
    }

    @Test
    public void validateJsonSchema(){

        RestAssured.baseURI = "https://reqres.in/api/users?page=2";

        given()
                .when()
                .get()
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("./resources/ExpectedSchema.json")));
    }
}
