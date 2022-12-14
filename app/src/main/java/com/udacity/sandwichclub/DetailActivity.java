package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView textView_description,textView_ingredient,textView_place ,textView_knownas;
    Sandwich sandwich ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        textView_description = findViewById(R.id.description_tv);
        textView_ingredient = findViewById(R.id.ingredients_tv);
        textView_place = findViewById(R.id.origin_tv);
        textView_knownas = findViewById(R.id.also_known_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        }catch (JSONException e){
            e.printStackTrace();

        }
        if(sandwich ==null){
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        textView_description.append(sandwich.getDescription());
        textView_description.append(sandwich.getPlaceOfOrigin());
        for (int i = 0; i < sandwich.getIngredients().size(); i++) {
            textView_ingredient.append(sandwich.getIngredients().get(i));
        }
        try {
            for (int i = 0; i < sandwich.getAlsoKnownAs().size(); i++) {


                textView_knownas.append(sandwich.getAlsoKnownAs().get(i));
            }
        } catch (Exception e) {

        }

    }}