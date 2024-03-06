package com.dh.TPIdiego_cata.controller;

import com.dh.TPIdiego_cata.service.IDomicilioService;
import com.dh.TPIdiego_cata.service.implementation.DomicilioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("domicilio")
public class DomicilioController {
    private IDomicilioService domicilioService;

    public DomicilioController(DomicilioService domicilioService){
        this.domicilioService = domicilioService;
    }
}
