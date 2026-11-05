package types.lua;

import java.util.ArrayList;
import java.util.List;

public class LuaEntity {

    private String name;
    private final List<String> craftingCategories = new ArrayList<>();
    private double speed;
    private int moduleSlots;

    private int height;
    private int width;

    private double extensionSpeed;
    private double rotationSpeed;

    private double supplyAreaDistance;

    private double beaconDistributionEffectivity;

    private double electricityWireDistance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + getCraftingCategories() + " (" + width + ", " + height + ")";
    }

    public List<String> getCraftingCategories() {
        return craftingCategories;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getModuleSlots() {
        return moduleSlots;
    }

    public void setModuleSlots(int moduleSlots) {
        this.moduleSlots = moduleSlots;
    }

    public double getExtensionSpeed() {
        return extensionSpeed;
    }

    public void setExtensionSpeed(double extensionSpeed) {
        this.extensionSpeed = extensionSpeed;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public double getSupplyAreaDistance() {
        return supplyAreaDistance;
    }

    public void setSupplyAreaDistance(double supplyAreaDistance) {
        this.supplyAreaDistance = supplyAreaDistance;
    }

    public double getBeaconDistributionEffectivity() {
        return beaconDistributionEffectivity;
    }

    public void setBeaconDistributionEffectivity(double beaconDistributionEffectivity) {
        this.beaconDistributionEffectivity = beaconDistributionEffectivity;
    }

    public double getElectricityWireDistance() {
        return electricityWireDistance;
    }

    public void setElectricityWireDistance(double electricityWireDistance) {
        this.electricityWireDistance = electricityWireDistance;
    }

}
