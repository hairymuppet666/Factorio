package types.processes;

import types.lua.LuaRecipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PotentialProcessList {
    public Set<String> finalInputs = new HashSet<>();
    public Set<String> failedInputs = new HashSet<>();

    public Set<String> outputList = new HashSet<>();
    public Set<String> inputList = new HashSet<>();

    public List<LuaRecipe> recipes = new ArrayList<>();
    public List<LuaRecipe> failedRecipes = new ArrayList<>();

    public PotentialProcessList() {
    }

    public PotentialProcessList(PotentialProcessList list) {
        this.finalInputs.addAll(list.finalInputs);
        this.outputList.addAll(list.outputList);
        this.inputList.addAll(list.inputList);
        this.recipes.addAll(list.recipes);
    }

    @Override
    public String toString() {
        return "PotentialProcessList{" +
                "finalInputs=" + finalInputs +
                ", failedInputs=" + failedInputs +
                ", failedRecipes=" + failedRecipes +
                ", outputList=" + outputList +
                ", inputList=" + inputList +
                ", recipes=" + recipes +
                '}';
    }
}