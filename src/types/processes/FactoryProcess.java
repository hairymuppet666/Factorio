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

    private Map<String, Double> outputsProvided = new HashMap<>();

    private Map<String, Double> inputsRequired = new HashMap<>();

    public FactoryProcess(String product, ProcessType processType, double outputPerSecond) {
        this.recipe = LuaEntityManager.getRecipe(product);

        if(recipe.getCategory() == null) {
            this.machine = LuaEntityManager.getAssemblyMachine();
        } else if(recipe.getCategory().equals("smelting")) {
            this.machine = LuaEntityManager.getEntity("electric-furnace");
        }

        this.processType = processType;
        this.outputsProvided.put(product, outputPerSecond);

        double outputAmount = recipe.getOutputs().get(product);

        for(Map.Entry<String, Integer> entry : recipe.getIngredients().entrySet()) {
            double inputAmount = entry.getValue() * outputPerSecond / outputAmount;
            inputsRequired.put(entry.getKey(), inputAmount);
        }

    }

    public FactoryProcess(String product, LuaRecipe recipe, ProcessType processType, double outputPerSecond) {
        if(recipe.getCategory() == null) {
            this.machine = LuaEntityManager.getAssemblyMachine();
        } else if(recipe.getCategory().equals("smelting")) {
            this.machine = LuaEntityManager.getEntity("electric-furnace");
        }

        this.processType = processType;
        for(Map.Entry<String, Integer> entry : recipe.getOutputs().entrySet()) {
            double ops = outputPerSecond;
            if(!product.equals(entry.getKey())) {
                int defaultOPS = recipe.getOutputs().get(product);
                ops = outputPerSecond * (entry.getValue() * 1.0) / (defaultOPS * 1.0);
            }

            this.outputsProvided.put(entry.getKey(), ops);
        }

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

    public Map<String, Double> getOutputsProvided() {
        return outputsProvided;
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
                ", outputsProvided=" + outputsProvided +
                ", inputsRequired=" + inputsRequired +
                '}';
    }
}
