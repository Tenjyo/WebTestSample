package com.example;

public class Helloword1 {

	int sayHello(){

		System.out.println("HelloWorld1");
		return 1;
	}
	String sayHello2(String a1){
		String str = "Hello " + a1;
		System.out.println(str);
		return str;
	}

	String sayHello2(String a1){
		String str = "HelloWorld1" + a1;
		System.out.println(str);
		return str;
	}

}
