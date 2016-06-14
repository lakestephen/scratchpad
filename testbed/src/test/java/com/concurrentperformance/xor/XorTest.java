package com.concurrentperformance.xor;

import com.google.common.base.Splitter;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Stephen on 10/06/2016.
 */
public class XorTest {

    private final static Logger log = LoggerFactory.getLogger(XorTest.class);

    private static final String KEY = "6ae51a0e-2051-4fca-a9d3-402e74d42e8d";


    @Test
    public void roundTripXorByte() throws IOException {
        String inString = "s";
        byte[] in = inString.getBytes();
        ByteArrayInputStream bis = new ByteArrayInputStream(in);
        XorInputStream xis = new XorInputStream(bis, KEY);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XorOutputStream xos = new XorOutputStream(bos, KEY);
        IOUtils.copy(xis, xos);

        byte[] out = bos.toByteArray();
        log.info(new String(out));

        assertArrayEquals(in, out);
    }

    @Test
    public void roundTripXorFile() {
        try {
            Path root = Paths.get("src/test/resources/forCompress/test.txt");
            Path inter = Paths.get("build/inter.txt");
            pack(root, inter);

            Path output = Paths.get("build/output.txt");
            unpack(inter, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void roundTripXorLog() {
        try {
            Path root = Paths.get("src.zip");
            log(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(final Path in) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStream b64os = Base64.getEncoder().wrap(bos);
        XorOutputStream xoros = new XorOutputStream(b64os, KEY);
        FileInputStream fis = new FileInputStream(in.toFile());
        IOUtils.copy(fis, xoros);
        fis.close();
        xoros.close();
        byte[] bytes = bos.toByteArray();


        String s = new String(bytes);
        Iterable<String> split = Splitter.fixedLength(100000).split(s);

        for (String s1 : split) {
            log.info(s1);
        }


    }

    public static void pack(final Path in, final Path out) throws IOException {
        FileOutputStream fos = new FileOutputStream(out.toFile());
        OutputStream b64os = Base64.getEncoder().wrap(fos);
        XorOutputStream xoros = new XorOutputStream(b64os, KEY);
        FileInputStream fis = new FileInputStream(in.toFile());
        IOUtils.copy(fis, xoros);
        fis.close();
        xoros.close();
    }

    public static void unpack(final Path in, final Path out) throws IOException {
        FileInputStream fis = new FileInputStream(in.toFile());
        InputStream b64is = Base64.getDecoder().wrap(fis);
        XorInputStream xoris = new XorInputStream(b64is, KEY);
        FileOutputStream fos = new FileOutputStream(out.toFile());
        IOUtils.copy(xoris, fos);
        fis.close();
        xoris.close();
    }
}
