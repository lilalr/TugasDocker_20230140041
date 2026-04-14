package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final String USERNAME = "admin";
    private final String PASSWORD = "20230140041";

    // Penyimpanan data temporary (bukan database)
    private List<User> listMahasiswa = new ArrayList<>();

    @GetMapping("/")
    public String loginpage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            return "redirect:/home";  // pakai redirect biar URL-nya /home
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/home")
    public String homepage(Model model) {
        model.addAttribute("listMahasiswa", listMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String formpage() {
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(@RequestParam String nama,
                             @RequestParam String nim,
                             @RequestParam String jenisKelamin) {
        User user = new User();
        user.setNama(nama);
        user.setNim(nim);
        user.setJenisKelamin(jenisKelamin);
        listMahasiswa.add(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}