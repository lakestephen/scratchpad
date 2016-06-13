package com.concurrentperformance.xor;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * TODO Comments
 *
 * @author Lake
 */
public class XorOutputStream extends FilterOutputStream {
    public static final int EOF = -1;

    private final byte[] key;

    private int keyIndex = 0;

    public XorOutputStream(OutputStream in, String key) {
        super(in);
        this.key = key.getBytes();
    }

    @Override
    public void write(int b) throws IOException {
        int xor = b ^ getKeyByte();
        super.write(xor);
    }

    private byte getKeyByte() {
        keyIndex++;
        if (keyIndex >= key.length) {
            keyIndex = 0;
        }
        return key[keyIndex];
    }

}