package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.model.Greeting;
import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(10L, "Hello, "+name);
    }

    @RequestMapping("/test")
    public Greeting test(@RequestParam(value="name", defaultValue="World") String name, @RequestParam(value="age") Long age) {
        return new Greeting(age, "Hello, "+name);
    }


}