package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeCred;
import com.employee.EmployeeDatabaseManagement.EDM.service.EmployeeCredService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user-api")
public class UserController {

//    @Autowired
//    private EmployeeCredService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new EmployeeCred());
        return "login";
    }

    @GetMapping("/panel")
    public String panel(Model model) {
        return "hr_panel";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home_page";
    }
    @GetMapping("/hr_dashboard")
    public ModelAndView showAdminForm() {
        return new ModelAndView("dashboard");
    }


    @GetMapping("/register-form")
    public String showRegistrationPage(@ModelAttribute("user") EmployeeCred employeeCred) {
        return "user_registration";
    }
}

//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Autowired
//    private JWTToken jwtTokenUtil;
//    @Autowired
//    private AuthenticationManager authenticationManager;


//    @PostMapping("/user")
//    public ResponseEntity<String> addUser(@RequestBody User user) {
//        userService.addUser(user);
//        return ResponseEntity.ok("User Added...");
//    }
//
//    @GetMapping("/user")
//    public ResponseEntity<User> getUser(@RequestParam("user_id") String userId) {
//        return ResponseEntity.ok(userService.getUser(userId));
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUser() {
//        return ResponseEntity.ok(userService.getAll());
//    }
//
//    @GetMapping("/user-id")
//    public ResponseEntity<User> getUserByNameAndPass(@RequestParam("uname") String uname, @RequestParam("pass") String pass) {
//        return ResponseEntity.ok(userService.getUserByNameAndPass(uname, pass));
//    }

//    @GetMapping("/users/{username}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String uname) {
//        return ResponseEntity.ok(userService.getUserByUsername(uname));
//    }

//    @PostMapping("/register")
//    public ModelAndView register(@ModelAttribute("user") User user,Model model) {
//        String username = user.getUsername();
//        String password = passwordEncoder.encode(user.getPassword());
//        UserRole role = user.getRole();
//        User jwtUser = new User(username, password, role);
//        User existingUser = userService.getUserByNameAndPass(user.getUsername(),user.getPassword());
//        if (existingUser != null) {
//            // Handle the case where the username already exists
////            throw new IllegalStateException("Username already exists");
//            model.addAttribute("error", "Username already exists");
//            return new ModelAndView("user_registration");
//        }

//        // Register the user
//        //save in db
//        userService.addUser(jwtUser);
//        return  new ModelAndView("redirect:/user-api/login");
//    }


  /*  @PostMapping("/authenticate")
    public ModelAndView authenticate(@ModelAttribute("user") User user, HttpServletRequest request) {
        // specify the type of authentication provider
//            String pass = passwordEncoder.encode(user.getPassword());
        authenticate(user.getUsername(), user.getPassword());
        // check if username is in db
        UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());

        // generate the token with the userdata
        String token = jwtTokenUtil.generateToken(details);
        System.out.println("token :: "+ token);
        if (jwtTokenUtil.validateToken(token, details)) {
            String encode = "Bearer "+ token;
//            HttpSession session = request.getSession();
//            request.setAttribute("Authorization", encode);
//            session.setAttribute("Authorization", encode);
            return ResponseEntity.ok().headers(httpHeaders -> httpHeaders.set("Authorization", encode)).body(new ModelAndView("redirect:/user-api/dashboard")).getBody();
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginForm", user);
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }

    // to specify the type of authentication
    private void authenticate(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid Credentials");
        } catch (DisabledException e) {
            System.out.println("Disabled");
        }
    }*/



    /*
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new User());
        return "login";
}
//    @GetMapping("/panel")
//    public String panel(Model model) {
//        model.addAttribute("panelForm", new User());
//        return "panel";
//    }
@GetMapping("/hr_dashboard")
public String hr_dashboard(Model model) {
    model.addAttribute("hrDashboardForm", new User());
    return "hr_dashboard";
}

    @GetMapping("/register")
    public String showRegistrationPage(@ModelAttribute("user") User user) {
        return "user_registration";
    }


    @PostMapping("/process_register")
    public String registerUser(@ModelAttribute("user") User user,Model model) {
        // Check if the username already exists
        User existingUser = userService.findByUnameAndPass(user.getUsername(),user.getPassword());
        if (existingUser != null) {
            // Handle the case where the username already exists
//            throw new IllegalStateException("Username already exists");
            model.addAttribute("error", "Username already exists");
            return "user_registration";
        }

        // Register the user
        userService.registerUser(user);
        return "redirect:/api/users/login";
    }
    @PostMapping("/processLogin")
    public ModelAndView processLoginForm(@RequestBody() User user, HttpServletRequest request) throws UnsupportedEncodingException {

        // specify the type of authentication provider
//        authenticate(user.getUsername(), user.getPassword());
        // check if username is in db
        UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());

        // generate the token with the userdata
        String token = jwtToken.generateToken(details);

        if (jwtToken.validateToken(token, details)) {
            String encode = "Bearer "+ token;
            HttpSession session = request.getSession();
            session.setAttribute("Authorization", encode);
            return new ModelAndView("redirect:/api/users/dashboard");
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginForm", user);
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }
    private void authenticate(String username,String password){
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            System.out.println("Invalid Credentials");
        }catch (DisabledException e ){
            System.out.println("Disabled");
        }
    }


    */



