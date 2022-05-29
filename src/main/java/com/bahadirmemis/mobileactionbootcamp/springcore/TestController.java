package com.bahadirmemis.mobileactionbootcamp.springcore;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
//@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * http://localhost:8080/test
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public TestReponse test(@PathVariable(required = false) Long id){
        System.out.println("Test");

        TestReponse testReponse = TestReponse.builder()
                .id(id)
                .data("Get data from db")
                .isSuccess(true)
                .build();

        return testReponse;
    }

    /**
     * http://localhost:8080/test?id=4
     * @param id
     * @return
     */
    @GetMapping("/test2")
    public TestReponse test2(@RequestParam(defaultValue = "aa") Long id){

        System.out.println("Test");

        TestReponse testReponse = TestReponse.builder()
                .id(id)
                .data("Get data from db")
                .isSuccess(true)
                .build();

        return testReponse;
    }

    @PostMapping
    public String save(){
        System.out.println("saved");
        return "set";

    }
}
