package types.lua;

public class LuaItem {

    private String name;
    private String type;

    private double prodEffect;
    private double speedEffect;

    private String fuelCategory;
    private double fuelValueMJ;

    @Override
    public String toString() {
        return name + "(" + prodEffect + ", " + speedEffect + ")";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProdEffect() {
        return prodEffect;
    }

    public void setProdEffect(double prodEffect) {
        this.prodEffect = prodEffect;
    }

    public double getSpeedEffect() {
        return speedEffect;
    }

    public void setSpeedEffect(double speedEffect) {
        this.speedEffect = speedEffect;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFuelCategory() {
        return fuelCategory;
    }

    public void setFuelCategory(String fuelCategory) {
        this.fuelCategory = fuelCategory;
    }

    public double getFuelValueMJ() {
        return fuelValueMJ;
    }

    public void setFuelValue(double fuelValueMJ) {
        this.fuelValueMJ = fuelValueMJ;
    }
}
