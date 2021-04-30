package RecipesScreen;

import androidx.annotation.NonNull;

public enum RecipesTypes {
    LOW_FAT("Low-Fat"),
    LOW_CARB("Low-Carb"),
    HIGH_PROTEIN("High-Protein");

    RecipesTypes(String type) {
    }

    @NonNull
    @Override
    public String toString() {
        switch (super.toString()){
            case "LOW_FAT":
                return "Low-Fat";
            case "LOW_CARB":
                return "Low-Carb";
            case "HIGH_PROTEIN":
                return "High-Protein";
            default:
                return null;
        }
    }
}
