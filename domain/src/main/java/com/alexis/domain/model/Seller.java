package com.alexis.domain.model;

import java.util.ArrayList;
import java.util.Date;

public class Seller {
    public int id;
    public String nickname;
    public boolean car_dealer;
    public boolean real_estate_agency;
    public boolean _;
    public Date registration_date;
    public ArrayList<String> tags;
    public String car_dealer_logo;
    public String permalink;
    public SellerReputation seller_reputation;
    public Eshop eshop;
}
