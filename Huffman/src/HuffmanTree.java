import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    private HuffmanNode root;

    private static class NodeComparator implements java.util.Comparator<HuffmanNode> {
        public int compare(HuffmanNode x, HuffmanNode y) {
            return x.frequency - y.frequency;
        }
    }

    public void buildTree(String text) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(new NodeComparator());
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode newNode = new HuffmanNode('$', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;
            pq.add(newNode);
        }

        root = pq.poll();
    }

    public Map<Character, String> getCodes() {
        Map<Character, String> codes = new HashMap<>();
        generateCodes(root, "", codes);
        return codes;
    }

    private void generateCodes(HuffmanNode node, String code, Map<Character, String> codes) {
        if (node == null) return;
        if (node.data != '$') codes.put(node.data, code);
        generateCodes(node.left, code + "0", codes);
        generateCodes(node.right, code + "1", codes);
    }

    public String decompress(String compressedText) {
        StringBuilder decompressedText = new StringBuilder();
        HuffmanNode currentNode = root;
        for (char bit : compressedText.toCharArray()) {
            currentNode = (bit == '0') ? currentNode.left : currentNode.right;
            if (currentNode.left == null && currentNode.right == null) {
                decompressedText.append(currentNode.data);
                currentNode = root;
            }
        }
        return decompressedText.toString();
    }

    public void rebuildTree(Map<Character, String> codes) {
        root = new HuffmanNode('$', 0);
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            HuffmanNode currentNode = root;
            for (char bit : entry.getValue().toCharArray()) {
                if (bit == '0') {
                    if (currentNode.left == null) currentNode.left = new HuffmanNode('$', 0);
                    currentNode = currentNode.left;
                } else {
                    if (currentNode.right == null) currentNode.right = new HuffmanNode('$', 0);
                    currentNode = currentNode.right;
                }
            }
            currentNode.data = entry.getKey();
        }
    }
}
