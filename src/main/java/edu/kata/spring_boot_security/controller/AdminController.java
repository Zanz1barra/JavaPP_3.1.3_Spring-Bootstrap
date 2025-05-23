package edu.kata.spring_boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kata.spring_boot_security.entity.User;
import edu.kata.spring_boot_security.service.RoleService;
import edu.kata.spring_boot_security.service.UserService;

@Controller
@RequestMapping(path = {"/admin"})
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(
            UserService UserService,
            RoleService roleService) {
        this.userService = UserService;
        this.roleService = roleService;
    }

    @GetMapping(path = {"", "/"})
    public String getUsersListWithFormForAddUser(
            String tab,
            ModelMap modelMap) {
        modelMap.addAttribute("userList", userService.getUserList());
        modelMap.addAttribute("isAdmin", true);
        modelMap.addAttribute("addedUser", new User());
        modelMap.addAttribute("allRoles", roleService.getRolesList());
        modelMap.addAttribute("currentTab",
                (tab == null) ? "user_list" : (tab.equals("add")) ? "add_user_form" : "user_list");
        return "admin/admin_main";
    }

    @GetMapping(path = {"/update"})
    public String getModalWindowWithFormForUpdateUser(
            @RequestParam(name = "id") Long userId,
            ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(userId));
        modelMap.addAttribute("allRoles", roleService.getRolesList());
        return "admin/admin_edit_form :: editForm";
    }

    @GetMapping(path = {"/delete"})
    public String getModalWindowWithFormForDeleteUser(
            @RequestParam(name = "id") Long userId,
            ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(userId));
        modelMap.addAttribute("allRoles", roleService.getRolesList());
        return "admin/admin_delete_form :: deleteForm";
    }

    @PostMapping(path = {"/add/"})
    public String addUser(
            User user,
            ModelMap modelMap) {
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @PostMapping(path = {"/delete/"})
    public String deleteUser(
            User user,
            ModelMap modelMap) {
        System.out.println(user);
        userService.deleteUser(user);
        return "redirect:/admin/";
    }

    @PostMapping(path = {"/update/"})
    public String updateUser(
            User user,
            ModelMap modelMap) {
        userService.updateUser(user);
        return "redirect:/admin/";
    }
}
