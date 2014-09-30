package com.ECS.client.jax.client;

import com.ECS.client.jax.Item;
import com.ECS.client.jax.Items;

public class ItemSearchTest {

	public static void main(String[] args) {
		// Set the service:
		com.ECS.client.jax.AWSECommerceService service = new com.ECS.client.jax.AWSECommerceService();

		//Set the service port:
		com.ECS.client.jax.AWSECommerceServicePortType port = service.getAWSECommerceServicePort();

		//Get the operation object:
		com.ECS.client.jax.ItemSearchRequest itemRequest = new com.ECS.client.jax.ItemSearchRequest();

		//Fill in the request object:
		itemRequest.setSearchIndex("Books");
		itemRequest.setKeywords("dog");
		//itemRequest.setVersion("2013-08-01");
		com.ECS.client.jax.ItemSearch ItemElement= new com.ECS.client.jax.ItemSearch();
		ItemElement.setAWSAccessKeyId("AKIAIKZBIOOLEMGFZ44A");
		ItemElement.getRequest().add(itemRequest);

		//Call the Web service operation and store the response
		//in the response object:
		com.ECS.client.jax.ItemSearchResponse		response;// =		port.itemSearch("", "AKIAIKZBIOOLEMGFZ44A", "flycun-20", "false", "false", "false", null, null, null);
		// Get the Title names of all the books for all the items returned in the re		sponse
		}
}
