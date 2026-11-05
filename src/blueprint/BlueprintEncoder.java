package blueprint;

import types.entities.AEntity;
import types.entities.Factory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;

public class BlueprintEncoder {

    public static String createBlueprint(Factory factory) throws IOException {

        String blueprint = "";

        blueprint += "{";
        blueprint += "\"blueprint\":{";
        blueprint += getIcons("assembling-machine-3");
        blueprint += "\"entities\":[";

        int entityNumber = 1;

        for(AEntity entity : factory.getEntities()) {
            blueprint += entity.getBlueprintString(entityNumber);
            entityNumber++;
            if(entityNumber <= factory.getEntities().size()) {
                blueprint += ",";
            }
        }

        blueprint += "],";
        blueprint += "\"item\":\"blueprint\",";
        blueprint += "\"version\":562949958467584";

        blueprint += "}}";

        System.out.println(blueprint);

        String encodedBlueprint = encodeBlueprint(blueprint);

        return encodedBlueprint;
    }

    public static byte[] compress(String data) throws IOException {
        // Convert the JSON string to UTF-8 bytes
        byte[] inputBytes = data.getBytes(StandardCharsets.UTF_8);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (DeflaterOutputStream deflaterOut = new DeflaterOutputStream(out)) {
            deflaterOut.write(inputBytes);
        } // Closing the stream flushes and finishes the compression

        return out.toByteArray();
    }

    private static String encodeBlueprint(String blueprint) throws IOException {
        byte[] encoded = compress(blueprint);

        Base64.Encoder encoder = Base64.getEncoder();

        return "0" + encoder.encodeToString(encoded);

    }

    private static String getIcons(String name) {
        return "\"icons\":[{\"signal\":{\"name\":\"" + name + "\"},\"index\":1}],";
    }


}
