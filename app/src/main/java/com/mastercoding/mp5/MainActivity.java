package com.mastercoding.mp5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final int TEXT_REQUEST = 1;
    public static final String KEY_RESULT = "result";

    private RecyclerView recyclerView;
    private MealItemAdapter mealAdapter;
    private ArrayList<AddItem> mealListDisplay;

    private LinearLayoutManager layoutManager;

    ItemTouchHelper itemTouchHelper;
    private int dragDirections =  ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    private int swipeDirections = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Start");


        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(dragDirections, swipeDirections) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                Log.d(TAG, "onMove: Start");
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mealListDisplay, from, to);
                mealAdapter.notifyItemMoved(from, to);
                Log.d(TAG, "onMove: to: " + to);
                Log.d(TAG, "onMove: from: " + from);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mealListDisplay.remove(viewHolder.getAbsoluteAdapterPosition());
                mealAdapter.notifyItemRemoved(viewHolder.getAbsoluteAdapterPosition());
                Log.d(TAG, "onSwiped: direction = " + direction);
            }
        });

        initRecyclerView();
        initData();

        FloatingActionButton fab = findViewById(R.id.fab);
        Log.d(TAG, "onCreate: End");
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initData() {
        Log.d(TAG, "initData: Start");

        mealListDisplay.clear();
        String[] titleMeal = getResources().getStringArray(R.array.title_meal);
        TypedArray imageMeal = getResources().obtainTypedArray(R.array.image_meal);

        for (int i = 0; i < titleMeal.length; i++) {
            mealListDisplay.add(new AddItem(titleMeal[i], imageMeal.getResourceId(i, 0)));
        }
        mealAdapter.notifyDataSetChanged();
        Log.d(TAG, "initData: End");
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: Start");
        mealListDisplay = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        mealAdapter = new MealItemAdapter(this, mealListDisplay);

        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mealAdapter);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        Log.d(TAG, "initRecyclerView: End");
    }

    public void onFabClick(View view) {
        Log.d(TAG, "onFabClick: Start");
        Intent intent = new Intent(this, AddToCart.class);
        startActivityForResult(intent, TEXT_REQUEST);
        Log.d(TAG, "onFabClick: End");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: Start");
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data != null ? data.getStringExtra(AddToCart.KEY_TO_ADD_MEAL) : null;
                mealAdapter.add(reply);
            }
        }
        Log.d(TAG, "onActivityResult: End");
    }
}