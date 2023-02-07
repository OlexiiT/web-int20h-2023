package com.example.foodhelper.entities;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Recipe {
    private String dateModified;                //null
    private String idMeal;
    private List<Object> ingredients;
    private String strArea;
    private String strCategory;
    private String strCreativeCommonsConfirmed; //null
    private String strDrinkAlternate;           //null
    private String strImageSource;              //null
    private String strInstructions;
    private String strMeal;
    private String strMealThumb;
    private String strSource;
    private String tags;                        //null
    private String strYoutube;
}
