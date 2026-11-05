package types.entities;

import manager.LuaEntityManager;
import types.enums.Direction;

public class BulkInserter extends ADirectionEntity {
    public BulkInserter(int x, int y, Direction direction) {
        super(LuaEntityManager.getBulkInserter(), x, y, direction);
    }

    @Override
    public String getBlueprintString(int entityNumber) {
        String rv = getCommonDirectionBlueprintString(entityNumber);

        rv += "}";

        return rv;
    }
}
