package com.apps.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class Demo {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Demo.class, args);

		ItemService itemService = context.getBean(ItemService.class);

		List<Item> items = itemService.getItems();
		items.forEach(System.out::println);
	}

}
