package com.trainings.demoproject.DemoProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.trainings.demoproject.DemoProject.files.Payload;
import com.trainings.demoproject.DemoProject.files.ReusableMethods;

public class Basics {
	
	public static void main(String[] args)
	{
		//validate if AddPlace API is working as expected
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		System.out.println();
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		
		//validate if UpdatePlace API is working as expected
		String newAddress="70 Winter Walk, Germany";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("maps/api/place/update/json").then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//validate if GetPlace API is working as expected
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		//JsonPath js1=new JsonPath(getPlaceResponse);
		JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	}

}
