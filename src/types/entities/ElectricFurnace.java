package types.entities;

import manager.LuaEntityManager;
import types.lua.LuaRecipe;

public class ElectricFurnace extends AEntity {

    public ElectricFurnace(int x, int y) {
        super(LuaEntityManager.getEntity("electric-furnace"), x, y);
    }

    @Override
    public String getBlueprintString(int entityNumber) {
        String rv = getCommonBlueprintString(entityNumber);

        rv += "}";

        return rv;
    }

}
