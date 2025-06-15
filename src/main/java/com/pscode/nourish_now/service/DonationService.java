package com.pscode.nourish_now.service;

import com.pscode.nourish_now.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;


}
