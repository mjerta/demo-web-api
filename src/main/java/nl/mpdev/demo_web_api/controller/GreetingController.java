package nl.mpdev.demo_web_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

  @GetMapping("/greeting")
  public String greeting() {
    return "Hello, World!";
  }

  @GetMapping("/greeting/{name}")
  public String greetingWithname(@PathVariable String name) {
    return "Hallo, leuk je te zien " + name;
  }
}
