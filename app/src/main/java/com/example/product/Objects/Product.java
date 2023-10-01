package com.example.product.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Product {



    private ArrayList<Batches> batchesArrayList = new ArrayList<>();
    private String product_id, variant_id, category_name, product_name, variant, variant_unit,
            variant_unit_name, id, category_id, brand_id, hsn_code, in_stock, gst_percentage,
            status, description, discount_type, featured_image, created_by, updated_by, deleted_by,
            created_at, updated_at, deleted_at, zone_id, narration, pkt_nos, tax_id, retailer_rate,
            trade_rate_vendor_A_type, trade_rate_vendor_B_type, trade_rate_vendor_C_type,
            trade_rate_vendor_D_type, inventory_count, discount_qty, sku, sort_number, tag,
            is_value_pack, display_name;



    public Product(JSONObject object)  {
        try {
            this.product_id = object.getString("product_id");
            this.variant_id = object.getString("variant_id");
            this.category_name = object.getString("category_name");
            this.product_name = object.getString("product_name");
            this.variant = object.getString("variant");
            this.variant_unit = object.getString("variant_unit");
            this.variant_unit_name = object.getString("variant_unit_name");
            this.id = object.getString("id");
            this.category_id = object.getString("category_id");
            this.brand_id = object.getString("brand_id");
            this.hsn_code = object.getString("hsn_code");
            this.in_stock = object.getString("in_stock");
            this.gst_percentage = object.getString("gst_percentage");
            this.status = object.getString("status");
            this.description = object.getString("description");
            this.discount_type = object.getString("discount_type");
            this.featured_image = object.getString("featured_image");
            this.created_by = object.getString("created_by");
            this.updated_by = object.getString("updated_by");
            this.deleted_by = object.getString("deleted_by");
            this.created_at = object.getString("created_at");
            this.updated_at = object.getString("updated_at");
            this.deleted_at = object.getString("deleted_at");
            this.zone_id = object.getString("zone_id");
            this.narration = object.getString("narration");
            this.pkt_nos = object.getString("pkt_nos");
            this.tax_id = object.getString("tax_id");
            this.retailer_rate = object.getString("retailer_rate");
            this.trade_rate_vendor_A_type = object.getString("trade_rate_vendor_A_type");
            this.trade_rate_vendor_B_type = object.getString("trade_rate_vendor_B_type");
            this.trade_rate_vendor_C_type = object.getString("trade_rate_vendor_C_type");
            this.trade_rate_vendor_D_type = object.getString("trade_rate_vendor_D_type");
            this.inventory_count = object.getString("inventory_count");
            this.discount_qty = object.getString("discount_qty");
            this.sku = object.getString("sku");
            this.sort_number = object.getString("sort_number");
            this.tag = object.getString("tag");
            this.is_value_pack = object.getString("is_value_pack");
            this.display_name = object.getString("display_name");

            JSONArray jsonArray =object.getJSONArray("batches");
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                batchesArrayList.add(new Batches(obj));
            }


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }



    public ArrayList<Batches> getBatchesArrayList() {
        return batchesArrayList;
    }

    public void setBatchesArrayList(ArrayList<Batches> batchesArrayList) {
        this.batchesArrayList = batchesArrayList;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(String variant_id) {
        this.variant_id = variant_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getVariant_unit() {
        return variant_unit;
    }

    public void setVariant_unit(String variant_unit) {
        this.variant_unit = variant_unit;
    }

    public String getVariant_unit_name() {
        return variant_unit_name;
    }

    public void setVariant_unit_name(String variant_unit_name) {
        this.variant_unit_name = variant_unit_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getHsn_code() {
        return hsn_code;
    }

    public void setHsn_code(String hsn_code) {
        this.hsn_code = hsn_code;
    }

    public String getIn_stock() {
        return in_stock;
    }

    public void setIn_stock(String in_stock) {
        this.in_stock = in_stock;
    }

    public String getGst_percentage() {
        return gst_percentage;
    }

    public void setGst_percentage(String gst_percentage) {
        this.gst_percentage = gst_percentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public String getFeatured_image() {
        return featured_image;
    }

    public void setFeatured_image(String featured_image) {
        this.featured_image = featured_image;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(String deleted_by) {
        this.deleted_by = deleted_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getPkt_nos() {
        return pkt_nos;
    }

    public void setPkt_nos(String pkt_nos) {
        this.pkt_nos = pkt_nos;
    }

    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    public String getRetailer_rate() {
        return retailer_rate;
    }

    public void setRetailer_rate(String retailer_rate) {
        this.retailer_rate = retailer_rate;
    }

    public String getTrade_rate_vendor_A_type() {
        return trade_rate_vendor_A_type;
    }

    public void setTrade_rate_vendor_A_type(String trade_rate_vendor_A_type) {
        this.trade_rate_vendor_A_type = trade_rate_vendor_A_type;
    }

    public String getTrade_rate_vendor_B_type() {
        return trade_rate_vendor_B_type;
    }

    public void setTrade_rate_vendor_B_type(String trade_rate_vendor_B_type) {
        this.trade_rate_vendor_B_type = trade_rate_vendor_B_type;
    }

    public String getTrade_rate_vendor_C_type() {
        return trade_rate_vendor_C_type;
    }

    public void setTrade_rate_vendor_C_type(String trade_rate_vendor_C_type) {
        this.trade_rate_vendor_C_type = trade_rate_vendor_C_type;
    }

    public String getTrade_rate_vendor_D_type() {
        return trade_rate_vendor_D_type;
    }

    public void setTrade_rate_vendor_D_type(String trade_rate_vendor_D_type) {
        this.trade_rate_vendor_D_type = trade_rate_vendor_D_type;
    }

    public String getInventory_count() {
        return inventory_count;
    }

    public void setInventory_count(String inventory_count) {
        this.inventory_count = inventory_count;
    }

    public String getDiscount_qty() {
        return discount_qty;
    }

    public void setDiscount_qty(String discount_qty) {
        this.discount_qty = discount_qty;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSort_number() {
        return sort_number;
    }

    public void setSort_number(String sort_number) {
        this.sort_number = sort_number;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIs_value_pack() {
        return is_value_pack;
    }

    public void setIs_value_pack(String is_value_pack) {
        this.is_value_pack = is_value_pack;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

}
