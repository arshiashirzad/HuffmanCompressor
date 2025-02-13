import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class HuffmanDecompressor {
    private HuffmanTree huffmanTree = new HuffmanTree();

    private String bytesToBinaryString(byte[] bytes) {
        BitSet bitSet = BitSet.valueOf(bytes);
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            binaryString.append(bitSet.get(i) ? '1' : '0');
        }
        return binaryString.toString();
    }

    public void decompress(String compressedFileName) {
        byte[] compressedBytes;
        try (FileInputStream fis = new FileInputStream(compressedFileName + "_compressed.bin")) {
            compressedBytes = fis.readAllBytes();
        } catch (IOException e) {
            System.out.println("Error reading compressed file: " + e.getMessage());
            return;
        }

        String compressedText = bytesToBinaryString(compressedBytes);

        Map<Character, String> codes = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(compressedFileName + "_hfcodes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                char ch = line.charAt(0);
                String code = line.substring(2); // Format: A:101
                codes.put(ch, code);
            }
        } catch (IOException e) {
            System.out.println("Error reading Huffman codes: " + e.getMessage());
            return;
        }

        huffmanTree.rebuildTree(codes);
        String decompressedText = huffmanTree.decompress(compressedText);

        FileHandler.writeToFile(compressedFileName + "_decompressed.txt", decompressedText);
        System.out.println("Decoding complete! File saved as " + compressedFileName + "_decompressed.txt");
    }
}
