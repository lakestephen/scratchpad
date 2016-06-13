package com.concurrentperformance.xor;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String KEY = "6ae51a0e-2051-4fca-a9d3-402e74d42e8d";


    @Test
    public void roundTripXor() throws IOException {
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
    public void roundTripComplete() {
        try {
            Path root = Paths.get("src/test/resources/forCompress");
            Path inter = Paths.get("build/inter.txt");
            log.info(inter.toAbsolutePath().normalize().toString());
            pack(root, inter);

            Path output = Paths.get("build/output.txt");
            unpack(inter, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pack(final Path folder, final Path interPath) throws IOException {

        FileOutputStream fos = new FileOutputStream(interPath.toFile());
        OutputStream b64os = Base64.getEncoder().wrap(fos);
        XorOutputStream xoros = new XorOutputStream(b64os, KEY);
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
        InputStream b64is = Base64.getDecoder().wrap(fis);
        XorInputStream xoris = new XorInputStream(b64is, KEY);
        FileOutputStream fos = new FileOutputStream(zipPath.toFile());
        IOUtils.copy(xoris, fos);
    }
}
