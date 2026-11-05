package manager;

import data.LuaDataStore;
import types.lua.LuaEntity;
import types.lua.LuaRecipe;

public class LuaEntityManager {

    public static LuaEntity getAssemblyMachine() {
        LuaEntity entity = LuaDataStore.getInstance().getEntityMap().get("assembling-machine-3");
        return entity;
    }

    public static LuaRecipe getRecipe(String recipe) {
        return LuaDataStore.getInstance().getRecipeMap().get(recipe);
    }

    public static LuaEntity getEntity(String name) {
        return LuaDataStore.getInstance().getEntityMap().get(name);
    }

    public static LuaEntity getBulkInserter() {
        LuaEntity entity = LuaDataStore.getInstance().getEntityMap().get("bulk-inserter");
        return entity;
    }
}
