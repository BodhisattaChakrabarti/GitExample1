package com.trainings.demoproject.DemoProject;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Queries
		String response=given().log().all().header("Content-Type", "application/json")
		.body("\"query ExampleQuery {\\n  character(characterId:8)\\n  {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId:8)\\n  {\\n    name\\n    dimension\\n  }\\n  episode(episodeId:1)\\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters:{name:\\\"Rahul\\\"})\\n  {\\n    info\\n    {\\n      count      \\n    }\\n    result\\n    {\\n      name\\n      type      \\n    }\\n  }\\n}\\n\"")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String characterName=js.getString("data.character.name");
		Assert.assertEquals(characterName, "Robin");
		
		//Mutations
		String mutationResponse=given().log().all().header("Content-Type", "application/json")
		.body("\"query ExampleQuery {\\n  character(characterId:8)\\n  {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId:8)\\n  {\\n    name\\n    dimension\\n  }\\n  episode(episodeId:1)\\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters:{name:\\\"Rahul\\\"})\\n  {\\n    info\\n    {\\n      count      \\n    }\\n    result\\n    {\\n      name\\n      type      \\n    }\\n  }\\n}\\n\"")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
				
		System.out.println(mutationResponse);
				
		JsonPath js1=new JsonPath(mutationResponse);
		String newCharacter=js1.getString("data.character.name");
		Assert.assertEquals(newCharacter, "Robin");
	}

}
