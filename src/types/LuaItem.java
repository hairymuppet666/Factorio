package types;

public class LuaItem {

    private String name;
    private String type;

    private double prodEffect;
    private double speedEffect;

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
}
