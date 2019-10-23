package hello;

import ifoodcompetition.Restaurant;
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

    @RequestMapping(value = "/restaurant", method = RequestMethod.PUT)
    public Restaurant restaurant(@RequestBody Restaurant restaurant) {
        restaurant.setId(9L);
        return restaurant;
    }

 //   @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
   // public Restaurant getRestaurant(@RequestParam(value="id") Long id) {
     //   return new Restaurant("Primeira Mesa",
       //         "Free buffet", "Calle 2", id);
   // }
}