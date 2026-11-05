package types.entities;

import types.enums.Direction;
import types.lua.LuaEntity;

public abstract class ADirectionEntity extends AEntity {

    private Direction direction;

    public ADirectionEntity(LuaEntity entity, int x, int y, Direction direction) {
        super(entity, x, y);
        this.direction = direction;
    }

    public String getCommonDirectionBlueprintString(int entityNumber) {
        String rv = getCommonBlueprintString(entityNumber);

        if(direction != Direction.DOWN) {
            rv += ",";
            rv += "\"direction\":" + direction.getCode();
        }

        return rv;
    }
}
