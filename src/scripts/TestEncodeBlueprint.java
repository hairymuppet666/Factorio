package scripts;

import blueprint.BlueprintEncoder;
import manager.AdvancedProcessManager;
import manager.FluidManager;
import manager.LuaEntityManager;
import types.entities.*;
import types.enums.Direction;
import types.enums.ProcessType;
import types.lua.LuaEntity;

import java.io.IOException;

public class TestEncodeBlueprint {

    public static void main() throws IOException {
        Factory factory = new Factory();

        int height = 50;

        for(int j=0; j<50; j++) {
            int y = j*3;
            factory.getEntities().add(new AssemblyMachine(0, y, "iron-gear-wheel"));
            factory.getEntities().add(new BulkInserter(3, y, Direction.LEFT));
            RequesterChest chest = new RequesterChest(4,y);
            chest.addRequest("iron-plate", 200);
            chest.addRequest("copper-plate", 100);
            factory.getEntities().add(chest);
            factory.getEntities().add(new BulkInserter(3, y+2, Direction.RIGHT));
            factory.getEntities().add(new PassiveProviderChest(4, y+2));
        }

        LuaEntity substation = LuaEntityManager.getEntity("substation");

        int maxElectricityHeight = (height * 3) + 3;

        for(int j = (int) substation.getSupplyAreaDistance() - 1; j < (maxElectricityHeight + substation.getSupplyAreaDistance()); j += substation.getElectricityWireDistance()) {
            factory.getEntities().add(new Substation(5, j));
        }

        String blueprint = BlueprintEncoder.createBlueprint(factory);

        FluidManager.determineSteamOutputPerSecond(LuaEntityManager.getEntity("heat-exchanger"));

        AdvancedProcessManager.calculateProcesses("logistic-science-pack", ProcessType.LOGISTIC, 1);
        AdvancedProcessManager.calculateProcesses("chemical-science-pack", ProcessType.LOGISTIC, 1);

        System.out.println(blueprint);
    }

}
