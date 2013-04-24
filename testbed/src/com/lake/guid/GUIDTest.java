package com.lake.guid;

import com.lake.TestArray;
import org.junit.Test;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: stephen
 * Date: 23/04/13
 * Time: 11:53
 * To change this template use File | Settings | File Templates.
 */
public class GUIDTest {

    @Test
    public void testGUID() {
        for (int i=0;i<100;i++) {
            System.out.println(UUID.randomUUID().toString());
        }
    }
}
