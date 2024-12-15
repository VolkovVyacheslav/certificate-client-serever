package com.volkov.client.controller;

import com.volkov.payload.CertificateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final MessageSource messageSource;

    @GetMapping("new")
    public String getNewCertificates(Model model){
        return "/certificates/index/new";
    }

    @PostMapping("create")
    public String createCertificateSchedule(CertificateModel certificateModel,
                                            Model model){
        return "/certificates/index/certificate";
    }

    @GetMapping("index")
    public String getMainPage(Model model) {
        return "/certificates/index";
    }
}
