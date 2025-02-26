package com.trainings.demoproject.DemoProject.files;

import org.testng.Assert;

//import org.junit.Test;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourses()
	{
		int sum=0;
		JsonPath js=new JsonPath(Payload.coursePrice());
		int count=js.getInt("courses.size()");
		for(int i=0;i<count;i++)
		{
			int coursePrice=js.getInt("courses["+i+"].price");
			int courseCopies=js.getInt("courses["+i+"].copies");
			int amount=coursePrice*courseCopies;
			System.out.println(amount);
			sum=sum+amount;
		}
		
		System.out.println(sum);
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	}
}
