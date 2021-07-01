package project.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.user.dto.UserDTO;
import project.user.model.ERole;
import project.user.model.User;
import project.user.service.UserService;

import static project.UrlMapping.USER;

import java.util.List;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO user) {
        return userService.create(user);
    }

    @PatchMapping
    public UserDTO edit(@RequestBody UserDTO user) {
        return userService.edit(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}