package types.entities;

import manager.LuaEntityManager;
import types.lua.LuaEntity;

public class BasicEntity extends AEntity {

    public BasicEntity(String name, int x, int y) {
        super(LuaEntityManager.getEntity(name), x, y);
    }

    @Override
    public String getBlueprintString(int entityNumber) {
        String rv = getCommonBlueprintString(entityNumber);
        rv += "}";

        return rv;
    }


}
