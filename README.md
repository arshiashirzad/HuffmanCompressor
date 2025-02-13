# Huffman Compression & Decompression in Java

A fully implemented Huffman Encoding and Decoding project in Java for efficient file compression and decompression. This program compresses text files using Huffman coding, reducing file sizes by storing data as compact byte sequences rather than plain binary strings. It also reconstructs the original text during decompression using stored Huffman codes.

## 📌 Features:
- ✅ **Compression:** Converts input text into a compact byte stream using Huffman encoding.  
- ✅ **Decompression:** Restores the original text from the compressed byte stream.  
- ✅ **Efficient Storage:** Stores compressed data in bytes (not just 0s and 1s) for smaller file sizes.  
- ✅ **Codebook Generation:** Saves Huffman codes in a separate file for accurate decompression.  
- ✅ **Reusable Classes:** Structured with clear class separation (`HuffmanTree`, `Compressor`, `Decompressor`, `FileHandler`).  

## 🛠️ Tech Stack:
- **Language:** Java  
- **Concept:** Huffman Coding Algorithm  
- **File Handling:** Read/Write Binary Files  
- **Data Structures:** Priority Queues, HashMaps, BitSets  

---

## 🚀 How It Works:
1. **Compress:**  
   - Reads user input  
   - Builds Huffman codes  
   - Stores compressed data in `.bin` and codes in `.txt`  
   
2. **Decompress:**  
   - Reads the compressed `.bin`  
   - Reads the Huffman codes from `.txt`  
   - Reconstructs and saves the original text  

---

## 📝 Usage:

### 1️⃣ **Compile the project:**
```bash
javac Main.java
