package types.entities;

import manager.LuaEntityManager;
import types.lua.LuaEntity;
import types.lua.LuaRecipe;

public class AssemblyMachine extends AEntity {

    private LuaRecipe recipe;

    public AssemblyMachine(int x, int y, String recipe) {
        super(LuaEntityManager.getAssemblyMachine(), x, y);
        this.recipe = LuaEntityManager.getRecipe(recipe);
    }

    @Override
    public String getBlueprintString(int entityNumber) {
        String rv = getCommonBlueprintString(entityNumber);

        rv += ",";
        rv += "\"recipe\":\"" + recipe.getName() + "\",";
        rv += "\"recipe_quality\":\"normal\"";

        rv += "}";

        return rv;
    }

}
