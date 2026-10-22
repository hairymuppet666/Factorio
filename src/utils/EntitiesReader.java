package utils;

import types.LuaEntity;
import types.LuaRecipe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntitiesReader {

    public static void readFile(String filename) {
        Path filePath = Path.of(filename);

        List<LuaEntity> entities = new ArrayList<>();
        LuaEntity entity = new LuaEntity();

        try {
            List<String> lines = Files.readAllLines(filePath);

            boolean parsing = false;

            int bracketCounter = 0;

            for(int i=0; i<lines.size(); i++) {
                String line = lines.get(i).trim();
                if("data:extend(".equals(lines.get(i)) || "data:extend".equals(lines.get(i))) {
                    parsing = true;
                    continue;
                }
                if(!parsing) {
                    continue;
                }
                if(line.endsWith(","))
                {
                    line = line.substring(0, line.length() - 1);
                }

                for(int j = 0; j<line.length(); j++) {
                    if(line.substring(j, j+1).equals("{")) {
                        bracketCounter++;
                        if(bracketCounter == 2 && entity != null) {
                            entities.add(entity);
                            System.out.println("Line: " + i + ", Saving Entity " + entity);
                            entity = new LuaEntity();
                        }
                    }
                    else if(line.substring(j, j+1).equals("}")) {
                        bracketCounter--;
                    }
                }

                if(line.startsWith("name") && !line.contains("arguments.name") && entity.getName() == null) {
                    String name = line.split("\"")[1];
                    entity.setName(name);
                }
                if(line.startsWith("extension_speed")) {
                    String number = line.split("=")[1];
                    number = number.trim();
                    number = number.trim();
                    entity.setExtensionSpeed(Double.parseDouble(number));
                }
                if(line.startsWith("rotation_speed")) {
                    String number = line.split("=")[1];
                    number = number.trim();
                    number = number.trim();
                    entity.setRotationSpeed(Double.parseDouble(number));
                }
                if(line.startsWith("supply_area_distance")) {
                    String number = line.split("=")[1];
                    number = number.trim();
                    number = number.trim();
                    entity.setBeaconSupplyAreaDistance(Double.parseDouble(number));
                }
                if(line.startsWith("distribution_effectivity") && !line.contains("bonus")) {
                    String number = line.split("=")[1];
                    number = number.trim();
                    number = number.trim();
                    entity.setBeaconDistributionEffectivity(Double.parseDouble(number));
                }
                if(line.startsWith("crafting_speed") || line.startsWith("speed")) {
                    String number = line.split("=")[1];
                    number = number.trim();
                    number = number.trim();
                    entity.setSpeed(Double.parseDouble(number));
                }
                if(line.startsWith("module_slots")) {
                    String number = line.split("=")[1];
                    number = number.trim();
                    number = number.trim();
                    entity.setModuleSlots(Integer.parseInt(number));
                }
                if(line.startsWith("crafting_categories")) {
                    if(line.endsWith("}")) {
                        String value = line.split("\\{")[1];
                        value = value.substring(0, value.length() - 1);
                        String[] cats = value.split(",");
                        for(String cat : cats) {
                            cat = cat.trim();
                            cat = cat.substring(1, cat.length() - 1);
                            cat = cat.trim();
                            entity.getCraftingCategories().add(cat);
                        }
                    } else {
                        System.err.println("Multi Line Crafting Cat");
                    }
                }
                if(line.startsWith("selection_box") && !line.contains("scale")) {
                    String value = line.split("=")[1];
                    value = value.replaceAll("}", "");
                    value = value.replaceAll("\\{", "");
                    String[] vals = value.split(",");
                    double val1 = Double.parseDouble(vals[0]);
                    double val2 = Double.parseDouble(vals[1]);
                    double val3 = Double.parseDouble(vals[2]);
                    double val4 = Double.parseDouble(vals[3]);

                    int width = (int) Math.round(val3 - val1);
                    int height = (int) Math.round(val4 - val2);
                    entity.setHeight(height);
                    entity.setWidth(width);
                }
            }
            entities.add(entity);
            System.out.println("Line: " + lines.size() + ", Saving Entity " + entity);

            System.out.println(entities.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parseLine(String line, Map<String, Integer> theMap) {
        int index = line.indexOf("=");
        line = line.substring(index + 1);
        String[] items = line.split("}");
        for(String item : items) {
            item = item.replaceAll("\\{", "");
            item = item.trim();
            if(item.isEmpty()) {
                continue;
            }

            String[] values = item.split(",");
            String outputName = null;
            int outputAmount = 0;
            for (String value : values) {
                value = value.trim();
                if (value.startsWith("name")) {
                    outputName = value.split("=")[1];
                    outputName = outputName.trim();
                    outputName = outputName.substring(1, outputName.length() - 1);
                }
                if (value.startsWith("amount")) {
                    String number = value.split("=")[1];
                    number = number.trim();
                    outputAmount = Integer.parseInt(number);
                }
            }
            theMap.put(outputName, outputAmount);

        }
    }
}
