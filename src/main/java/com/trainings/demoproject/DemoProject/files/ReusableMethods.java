package com.trainings.demoproject.DemoProject.files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

	public static JsonPath rawToJson(String response)
	{
		JsonPath js1=new JsonPath(response);
		return js1;
	}
}
