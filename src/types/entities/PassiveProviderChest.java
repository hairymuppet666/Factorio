package types.entities;

import manager.LuaEntityManager;
import types.lua.LuaEntity;

public class PassiveProviderChest extends AEntity {
    public PassiveProviderChest(int x, int y) {
        super(LuaEntityManager.getEntity("passive-provider-chest"), x, y);
    }

    @Override
    public String getBlueprintString(int entityNumber) {
        return getCommonBlueprintString(entityNumber) + "}";
    }
}
