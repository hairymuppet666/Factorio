package types.entities;

import types.lua.LuaEntity;

public abstract class AEntity {

    protected final LuaEntity luaEntity;

    protected int x;
    protected int y;

    public AEntity(LuaEntity entity, int x, int y) {
        this.luaEntity = entity;
        this.x = x;
        this.y = y;
    }

    public LuaEntity getLuaEntity() {
        return luaEntity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract String getBlueprintString(int entityNumber);

    protected double mapX() {
        double newX = x + (luaEntity.getWidth() / 2.0);
        return newX;
    }

    protected double mapY() {
        double newY = y + (luaEntity.getHeight() / 2.0);
        return newY * -1;
    }

    protected String getCommonBlueprintString(int entityNumber) {
        String rv = "";
        rv += "{";
        rv += "\"entity_number\":" + entityNumber + ",";
        rv += "\"name\":\"" + luaEntity.getName() + "\",";
        rv += "\"position\":{";
        rv += "\"x\":" + mapX() + ",";
        rv += "\"y\":" + mapY();
        rv += "}";
        return rv;
    }

}
