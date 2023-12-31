package bg.softuni.pathfinder.controller;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.service.RestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class RestDemoController {

    private RestDemoService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }


    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        return this.userService.getAll();
    }


    @Autowired
    public void setRestDemoService(RestDemoService userService) {
        this.userService = userService;
    }


}
