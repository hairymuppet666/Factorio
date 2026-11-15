package utils;

import types.lua.LuaItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ItemReader {

    public static List<LuaItem> readFile(String filename) {
        Path filePath = Path.of(filename);

        List<LuaItem> items = new ArrayList<>();
        LuaItem item = null;

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

                if("type = \"item\"".equals(line) || "type = \"module\"".equals(line) || "type = \"ammo\"".equals(line)) {
                    if(item != null) {
                        items.add(item);
                        System.out.println("Line " + i + ": Saving Item " + item);
                    }
                    item = new LuaItem();
                    String type = line.split("\"")[1];

                    item.setType(type);
                }
                if(line.startsWith("name")) {
                    String name = line.split("\"")[1];
                    item.setName(name);
                }
                if(line.startsWith("fuel_category")) {
                    String cat = line.split("\"")[1];
                    cat = cat.trim();
                    item.setFuelCategory(cat);
                }
                if(line.startsWith("fuel_value")) {
                    String value = line.split("\"")[1];
                    value = value.trim();
                    double fuelVal = Double.parseDouble(value.substring(0, value.length()-2));
                    if(value.endsWith("KJ")) {
                        fuelVal /= 1000;
                    }
                    else if(value.endsWith("GJ")) {
                        fuelVal *= 1000;
                    }
                    else if(value.endsWith("TJ")) {
                        fuelVal *= 1000000;
                    }
                    item.setFuelValue(fuelVal);
                }
                if(line.startsWith("effect")) {
                    if(line.contains("speed")) {
                        parseLine(line, item);
                    } else {
                        while (true) {
                            i++;
                            line = lines.get(i);
                            line = line.trim();
                            if(line.endsWith(","))
                            {
                                line = line.substring(0, line.length() - 1);
                            }
                            line = line.trim();
                            if (line.startsWith("productivity")) {
                                String value = line.split("=")[1];
                                value = value.trim();
                                item.setProdEffect(Double.parseDouble(value));
                            }
                            if (line.startsWith("speed")) {
                                String value = line.split("=")[1];
                                value = value.trim();
                                item.setSpeedEffect(Double.parseDouble(value));
                            }
                            if ("}".equals(line)) {
                                break;
                            }
                        }
                    }
                }
            }
            items.add(item);

            System.out.println("Line " + lines.size() + ": Saving Item " + item);

            System.out.println(items.size());

            return items;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parseLine(String line, LuaItem mainItem) {
        int index = line.indexOf("=");
        line = line.substring(index + 1);
        line = line.replaceAll("\\{", "");
        line = line.replaceAll("}", "");
        String[] values = line.split(",");
        for (String value : values) {
            value = value.trim();
            if (value.startsWith("productivity")) {
                String val = value.split("=")[1];
                val = val.trim();
                mainItem.setProdEffect(Double.parseDouble(val));
            }
            if (value.startsWith("speed")) {
                String val = value.split("=")[1];
                val = val.trim();
                mainItem.setSpeedEffect(Double.parseDouble(val));
            }
        }
    }
}
