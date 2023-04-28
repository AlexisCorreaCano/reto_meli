package com.alexis.domain.model;

import java.util.ArrayList;
import java.util.Date;

public class Result {
    public String id;
    public String title;
    public String condition;
    public String thumbnail_id;
    public String catalog_product_id;
    public String listing_type_id;
    public String permalink;
    public String buying_mode;
    public String site_id;
    public String category_id;
    public String domain_id;
    public String thumbnail;
    public String currency_id;
    public int order_backend;
    public double price;
    public Object original_price;
    public Object sale_price;
    public int sold_quantity;
    public int available_quantity;
    public Object official_store_id;
    public boolean use_thumbnail_id;
    public boolean accepts_mercadopago;
    public ArrayList<String> tags;
    public Shipping shipping;
    public Date stop_time;
    public Seller seller;
    public SellerAddress seller_address;
    public Address address;
    public ArrayList<Attribute> attributes;
    public Installments installments;
    public Object winner_item_id;
    public boolean catalog_listing;
    public Object discounts;
    public ArrayList<Object> promotions;
    public Object inventory_id;
    public String variation_id;
    public ArrayList<String> variation_filters;
    public Object variations_data;
    public DifferentialPricing differential_pricing;
}
