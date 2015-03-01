package org.softserveinc.controller;

import com.google.common.collect.Sets;
import org.softserveinc.domain.User;
import org.softserveinc.domain.UserRole;
import org.softserveinc.dtos.enums.Role;
import org.softserveinc.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {

    @Inject
    private UserService userService;

    @Inject
    private AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required=false) String message) {
        model.addAttribute("message", message);
        return "access/login";
    }

    @RequestMapping(value = "/denied")
    public String denied() {
        return "access/denied";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure() {
        String message = "Login Failure!";
        return "redirect:/login?message="+message;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addUser(User user, HttpServletRequest request, HttpServletResponse responsee) {
        user.setRoles(Sets.newHashSet(
                new UserRole(Role.ROLE_USER.getId(), Role.ROLE_USER.name()))
        );
        userService.saveUserIntoDB(user);
        authenticateUserAndSetSession(user, request);
        return "redirect:/userProfile";
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "access/signup";

    }
}
