package data;

import types.lua.LuaEntity;
import types.lua.LuaItem;
import types.lua.LuaRecipe;
import utils.EntitiesReader;
import utils.ItemReader;
import utils.RecipeReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LuaDataStore {

    private static final String INSTALL_DIR = "C:\\Program Files (x86)\\Steam\\steamapps\\common\\Factorio";

    private static LuaDataStore instance;

    private Map<String, LuaRecipe> recipeMap = new HashMap<>();
    private Map<String, LuaEntity> entityMap = new HashMap<>();
    private Map<String, LuaItem> itemMap = new HashMap<>();

    public static LuaDataStore getInstance() {
        if(instance == null) {
            instance = new LuaDataStore();
        }
        return instance;
    }

    private LuaDataStore() {
        List<LuaRecipe> recipes = RecipeReader.readFile(INSTALL_DIR + "\\data\\base\\prototypes\\recipe.lua");
        recipeMap.putAll(recipes.stream().filter(e -> e.getName() != null).collect(Collectors.toMap(LuaRecipe::getName, Function.identity())));

        List<LuaEntity> mainEntities = EntitiesReader.readFile(INSTALL_DIR + "\\data\\base\\prototypes\\entity\\entities.lua");
        entityMap.putAll(mainEntities.stream().filter(e -> e.getName() != null).collect(Collectors.toMap(LuaEntity::getName, Function.identity())));

        List<LuaEntity> beltEntities = EntitiesReader.readFile(INSTALL_DIR + "\\data\\base\\prototypes\\entity\\transport-belts.lua");
        entityMap.putAll(beltEntities.stream().filter(e -> e.getName() != null).collect(Collectors.toMap(LuaEntity::getName, Function.identity())));

        List<LuaItem> items = ItemReader.readFile(INSTALL_DIR + "\\data\\base\\prototypes\\item.lua");
        itemMap.putAll(items.stream().filter(e -> e.getName() != null).collect(Collectors.toMap(LuaItem::getName, Function.identity())));
    }


    public Map<String, LuaRecipe> getRecipeMap() {
        return recipeMap;
    }

    public Map<String, LuaEntity> getEntityMap() {
        return entityMap;
    }

    public Map<String, LuaItem> getItemMap() {
        return itemMap;
    }
}
