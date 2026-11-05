package types.processes;

import manager.LuaEntityManager;
import types.enums.ProcessType;
import types.lua.LuaEntity;
import types.lua.LuaRecipe;

import java.util.HashMap;
import java.util.Map;

public class FactoryProcess {

    private LuaRecipe recipe;

    private LuaEntity machine;

    private ProcessType processType;

    private double outputPerSecond;

    private Map<String, Double> inputsRequired = new HashMap<>();

    public FactoryProcess(String product, ProcessType processType, double outputPerSecond) {
        this.recipe = LuaEntityManager.getRecipe(product);

        if(recipe.getCategory() == null) {
            this.machine = LuaEntityManager.getAssemblyMachine();
        } else if(recipe.getCategory().equals("smelting")) {
            this.machine = LuaEntityManager.getEntity("electric-furnace");
        }

        this.processType = processType;
        this.outputPerSecond = outputPerSecond;

        double outputAmount = recipe.getOutputs().get(product);

        for(Map.Entry<String, Integer> entry : recipe.getIngredients().entrySet()) {
            double inputAmount = entry.getValue() * outputPerSecond / outputAmount;
            inputsRequired.put(entry.getKey(), inputAmount);
        }

    }

    public LuaRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(LuaRecipe recipe) {
        this.recipe = recipe;
    }

    public LuaEntity getMachine() {
        return machine;
    }

    public void setMachine(LuaEntity machine) {
        this.machine = machine;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public double getOutputPerSecond() {
        return outputPerSecond;
    }

    public void setOutputPerSecond(double outputPerSecond) {
        this.outputPerSecond = outputPerSecond;
    }

    public Map<String, Double> getInputsRequired() {
        return inputsRequired;
    }

    @Override
    public String toString() {
        return "FactoryProcess{" +
                "recipe=" + recipe +
                ", machine=" + machine +
                ", processType=" + processType +
                ", outputPerSecond=" + outputPerSecond +
                ", inputsRequired=" + inputsRequired +
                '}';
    }
}
