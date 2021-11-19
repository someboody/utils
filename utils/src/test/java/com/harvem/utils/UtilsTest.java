package com.harvem.utils;

import com.harven.utils.ByteUtils;
import com.harven.utils.LogHelper;

import org.junit.Test;

/**
 * UtilsTest
 *
 * @author harven
 * @date 2021/11/17 17:15
 */
public class UtilsTest {
    @Test
    public void test() {
        System.out.println("-------------------------");
        System.out.println(LogHelper.track("    "));
        System.out.println("-------------------------");
        System.out.println(LogHelper.track(4, "    "));
        System.out.println("-------------------------");
        System.out.println(LogHelper.currentMethod());
        System.out.println("-------------------------");
    }

    @Test
    public void bytesUtils() {
        System.out.printf("%-6s: %s\r\n", "12", ByteUtils.toBinaryString((byte) 12));
        System.out.printf("%-6s: %s\r\n", "12", ByteUtils.toBinaryString((short) 12));
        System.out.printf("%-6s: %s\r\n", "-12", ByteUtils.toBinaryString(-12));
        System.out.printf("%-6s: %s\r\n", "12", ByteUtils.toBinaryString(12));
        System.out.printf("%-6s: %s\r\n", "-12L", ByteUtils.toBinaryString(-12L));
        System.out.printf("%-6s: %s\r\n", "12L", ByteUtils.toBinaryString(12L));
        System.out.printf("%-6s: %s\r\n", "-12F", ByteUtils.toBinaryString(-12F));
        System.out.printf("%-6s: %s\r\n", "12F", ByteUtils.toBinaryString(12F));
        System.out.printf("%-6s: %s\r\n", "12.0", ByteUtils.toBinaryString(12.0));
        System.out.printf("%-6s: %s\r\n", "-12.0", ByteUtils.toBinaryString(-12.0));
    }
}
