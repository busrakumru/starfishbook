package de.beuth.starfishbook.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.beuth.starfishbook.model.User;
import de.beuth.starfishbook.service.UserService;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("/auth/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> findAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PatchMapping(value = "/users/{id}")
    public User updateTodoWithMap(@PathVariable(value = "id") Long id, @RequestBody Map<Object, Object> objectMap) {
        return userService.updateWithMap(id, objectMap);

    }
}
