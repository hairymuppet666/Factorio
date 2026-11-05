package manager;

import types.enums.ProcessType;
import types.lua.LuaRecipe;
import types.processes.FactoryProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessManager {

    public static List<FactoryProcess> calculateProcesses(String product, ProcessType type, double perSecond) {

        List<FactoryProcess> processes = new ArrayList<>();

        addSubProcesses(processes, product, type, perSecond);

        return processes;
    }

    private static void addSubProcesses(List<FactoryProcess> processes, String product, ProcessType type, double perSecond) {
        FactoryProcess process = new FactoryProcess(product, type, perSecond);

        processes.add(process);

        for(Map.Entry<String, Double> entry : process.getInputsRequired().entrySet()) {
            if(LuaEntityManager.getRecipe(entry.getKey()) != null) {
                addSubProcesses(processes, entry.getKey(), type, entry.getValue());
            }
        }
    }
}
