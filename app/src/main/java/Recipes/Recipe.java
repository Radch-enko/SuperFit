package Recipes;

import androidx.annotation.NonNull;

public class Recipe {
    private String title, kcal, info, type;
    private String img;
    private String protein, fat, carbs;

    public Recipe(String title, String kcal, String protein, String fat, String carbs, String img, String type){
        this.title = title;
        this.kcal = kcal;
        this.info = protein + " • " + fat + " • " + carbs;
        this.img = img;
        this.type = type;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String protein, String fat, String carbs) {
        this.info = protein + " • " + fat + " • " + carbs;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "title: " + title + " kcal: " + kcal + "protein: " + protein + " fat: " + fat
                + " carbs: " + carbs + " type: " + type;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }
}
