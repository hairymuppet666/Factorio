package manager;

import types.enums.ProcessType;
import types.lua.LuaRecipe;
import types.processes.FactoryProcess;
import types.processes.PotentialProcessList;

import java.util.*;

public class AdvancedProcessManager {

    public static String DEFAULT_FUEL = "coal";

    public static List<FactoryProcess> calculateProcesses(String product, ProcessType type, double perSecond) {

        calculateRecipes(product);

        List<FactoryProcess> processes = new ArrayList<>();

//        addSubProcesses(processes, product, type, perSecond);

        return processes;
    }

    private static void calculateRecipes(String product) {

        PotentialProcessList initialList = new PotentialProcessList();

        initialList.inputList.add(product);

        List<PotentialProcessList> allLists = new ArrayList<>();
        allLists.add(initialList);

        processPotentialList(initialList, allLists);

        System.out.println("List Count: " + allLists.size());
        for(PotentialProcessList list : allLists) {
            System.out.println(list);
        }

    }

    private static void processPotentialList(PotentialProcessList list, List<PotentialProcessList> allLists) {

        while (!list.inputList.isEmpty()) {
            String nextProduct = list.inputList.stream().findFirst().get();
            list.inputList.remove(nextProduct);

            // If Product is Steam, do custom stuff here

            List<LuaRecipe> recipes = LuaEntityManager.getAllRecipes(nextProduct);
            if(recipes.isEmpty()) {
                list.finalInputs.add(nextProduct);
            } else if (recipes.size() == 1) {
                list.outputList.add(nextProduct);
                boolean isValid = true;
                for(String output : recipes.get(0).getOutputs().keySet()) {
                    if(!list.outputList.contains(output)) {
                        isValid = false;
                    }
                }
                if(isValid) {
                    list.recipes.add(recipes.get(0));
                    for(String input : recipes.get(0).getIngredients().keySet()) {
                        // TODO need to only exclude this input if there is enough coming in from other sources
                        if(!list.outputList.contains(input)) {
                            System.out.println("Ignoring " + input + ", already making it.");
                            list.inputList.add(input);
                        }
                    }
                } else {
                    // Dead End
                    list.failedInputs.add(nextProduct);
                    list.failedRecipes.add(recipes.get(0));
                    return;
                }

            } else {
                list.outputList.add(nextProduct);
                int i=0;
                PotentialProcessList masterList = new PotentialProcessList(list);
                for(LuaRecipe recipe : recipes) {
                    PotentialProcessList currentList = list;
                    if(i > 0) {
                        currentList = new PotentialProcessList(masterList);
                    }
                    i++;
                    boolean isValid = true;
                    for(String output : recipe.getOutputs().keySet()) {
                        if(!currentList.outputList.contains(output)) {
                            isValid = false;
                        }
                    }
                    if(isValid) {
                        currentList.recipes.add(recipe);
                        for(String input : recipe.getIngredients().keySet()) {
                            // TODO need to only exclude this input if there is enough coming in from other sources
                            if(!currentList.outputList.contains(input)) {
                                System.out.println("Ignoring " + input + ", already making it.");
                                currentList.inputList.add(input);
                            }
                        }
                        if(i > 0) {
                            allLists.add(currentList);
                            processPotentialList(currentList, allLists);
                        }
                    } else {
                        // Dead End
                        currentList.failedInputs.add(nextProduct);
                        currentList.failedRecipes.add(recipe);
                    }
                }
            }

        }

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
