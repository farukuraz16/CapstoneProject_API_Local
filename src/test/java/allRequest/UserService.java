package allRequest;

import baseURL.QuasPareURL;


import datasPojo.UserServicePojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pages.Login_Token;
import utilities.ConfigReader;
import utilities.JsonToJava;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class UserService extends QuasPareURL {
    HashMap<String, Object> responseBody;
    String newUserID;

    @Test
    public void GET_TC001_GetAllUsers() {
        Login_Token loginToken = new Login_Token();
        loginToken.getToken();
        String token = Login_Token.access_token;


        /*
        // header("Authorization","Bearer "+LoginAndToken.token).
        auth().preemptive().oauth2(LoginAndToken.token).
         */
        specification.pathParam("userPath", "user");
        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + token).
                get("/{userPath}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
    }

    @Test
    public void GET_TC002_GetAllUsersofOrganization() {
        Login_Token loginToken = new Login_Token();
        loginToken.getToken();
        String token = Login_Token.access_token;
        //    "https://a3m-qa-gm3.quaspareparts.com/auth/api/user?organizationId=1"
        specification.pathParam("userPath", "user").
                queryParam("organizationId", 26);

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + token).
                get("/{userPath}");

        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
    }

    @Test
    public void POST_TC003_AddNewUser() {
        Login_Token loginToken = new Login_Token();
        loginToken.getToken();
        String token = Login_Token.access_token;


        String URL = ConfigReader.getProperty("baseURL") + "/organization/26/application/2/role/5/user";

       /*
       specification.pathParams(
                "pp1", "organization",
                "pp2", "26",
                "pp3", "application",
                "pp4", "2",
                "pp5", "role",
                "pp6", "5",
                "pp7", "user");
*/


        UserServicePojo requestBody = new UserServicePojo(
                ConfigReader.getProperty("USid"),
                ConfigReader.getProperty("USname"),
                ConfigReader.getProperty("USlastname"),
                ConfigReader.getProperty("USusername"),
                ConfigReader.getProperty("USemail"),
                ConfigReader.getProperty("USphone"),
                ConfigReader.getProperty("USaddress"),
                ConfigReader.getProperty("UScountry"));

        System.out.println("userData = " + requestBody);

        Response response = given().
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + token).
                body(requestBody).
                when().
                post(URL);
        //post("/{pp1}/{pp2}/{pp3}/{pp4}/{pp5}/{pp6}/{pp7}");

        response.prettyPrint();

        responseBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        System.out.println("actualDataMap = " + responseBody);

        response.then().assertThat().statusCode(201);

        assertEquals(requestBody.getName(), responseBody.get("username"));
        assertEquals(requestBody.getName(), responseBody.get("username"));
        assertEquals(requestBody.getEmail(), responseBody.get("email"));

        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        newUserID = (String) responseBody.get("id");
    }

    @Test
    public void GET_TC004_GetUserbyId() {
        Login_Token loginToken = new Login_Token();
        loginToken.getToken();
        String token = Login_Token.access_token;

//        String URL = "https://a3m-qa-gm3.quaspareparts.com/auth/api/user/336";
        specification.pathParams("userPath", "user", "idPath", newUserID);
        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + token).
                get("/{userPath}/{idPath}");

        response.prettyPrint();
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        responseBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        response.then().assertThat().statusCode(200);
        assertEquals(newUserID, responseBody.get("id"));

    }

    @Test
    public void POST_TC005_SendEmailVerification() {

        Login_Token loginToken = new Login_Token();
        loginToken.getToken();
        String token = Login_Token.access_token;

        //String URL = "https://a3m-qa-gm3.quaspareparts.com/auth/api/user/send-verification-request?organizationId=26&appId=2";
        specification.pathParams("userPath", "user", "verifyPath", "send-verification-request").
                queryParams("organizationId", "26",
                        "appId", "2");

        UserServicePojo requestBody = new UserServicePojo(newUserID, ConfigReader.getProperty("email"));
        Response response = given().
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + token).
                body(requestBody).
                when().
                post("/{userPath}/{verifyPath}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        responseBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        assertEquals("\"Email Verification request sent successfully\"", responseBody.get("message"));


        System.out.println("response.getStatusCode() = " + response.getStatusCode());
    }

    @Test
    public void PUT_TC007_UpdateExistingUser() {
        Login_Token loginToken = new Login_Token();
        loginToken.getToken();
        String token = Login_Token.access_token;

        //String URL = "https://a3m-qa-gm3.quaspareparts.com/auth/api/user";
        UserServicePojo requestBody = new UserServicePojo(
                newUserID,
                "IronMan",
                "Çelikoğlu",
                "ironman@example.com",
                "ironman@example.com",
                "123456789",
                "Atlanta,GA",
                "US");

        specification.pathParam("userPath", "user");

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + token).
                body(requestBody).
                when().
                get("/{userPath}");


        response.prettyPrint();
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        responseBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        response.then().assertThat().statusCode(200);
        assertEquals(requestBody.getLastname(), responseBody.get("lastname"));
        assertEquals(requestBody.getName(), responseBody.get("name"));
    }


    @Test
    public void DELETE_TC008_UpdateExistingUser() {
        Login_Token loginToken = new Login_Token();
        loginToken.getToken();
        String token = Login_Token.access_token;

//        String URL = "https://a3m-qa-gm3.quaspareparts.com/auth/api/user/336";
        specification.pathParams("userPath", "user", "idPath", newUserID);
        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + token).
                delete("/{userPath}/{idPath}");

        response.prettyPrint();
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

//        responseBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        response.then().assertThat().statusCode(200);
        assertEquals(null, response.asString());
    }
}
