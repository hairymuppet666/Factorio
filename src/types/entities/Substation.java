package types.entities;

import manager.LuaEntityManager;
import types.lua.LuaEntity;

public class Substation extends AEntity {
    public Substation(int x, int y) {
        super(LuaEntityManager.getEntity("substation"), x, y);
    }

    @Override
    public String getBlueprintString(int entityNumber) {
        return getCommonBlueprintString(entityNumber) + "}";
    }
}
