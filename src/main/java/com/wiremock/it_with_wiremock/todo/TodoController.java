package com.wiremock.it_with_wiremock.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.node.ArrayNode;

@RestController
@RequestMapping("/api/todos")
public class TodoController{

    private final WebClient webclient;

    public TodoController(WebClient todoWebClient){
        this.webclient = todoWebClient;
    }

    @GetMapping
    public ArrayNode getAllTodos(){
        return this.webclient
                .get()
                .uri("/todos")
                .retrieve()
                .bodyToMono(ArrayNode.class)
                .block();
    }

}