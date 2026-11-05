package manager;

import types.entities.*;
import types.enums.Direction;
import types.enums.ProcessType;
import types.lua.LuaEntity;
import types.processes.FactoryProcess;

import java.util.List;
import java.util.Map;

public class FactoryManager {

    public static int REQUEST_SECONDS_COUNT = 30;

    public static Factory buildFactory(String product, double perSecond) {
        Factory factory = new Factory();

        List<FactoryProcess> processes = ProcessManager.calculateProcesses(product, ProcessType.LOGISTIC, perSecond);


        int i = 0;

        for(FactoryProcess process : processes) {
            System.out.println(process);

            double machinesRequiredDouble = process.getOutputPerSecond() * process.getRecipe().getTime() / process.getMachine().getSpeed();

            int machinesRequired = (int) Math.ceil(machinesRequiredDouble);
            System.out.println(machinesRequired);

            for(int j=0; j<machinesRequired; j++) {
                int y = j*3;
                if(process.getMachine().getName().equals("electric-furnace")) {
                    factory.getEntities().add(new ElectricFurnace(i, y));
                } else {
                    factory.getEntities().add(new AssemblyMachine(i, y, process.getRecipe().getName()));
                }
                factory.getEntities().add(new BulkInserter(i+3, y, Direction.LEFT));
                RequesterChest chest = new RequesterChest(i+4,y);
                for(Map.Entry<String, Double> entry : process.getInputsRequired().entrySet()) {
                    chest.addRequest(entry.getKey(), (int) (entry.getValue() * REQUEST_SECONDS_COUNT / machinesRequired));
                }
                factory.getEntities().add(chest);
                factory.getEntities().add(new BulkInserter(i+3, y+2, Direction.RIGHT));
                factory.getEntities().add(new PassiveProviderChest(i+4, y+2));
            }

            LuaEntity substation = LuaEntityManager.getEntity("substation");

            int maxElectricityHeight = (machinesRequired * 3) + 3;

            for(int j = (int) substation.getSupplyAreaDistance() - 1; j < (maxElectricityHeight + substation.getSupplyAreaDistance()); j += substation.getElectricityWireDistance()) {
                factory.getEntities().add(new Substation(i+5, j));
            }

            i += 7;

        }


        // create wires for substations

        return factory;
    }
}
