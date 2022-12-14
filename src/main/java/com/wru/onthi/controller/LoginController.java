package com.wru.onthi.controller;

import com.google.common.base.Strings;
import com.wru.onthi.entity.Role;
import com.wru.onthi.entity.User;
import com.wru.onthi.repository.RoleRepository;
import com.wru.onthi.repository.UserRepository;
import com.wru.onthi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Controller
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/signin")
    public String signIn(Model model, HttpServletRequest httpServletRequest) {
        String fullname= httpServletRequest.getParameter("fullname");
        String email= httpServletRequest.getParameter("email");
        String username= httpServletRequest.getParameter("username");
        String password= httpServletRequest.getParameter("password");
        String phone= httpServletRequest.getParameter("phone");

        if (!Strings.isNullOrEmpty(fullname) && !Strings.isNullOrEmpty(email)
                && !Strings.isNullOrEmpty(password) && !Strings.isNullOrEmpty(phone)) {

            User checkEmailExist= userRepository.findByEmail(email);
            User checkUsernameExist= userRepository.findByUsername(username);

            if (checkEmailExist == null && checkUsernameExist == null) {
                User user = new User();
                Role role = roleRepository.findByRole("USER");
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(passwordEncoder.encode(password));
                user.setFullname(fullname);
                user.setPhone(phone);
                user.setImage("default_avatar.png");
                Date date= new Date();
              //  System.out.println("time"+ date+ "datetostring"+ date.toString());
                user.setCreateDate(date);
                user.setStatus(1);
                user.setRoles(Arrays.asList(role));
                userService.createUser(user);
                model.addAttribute("success", "????ng k?? th??nh c??ng, vui l??ng ????ng nh???p l???i!");
                model.addAttribute("email", email);
            } else {
                if(checkEmailExist != null){
                    model.addAttribute("error", "Email ???? t???n t???i, vui l??ng ????ng k?? email kh??c!");
                }
                else if(checkUsernameExist != null){
                    model.addAttribute("error", "Username ???? t???n t???i, vui l??ng ????ng k?? username kh??c!");
                }
            }
        }
        return "login";
    }
}
