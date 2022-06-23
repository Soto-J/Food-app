package com.mastercoding.mp5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MealItemAdapter extends RecyclerView.Adapter<MealItemAdapter.ViewHolder> {
    private ArrayList<AddItem> mealItemList; // <mealItem>
    private Context context;

    public MealItemAdapter(Context context, ArrayList<AddItem> mealArrayList) {
        this.context = context;
        mealItemList = mealArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealItemAdapter.ViewHolder holder, int position) {
//        int image = mealItemList.get(position).getImageView1();
//        String mealName = mealItemList.get(position).getTextView1();
        AddItem currentMeal = mealItemList.get(position);
//        holder.setData(currentMeal);
        holder.bindItem(currentMeal);
    }

    @Override
    public int getItemCount() {
        return mealItemList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void add(String reply) {
        AddItem addItem = new AddItem(reply);
        mealItemList.add(addItem);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView textView;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_img_title);
            imageView = itemView.findViewById(R.id.iv_1);
        }

//        public void setData(int image, String mealName) {
//            imageView.setImageResource(image);
//            textView.setText(mealName);
//        }

        public void bindItem(AddItem currentMeal) {
            try {
                imageView.setImageResource(currentMeal.getImageId());
                Glide.with(context).load(currentMeal.getImageId()).into(imageView);
            } catch (Exception e) {
                imageView.setImageResource(R.drawable.ic_launcher_background);
            }
            textView.setText(currentMeal.getTitle());
        }
    }
}
