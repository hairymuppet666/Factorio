package scripts;

import utils.EntitiesReader;
import utils.ItemReader;
import utils.RecipeReader;

public class TestJson {
    public static void main(String[] args) {

        try {
            RecipeReader.readFile("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe.lua");
            EntitiesReader.readFile("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Factorio\\data\\base\\prototypes\\entity\\entities.lua");
            EntitiesReader.readFile("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Factorio\\data\\base\\prototypes\\entity\\transport-belts.lua");
            ItemReader.readFile("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Factorio\\data\\base\\prototypes\\item.lua");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
