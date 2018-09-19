/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import progmatic.bookingmanager.databaseEntity.Role;
import progmatic.bookingmanager.databaseEntity.User;
import progmatic.bookingmanager.repositories.RoleRepository;
import progmatic.bookingmanager.repositories.UserRepository;
import progmatic.bookingmanager.services.ManagerService;

/**
 *
 * @author Stankye
 */
@Controller
public class ManagerController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    ManagerService managerService;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder encoder;

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String addNewReceptionistGet(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("roleList", roleRepo.findAll());
        return "add_user";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String addNewReceptionistPost(Model model, @Valid @ModelAttribute("newUser") User user, BindingResult bindingResult,
            @RequestParam(value = "inputPasswordAgain", required = true) String inputPasswordAgain) {

        if (bindingResult.hasErrors() | !(addNewReceptionistPostHasCustomErrors(user, bindingResult, inputPasswordAgain, model))) {
            model.addAttribute("newUser", user);
            model.addAttribute("roleList", roleRepo.findAll());
            return "add_user";
        }

        user.setActive((short) 1);
        user.setPassword(encoder.encode(user.getPassword()));
        managerService.addNewUser(user);
        return "redirect:/users";

    }

    private boolean addNewReceptionistPostHasCustomErrors(User user, BindingResult bindingResult, String inputPasswordAgain, Model model) {
        boolean ok = true;
        if (!managerService.usernameIsAvalible(user.getUsername())) {
            FieldError fe = new FieldError("newUser", "username", "Username already in use.");
            bindingResult.addError(fe);
            ok = false;
        }
        if (!(managerService.checkTheTwoPasswordAreTheSame(user.getPassword(), inputPasswordAgain))) {
            FieldError fe = new FieldError("newUser", "password", "Passwords are not the same.");
            bindingResult.addError(fe);
            ok = false;
        }
        return ok;
    }

    @RequestMapping(value = "/users/modify/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String modifyUserGet(Model model, @PathVariable("id") Long id) {
        model.addAttribute("newUser", userRepo.findOne(id));
        model.addAttribute("roleList", roleRepo.findAll());
        model.addAttribute("url", "/users/modify/" + id);
        return "modify_user";
    }

    @RequestMapping(value = "/users/modify/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String modifyUserPost(Model model, User user, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", userRepo.findOne(id));
            model.addAttribute("roleList", roleRepo.findAll());
            model.addAttribute("url", "/users/modify/" + id);
            return "modify_user";
        }

        managerService.modifyUser(user,id);
        return "redirect:/users";

    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String showUserList(Model model) {
        model.addAttribute("userList", userRepo.findAll());
        return "users";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public @ResponseBody
    Long setUserActiveOdInactive(@PathVariable("id") Long id) {
        managerService.setUserActiveOrInactive(id);
        return id;
    }

}
