package com.futureacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReactAppController {
    @RequestMapping(value = {
            "/",
            "/login",
            "/dashboard",
            "/mis-tareas",
            "tareas",
            "/#contacto"
    }, method = RequestMethod.GET)
    public String getReactApp() {
        return "forward:/index.html";
    }
}