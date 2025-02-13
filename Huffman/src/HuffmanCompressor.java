import java.io.*;
import java.util.BitSet;
import java.util.Map;

public class HuffmanCompressor {
    private HuffmanTree huffmanTree = new HuffmanTree();

    private byte[] binaryStringToBytes(String binaryString) {
        BitSet bitSet = new BitSet(binaryString.length());
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                bitSet.set(i);
            }
        }
        return bitSet.toByteArray();
    }

    public void compress(String text, String compressedFileName) {
        huffmanTree.buildTree(text);
        Map<Character, String> codes = huffmanTree.getCodes();

        StringBuilder compressedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            compressedText.append(codes.get(c));
        }

        byte[] compressedBytes = binaryStringToBytes(compressedText.toString());

        try (FileOutputStream fos = new FileOutputStream(compressedFileName + "_compressed.bin")) {
            fos.write(compressedBytes);
        } catch (IOException e) {
            System.out.println("Error writing compressed file: " + e.getMessage());
        }

        StringBuilder codeFileContent = new StringBuilder();
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            codeFileContent.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }
        FileHandler.writeToFile(compressedFileName + "_hfcodes.txt", codeFileContent.toString());

        System.out.println("Encoding complete! Files saved as " + compressedFileName + "_compressed.bin and " + compressedFileName + "_hfcodes.txt");
    }
}
