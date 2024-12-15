package me.janeldq.algorithms.assignments.burrows;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * Move-to-front encoding and decoding
 *
 * The main idea of move-to-front encoding is to maintain an ordered
 * sequence of the characters in the alphabet, and repeatedly read a
 * character from the input message, print out the position in which
 * that character appears, and move that character to the front of
 * the sequence.
 *
 */
public class MoveToFront {

    public static void encode() {
        int R = 256;
        int[] ascii = new int[R];
        for (char i = 0; i < R; i++) {
            ascii[i] = i;
        }
        while(!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            char idx = 0;
            for (char i = 0; i < R; i++) {
                if (ascii[i] == c) {
                    idx = i;
                    BinaryStdOut.write(i);
                    break;
                }
            }
            for (char i = idx; i > 0; i--) {
                ascii[i] = ascii[i - 1];
            }
            ascii[0] = c;
        }
        BinaryStdOut.close();
    }

    public static void decode() {
        int R = 256;
        char[] ascii = new char[R];
        for (char i = 0; i < R; i++) {
            ascii[i] = i;
        }
        while(!BinaryStdIn.isEmpty()) {
            char index = BinaryStdIn.readChar();
            char c = ascii[index];
            BinaryStdOut.write(c);
            for (char i = index; i > 0; i--) {
                ascii[i] = ascii[i - 1];
            }
            ascii[0] = c;
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        if (args[0].equals("+")) decode();
    }
}