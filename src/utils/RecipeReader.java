package utils;

import types.LuaRecipe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeReader {

    public static void readFile(String filename) {
        Path filePath = Path.of(filename);

        List<LuaRecipe> recipes = new ArrayList<>();
        LuaRecipe recipe = null;

        try {
            List<String> lines = Files.readAllLines(filePath);

            boolean parsing = false;

            for(int i=0; i<lines.size(); i++) {
                String line = lines.get(i).trim();
                if("data:extend".equals(line)) {
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

                if("type = \"recipe\"".equals(line)) {
                    if(recipe != null) {
                        recipes.add(recipe);
                        if(recipe.getIngredients().isEmpty()) {
                            System.out.println("Could not parse any ingredients for " + recipe);
                        }
//                        System.out.println("Saving Recipe " + recipe);
                    }
                    recipe = new LuaRecipe();
                }
                if(line.startsWith("name")) {
                    String name = line.split("\"")[1];
                    recipe.setName(name);
                }
                if(line.startsWith("energy_required")) {
                    String number = line.split("=")[1];
                    number = number.trim();
                    number = number.trim();
                    recipe.setTime(Double.parseDouble(number));
                }
                if(line.startsWith("allow_productivity")) {
                    String boolValue = line.split("=")[1];
                    boolValue = boolValue.trim();
                    recipe.setProd(Boolean.parseBoolean(boolValue));
                }
                if(line.startsWith("results")) {
                    if(line.contains("amount")) {
                        parseLine(line, recipe.getOutputs());
                    } else {
                        while (true) {
                            i++;
                            line = lines.get(i);
                            line = line.trim();
                            if (line.contains("amount")) {
                                if (line.endsWith(",")) {
                                    line = line.substring(0, line.length() - 1);
                                }
                                while (line.endsWith("}")) {
                                    line = line.substring(0, line.length() - 1);
                                }
                                String[] values = line.split(",");
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
                                recipe.getOutputs().put(outputName, outputAmount);
                            }

                            if ("},".equals(line)) {
                                break;
                            }
                        }
                    }
                }
                if(line.startsWith("ingredients")) {
                    if(line.contains("amount")) {
                        parseLine(line, recipe.getIngredients());
                    } else {
                        while (true) {
                            i++;
                            line = lines.get(i);
                            line = line.trim();
                            if (line.contains("amount")) {
                                if (line.endsWith(",")) {
                                    line = line.substring(0, line.length() - 1);
                                }
                                while (line.endsWith("}")) {
                                    line = line.substring(0, line.length() - 1);
                                }
                                String[] values = line.split(",");
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
                                recipe.getIngredients().put(outputName, outputAmount);
                            }

                            if ("},".equals(line)) {
                                break;
                            }
                        }
                    }
                }
            }
            recipes.add(recipe);

            System.out.println(recipes.size());
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
