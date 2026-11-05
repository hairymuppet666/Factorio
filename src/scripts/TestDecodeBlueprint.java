package scripts;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.InflaterInputStream;

public class TestDecodeBlueprint {

    public static String blueprint = "0eNqdkNsKgzAMht8l1xHWaj30VUSGuiABrVLrNpG++6qD3Wy78SYn/v8LyQZNv9Bk2TjQG3A7mhl0ucHMnan7fWbqgUBDw11EPbXOchtNY0/gEdjc6Ala+AqBjGPH9LYfzXo1y9CQDQL8j0GYxjk4R7NvC7RE5Agr6Ehkwnv8gslzsLj4BYvPwS6Z329+sD0OLgUqlKgqLGXIMcqjUqFSVdCxo2Ff8fk1wp3sfJBVKoukKFSepFkI3r8AijmBTQ==";

    public static byte[] decompress(byte[] compressedData) throws IOException {
        ByteArrayInputStream bin = new ByteArrayInputStream(compressedData);
        try (InflaterInputStream inflaterIn = new InflaterInputStream(bin);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inflaterIn.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        }
    }

    public static void main() throws IOException {

        Base64.Decoder decoder = Base64.getDecoder();

        byte[] decodedBytes = decoder.decode(blueprint.substring(1));

        byte[] decompressedBytes = decompress(decodedBytes);


        String decoded = new String(decompressedBytes);

        System.out.println(decoded);
    }
}
