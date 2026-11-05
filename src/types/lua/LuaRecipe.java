package types.lua;

import java.util.HashMap;
import java.util.Map;

public class LuaRecipe {

    private String name;
    private String category;
    private double time;
    private final Map<String, Integer> ingredients = new HashMap<>();
    private final Map<String, Integer> outputs = new HashMap<>();
    private boolean prod;

    public LuaRecipe() {

    }

    @Override
    public String toString() {
        return name + " (" + getTime() + ")";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTime() {
        if(time == 0) {
            return 0.5;
        }
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Map<String, Integer> getOutputs() {
        return outputs;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public boolean isProd() {
        return prod;
    }

    public void setProd(boolean prod) {
        this.prod = prod;
    }

}
