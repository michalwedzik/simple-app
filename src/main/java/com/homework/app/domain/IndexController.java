package com.homework.app.domain;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController {

    private static final String PATH = "/error";


    @RequestMapping(value = PATH)
    public String error() {
        return "Error, please contact with administrator";
    }


    @Override
    public String getErrorPath() {
        return PATH;
    }
}
