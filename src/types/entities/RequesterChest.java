package types.entities;

import manager.LuaEntityManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequesterChest extends AEntity {

    private LinkedHashMap<String, Integer> requests = new LinkedHashMap<>();

    public RequesterChest(int x, int y) {
        super(LuaEntityManager.getEntity("requester-chest"), x, y);
    }

    public void addRequest(String item, int amount) {
        requests.put(item, amount);
    }

    @Override
    public String getBlueprintString(int entityNumber) {
        String rv = getCommonBlueprintString(entityNumber);
        rv += ",";

        rv += "\"request_filters\":{";
        rv += "\"sections\":[";
        rv += "{";
        rv += "\"index\":1,";
        rv += "\"filters\":[";

        int i = 1;
        for(Map.Entry<String, Integer> entry : requests.entrySet()) {
            rv += "{";
            rv += "\"index\":" + i + ",";
            rv += "\"name\":\"" + entry.getKey() + "\",";
            rv += "\"quality\":\"normal\",";
            rv += "\"comparator\":\"=\",";
            rv += "\"count\":" + entry.getValue();
            rv += "}";
            if(i < requests.size()) {
                rv += ",";
            }
            i++;
        }

        rv += "]";
        rv += "}";
        rv += "]";
        rv += "}";

        rv += "}";

        return rv;
    }
}
