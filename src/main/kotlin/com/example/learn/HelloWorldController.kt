package com.example.learn

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello") //end point
class HelloWorldController {

    @GetMapping//("springboot") //this is a get end point
    fun helloWorld():String{
        return "Hello this is a rest end point"
    }
}