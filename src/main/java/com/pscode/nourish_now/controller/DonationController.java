package com.pscode.nourish_now.controller;

import com.pscode.nourish_now.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("donation")
public class DonationController {

    @Autowired
    private DonationService donationService;
}
