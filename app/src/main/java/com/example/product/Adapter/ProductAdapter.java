package com.example.product.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.product.Objects.Product;
import com.example.product.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private List<Product> productList;
    private OnProductClickListener onProductClickListener;


    public ProductAdapter(List<Product> productArrayList) {
        this.productList = productArrayList;
    }


    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public interface OnProductClickListener {
        void onProductClick(int position);
    }


    public void setOnProductClickListener(OnProductClickListener listener) {
        this.onProductClickListener = listener;
    }


    /**
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     **/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product, parent, false);

        return new ViewHolder(view);
    }


    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Product product = productList.get(position);
        holder.tv_product_category.setText(product.getCategory_name());
        holder.tv_product_display_name.setText(product.getDisplay_name());
        holder.tv_product_price.setText(product.getBatchesArrayList().get(0).getRetailer_rate());
        holder.tv_product_qty.setText(product.getBatchesArrayList().get(0).getQty());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onProductClickListener != null) {

                    if (position != RecyclerView.NO_POSITION) {
                        onProductClickListener.onProductClick(holder.getAdapterPosition());     //onProductClickListener.onProductClick(position);

                    }
                }
            }
        });

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_product_price, tv_product_qty, tv_product_category, tv_product_display_name;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parent_layout);
            tv_product_category = itemView.findViewById(R.id.tv_product_category);
            tv_product_display_name = itemView.findViewById(R.id.tv_product_display_name);
            tv_product_price = itemView.findViewById(R.id.tv_product_price);
            tv_product_qty = itemView.findViewById(R.id.tv_product_qty);
        }

    }

}

