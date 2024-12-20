package com.volkov.client.controller;

import com.volkov.payload.CertificateModel;
import com.volkov.payload.CertificateModels;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/certificates")
public class MainController {

    private final MessageSource messageSource;

    private CertificateModels certificateModels;

    @GetMapping("new")
    public String getNewCertificates(Model model){
        return "/certificates/index/new";
    }

    @PostMapping("create")
    public String createCertificateSchedule(CertificateModel certificateModel,
                                            Model model){
        return "/certificates/index/certificate";
    }

    @GetMapping("")
    public String getMainPage(NoSuchElementException exception, Model model) {
        this.certificateModels = new CertificateModels();
        model.addAttribute("paloads", certificateModels);
        model.addAttribute("errors",
                exception);
        return "/certificates/index";
    }
}
