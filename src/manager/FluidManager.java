package manager;

import types.lua.LuaEntity;

public class FluidManager {

    public static Double WATER_STARTING_TEMP = 15.0;
    public static Double WATER_HEAT_CAPACITY_JOULES = 200.0;

    public static double determineSteamOutputPerSecond(LuaEntity boiler) {
        double outputRate = boiler.getEnergyConsumptionMW() * 1000000;
        outputRate /= WATER_HEAT_CAPACITY_JOULES;
        outputRate /= (boiler.getTargetTemperature() - WATER_STARTING_TEMP);


        return outputRate;
    }
}
