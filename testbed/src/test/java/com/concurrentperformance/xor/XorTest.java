package com.concurrentperformance.xor;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Stephen on 10/06/2016.
 */
public class XorTest {

    @Test
    public void roundTripXor() throws IOException {
        String inString = "s";
        byte[] in = inString.getBytes();
        ByteArrayInputStream bis = new ByteArrayInputStream(in);
        XorInputStream xis = new XorInputStream(bis);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XorOutputStream xos = new XorOutputStream(bos);
        IOUtils.copy(xis, xos);

        byte[] out = bos.toByteArray();
        String outStr = new String(out);

        assertArrayEquals(in, out);
    }

    @Test
    public void roundTripComplete() {
        try {
            Path root = Paths.get("C:\\Temp\\input\\doit");
            Path inter = Paths.get("C:\\Temp\\inter\\inter.txt");
            pack(root, inter);

            Path output = Paths.get("C:\\Temp\\output\\output.zip");
            unpack(inter, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pack(final Path folder, final Path interPath) throws IOException {

        FileOutputStream fos = new FileOutputStream(interPath.toFile());
//        OutputStream b64os = Base64.getEncoder().wrap(fos);
        XorOutputStream xoros = new XorOutputStream(fos);
        ZipOutputStream zos = new ZipOutputStream(xoros);
        Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                zos.putNextEntry(new ZipEntry(folder.relativize(file).toString()));
                Files.copy(file, zos);
                zos.closeEntry();
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (dir.endsWith(".git")) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                if (dir.endsWith(".gradle")) {
                    return FileVisitResult.SKIP_SUBTREE;
                }

                zos.putNextEntry(new ZipEntry(folder.relativize(dir).toString() + "/"));
                zos.closeEntry();
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void unpack(final Path interPath, final Path zipPath) throws IOException {
        FileInputStream fis = new FileInputStream(interPath.toFile());
//        InputStream b64is = Base64.getDecoder().wrap(fis);
        XorInputStream xoris = new XorInputStream(fis);
        FileOutputStream fos = new FileOutputStream(zipPath.toFile());
        IOUtils.copy(xoris, fos);
    }



    private static final String KEY = "6ae51a0e-2051-4fca-a9d3-402e74d42e8d";
    private static final byte[] secret = KEY.getBytes();

    public static class XorOutputStream extends FilterOutputStream {
        int spos = 0;

        public XorOutputStream(OutputStream out) {
            super(out);
        }

        @Override
        public void write(int b) throws IOException {
            spos++;
            if (spos >= secret.length) {
                spos = 0;
            }
            int xor = b ^ secret[spos];
            super.write(xor);
        }
    }

    public static class XorInputStream extends FilterInputStream {
        int spos = 0;

        public XorInputStream(InputStream in) {
            super(in);
        }

        @Override
        public int read() throws IOException {
            spos++;
            if (spos >= secret.length) {
                spos = 0;
            }
            int b = super.read();
            if (b== -1) {
                return b;
            }
            int xor = b ^ secret[spos];
            return xor;
        }
    }
}
