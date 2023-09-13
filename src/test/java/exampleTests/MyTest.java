package exampleTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.CityRequestBody;
import pojo.UserDetails;
import pojo.ResponseBody_CreateUser;
import utils.ExtentReportManager;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class MyTest {

    private ExtentReports extentReport;
    private ExtentTest extentTest;

    @BeforeMethod
    public void setUp()
    {
        extentReport = ExtentReportManager.initializeExtentReport();
        extentTest = extentReport.createTest("My Test case", "DEscription of the Test case");
    }

    @Test
    public void myTestCase()
    {
        ResponseBody_CreateUser respo = new ResponseBody_CreateUser();
        CityRequestBody cityRequestBody = new CityRequestBody();
        cityRequestBody.setName("Annavarram");
        cityRequestBody.setTemperature("45");

        List<CityRequestBody> li = new ArrayList<>();
        li.add(cityRequestBody);

        UserDetails requestBody = new UserDetails();
        requestBody.setName("Peter Parker");
        requestBody.setJob("Spiderman");
        requestBody.setCity(li);


        Response response = given()
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/users");
        System.out.println(response.body().asPrettyString());

        UserDetails responseBody_test = response.as(UserDetails.class);
        System.out.println("Response Job : " + responseBody_test.getId());

        extentTest.log(Status.INFO,"Get users request is executed");

    }

    private RequestSpecification getRequestSpecificationBuilder()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://reqres.in/api/users");
        requestSpecBuilder.addQueryParam("page=2");
        requestSpecBuilder.setContentType("application/json");

        RequestSpecification requestSpec = requestSpecBuilder.build();
        return requestSpec;
    }

    private ResponseSpecification getResponseSpecification()
    {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectContentType("application/json");

        ResponseSpecification responseSpecification = responseSpecBuilder.build();
        return responseSpecification;
    }

    @Test
    public void getUsersData()
    {
        given()
                .spec(getRequestSpecificationBuilder())
                .when()
                .get()
                .then()
                .spec(getResponseSpecification());

    }

    @AfterMethod
    public void tearDown(){
        extentReport.flush();
    }
}
