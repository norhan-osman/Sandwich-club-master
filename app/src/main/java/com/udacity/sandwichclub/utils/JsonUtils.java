package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

        private static List<String> ingredients_list = new ArrayList<>();
        private static List<String> alsoKnownAs_list = new ArrayList<>();

        public static Sandwich parseSandwichJson(String json) throws JSONException {

            JSONObject jObject = new JSONObject(json);

            JSONArray jArrary_ingredients=  jObject.getJSONArray("ingredients");

            JSONObject jObject_sandwich = jObject.getJSONObject("name");

            JSONArray jArrary_alsoKnownAs =  jObject_sandwich.getJSONArray("alsoKnownAs");

            Sandwich sandwich = new Sandwich();

            sandwich.setMainName(jObject_sandwich.getString("mainName"));
            sandwich.setImage(jObject.getString("image"));
            sandwich.setPlaceOfOrigin(jObject.getString("placeOfOrigin"));
            sandwich.setDescription(jObject.getString("description"));

            for (int i = 0 ; i<jArrary_ingredients.length() ; i++)
            {
                String ingredients = jArrary_ingredients.getString(i);

                ingredients_list.add(ingredients);

                sandwich.setIngredients(ingredients_list);


            }
            for (int i = 0 ; i<jArrary_alsoKnownAs.length() ; i++)
            {
                String  alsoKnownAs = jArrary_alsoKnownAs.getString(i);

                alsoKnownAs_list.add(alsoKnownAs);

                sandwich.setAlsoKnownAs(alsoKnownAs_list);


            }

            return sandwich;
        }
    }


