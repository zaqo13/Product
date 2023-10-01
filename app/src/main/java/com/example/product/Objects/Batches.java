package com.example.product.Objects;

import org.json.JSONObject;

public class Batches {



    private String product_id;
    private String variant_id;
    private String batch_no;
    private String qty;
    private String retailer_rate;
    private String trade_rate_vendor_A_type;
    private String trade_rate_vendor_B_type;
    private String trade_rate_vendor_C_type;
    private String trade_rate_vendor_D_type;
    private String gst_percent;
    private String gst_amount;
    private String scheme_percentage;
    private String discount;
    private String created_by;
    private String created_at;
    private String updated_by;
    private String updated_at;
    private String deleted_by;
    private String deleted_at;
    private String mrp;
    private String id;


    public Batches(JSONObject object) {
        try {
            this.id = object.getString("id");
            this.product_id = object.getString("product_id");
            this.variant_id = object.getString("variant_id");
            this.batch_no = object.getString("batch_no");
            this.qty = object.getString("qty");
            this.retailer_rate = object.getString("retailer_rate");
            this.trade_rate_vendor_A_type = object.getString("trade_rate_vendor_A_type");
            this.trade_rate_vendor_B_type = object.getString("trade_rate_vendor_B_type");
            this.trade_rate_vendor_C_type = object.getString("trade_rate_vendor_C_type");
            this.trade_rate_vendor_D_type = object.getString("trade_rate_vendor_D_type");
            this.gst_percent = object.getString("gst_percent");
            this.gst_amount = object.getString("gst_amount");
            this.scheme_percentage = object.getString("scheme_percentage");
            this.discount = object.getString("discount");
            this.created_at = object.getString("created_at");
            this.mrp = object.getString("mrp");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setVariant_id(String variant_id) {
        this.variant_id = variant_id;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void setRetailer_rate(String retailer_rate) {
        this.retailer_rate = retailer_rate;
    }

    public void setTrade_rate_vendor_A_type(String trade_rate_vendor_A_type) {
        this.trade_rate_vendor_A_type = trade_rate_vendor_A_type;
    }

    public void setTrade_rate_vendor_B_type(String trade_rate_vendor_B_type) {
        this.trade_rate_vendor_B_type = trade_rate_vendor_B_type;
    }

    public void setTrade_rate_vendor_C_type(String trade_rate_vendor_C_type) {
        this.trade_rate_vendor_C_type = trade_rate_vendor_C_type;
    }

    public void setTrade_rate_vendor_D_type(String trade_rate_vendor_D_type) {
        this.trade_rate_vendor_D_type = trade_rate_vendor_D_type;
    }

    public void setGst_percent(String gst_percent) {
        this.gst_percent = gst_percent;
    }

    public void setGst_amount(String gst_amount) {
        this.gst_amount = gst_amount;
    }

    public void setScheme_percentage(String scheme_percentage) {
        this.scheme_percentage = scheme_percentage;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setDeleted_by(String deleted_by) {
        this.deleted_by = deleted_by;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public void setId(String id) {
        this.id = id;
    }




//    getter and setter methods
    public String getId() {
        return id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getVariant_id() {
        return variant_id;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public String getQty() {
        return qty;
    }

    public String getRetailer_rate() {
        return retailer_rate;
    }

    public String getTrade_rate_vendor_A_type() {
        return trade_rate_vendor_A_type;
    }

    public String getTrade_rate_vendor_B_type() {
        return trade_rate_vendor_B_type;
    }

    public String getTrade_rate_vendor_C_type() {
        return trade_rate_vendor_C_type;
    }

    public String getTrade_rate_vendor_D_type() {
        return trade_rate_vendor_D_type;
    }

    public String getGst_percent() {
        return gst_percent;
    }

    public String getGst_amount() {
        return gst_amount;
    }

    public String getScheme_percentage() {
        return scheme_percentage;
    }

    public String getDiscount() {
        return discount;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getDeleted_by() {
        return deleted_by;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getMrp() {
        return mrp;
    }

}
