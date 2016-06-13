package com.concurrentperformance.xor;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO Comments
 *
 * @author Lake
 */
public class XorInputStream extends FilterInputStream {
    public static final int EOF = -1;

    private final byte[] key;

    private int keyIndex = 0;

    public XorInputStream(InputStream in, String key) {
        super(in);
        this.key = key.getBytes();
    }


    @Override
    public int read(byte b[]) throws IOException {
        int nextByte = super.read();
        if (nextByte == EOF) {
            return EOF;
        }
        b[0] = (byte)(nextByte ^ getKeyByte());
        return 1;
    }

    private byte getKeyByte() {
        keyIndex++;
        if (keyIndex >= key.length) {
            keyIndex = 0;
        }
        return key[keyIndex];
    }
}