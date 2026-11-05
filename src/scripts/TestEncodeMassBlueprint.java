package scripts;

import blueprint.BlueprintEncoder;
import manager.FactoryManager;
import manager.LuaEntityManager;
import types.entities.*;
import types.enums.Direction;
import types.lua.LuaEntity;

import java.io.IOException;

public class TestEncodeMassBlueprint {

    public static void main() throws IOException {
//        Factory factory = FactoryManager.buildFactory("automation-science-pack", 3);
        Factory factory = FactoryManager.buildFactory("logistic-science-pack", 5);


        String blueprint = BlueprintEncoder.createBlueprint(factory);

        System.out.println(blueprint);
    }

}
