package com.greetingapp.greeting.controller;

import com.greetingapp.greeting.dto.UserDTO;
import com.greetingapp.greeting.entities.GreetingEntity;
import com.greetingapp.greeting.services.GreetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/greeting/hello")
public class GreetingServiceController {

    //private attribute
    private final GreetingService greetingService;

    //constructor based Dependency injection
    public GreetingServiceController(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    @GetMapping
    public String getGreeting(){
        return greetingService.getGreetingMessage();
    }
    @GetMapping("/name")
    public String getCustomGreetingMessage(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName){
        return greetingService.customGreetingMessage(firstName,lastName);
    }

    @PostMapping("/post")
    public String getCustomGreetingMessageWithPost(@RequestBody UserDTO user){
        return greetingService.customGreetingMessage(user.getFirstName(), user.getLastName());
    }
    // Save Greeting Message
    @PostMapping("/save")
    public GreetingEntity saveGreeting(@RequestBody String message) {
        return greetingService.saveGreeting(message);
    }
    // Get Greeting by ID
    @GetMapping("/{id}")
    public Optional<GreetingEntity> getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }
    // Get All Greeting Messages
    @GetMapping("/all")
    public List<GreetingEntity> getAllGreetings() {
        return greetingService.getAllGreetings();
    }
    // Update Greeting Message by id
    @PutMapping("/update/{id}")
    public GreetingEntity updateGreeting(@PathVariable Long id, @RequestBody GreetingEntity greeting) {
        return greetingService.updateGreeting(id, greeting.getMessage());
    }
    // Delete Greeting Message
    @DeleteMapping("/delete/{id}")
    public String deleteGreeting(@PathVariable Long id) {
        boolean deleted = greetingService.deleteGreeting(id);
        return deleted ? "Greeting deleted successfully" : "Greeting not found!";
    }

}