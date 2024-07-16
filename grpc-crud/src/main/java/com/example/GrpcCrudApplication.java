package com.example;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.UserServiceGrpc.UserServiceImplBase;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
public class GrpcCrudApplication {
	@Bean
	public Server grpServer(UserServiceImplBase userServiceImplBase){
		return ServerBuilder.forPort(9090).addService(userServiceImplBase).build();    
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Hello this grpc");
		ConfigurableApplicationContext context = SpringApplication.run(GrpcCrudApplication.class, args);
		Server server = context.getBean(Server.class);
		server.start();
		server.awaitTermination();
	}

}

