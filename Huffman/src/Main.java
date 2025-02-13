import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to compress or decompress? (c/d): ");
        char choice = scanner.next().charAt(0);
        scanner.nextLine();

        if (choice == 'c') {
            System.out.print("Enter the text to compress: ");
            String text = scanner.nextLine();

            if (text.isEmpty()) {
                System.out.println("Error: No text provided for compression.");
                return;
            }

            System.out.print("Enter the compressed file name (without extension): ");
            String compressedFileName = scanner.nextLine();

            HuffmanCompressor compressor = new HuffmanCompressor();
            compressor.compress(text, compressedFileName);
        } else if (choice == 'd') {
            System.out.print("Enter the compressed file name (without extension): ");
            String compressedFileName = scanner.nextLine();

            if (compressedFileName.isEmpty()) {
                System.out.println("Error: No file name provided.");
                return;
            }

            HuffmanDecompressor decompressor = new HuffmanDecompressor();
            decompressor.decompress(compressedFileName);
        } else {
            System.out.println("Invalid choice! Choose between 'c' and 'd'.");
        }

        scanner.close();
    }
}
