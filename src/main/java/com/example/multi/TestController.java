package com.example.multi;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class TestController {
    private final TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public CompletableFuture<List<String>> testing() {
        CompletableFuture<List<String>> res = service.serialSort();
        return res;
    }

    @DeleteMapping("/delete")
    public String delete() {
        service.deleteData();
        return "all data is deleted~!! ^^";
    }

    @PostMapping("/random")
    public String randomData() {
        service.randomData();
        return "300 data is posted in DB~~";
    }
}
