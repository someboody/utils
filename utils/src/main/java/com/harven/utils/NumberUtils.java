package com.harven.utils;

/**
 * NumberUtils 转数字的一些方法
 *
 * @author harven
 * @date 2021/11/17 17:40
 */
public class NumberUtils {
    /**
     * {@link String} 转整型数字
     *
     * @param number   数字字符串
     * @param defValue 转失败时返回的默认值
     * @return 字符串转换后的数值 或 转换失败时为给定的默认值
     */
    public static int toInt(String number, int defValue) {
        try {
            return Integer.parseInt(number);
        } catch (Exception ignored) {
        }
        return defValue;
    }

    /**
     * {@link String} 转整型数字
     *
     * @param number   数字字符串
     * @param defValue 转失败时返回的默认值
     * @return 字符串转换后的数值 或 转换失败时为给定的默认值
     */
    public static long toLong(String number, long defValue) {
        try {
            return Long.parseLong(number);
        } catch (Exception ignored) {
        }
        return defValue;
    }
}
