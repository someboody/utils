package com.harven.utils;

/**
 * ByteUtils byte工具类,用于 <br>
 * byte,short,int,long,float,double,float,boolean基本数据与byte数组的相互转化<br>
 * boolean false对应0, true对应非0
 *
 * @author harven
 * @date 2018/11/20 11:04
 */
public class ByteUtils {
    private ByteUtils() {
    }

    /**
     * 将输入值转为 byte []
     *
     * @param value 输入值
     * @return byte []
     */
    public static byte[] bytesFrom(byte value) {
        return new byte[]{value};
    }

    /**
     * 将输入值转为 byte []
     *
     * @param value 输入值
     * @return byte []
     */
    public static byte[] bytesFrom(short value) {
        byte[] buf = new byte[2];
        buf[0] = (byte) (value >>> 8);
        buf[1] = (byte) value;
        return buf;
    }

    /**
     * 将输入值转为 byte []
     *
     * @param value 输入值
     * @return byte []
     */
    public static byte[] bytesFrom(int value) {
        byte[] buf = new byte[4];
        buf[0] = (byte) (value >>> 24);
        buf[1] = (byte) (value >>> 16);
        buf[2] = (byte) (value >>> 8);
        buf[3] = (byte) (value & 0xFF);
        return buf;
    }

    /**
     * 将输入值转为 byte []
     *
     * @param value 输入值
     * @return byte []
     */
    public static byte[] bytesFrom(long value) {
        byte[] buf = new byte[8];
        buf[0] = (byte) (value >>> 56);
        buf[1] = (byte) (value >>> 48);
        buf[2] = (byte) (value >>> 40);
        buf[3] = (byte) (value >>> 32);
        buf[4] = (byte) (value >>> 24);
        buf[5] = (byte) (value >>> 16);
        buf[6] = (byte) (value >>> 8);
        buf[7] = (byte) (value & 0xFF);
        return buf;
    }

    /**
     * 将输入值转为 byte []
     *
     * @param value 输入值
     * @return byte []
     */
    public static byte[] bytesFrom(float value) {
        int i = Float.floatToIntBits(value);
        return bytesFrom(i);
    }

    /**
     * 将输入值转为 byte []
     *
     * @param value 输入值
     * @return byte []
     */
    public static byte[] bytesFrom(double value) {
        long l = Double.doubleToLongBits(value);
        return bytesFrom(l);
    }

    /**
     * 将输入值转为 byte []
     *
     * @param value 输入值
     * @return byte []
     */
    public static byte[] bytesFrom(boolean value) {
        if (value)
            return new byte[]{1};
        else
            return new byte[]{0};
    }

    /**
     * 将输入值转为 byte []
     *
     * @param value 输入值
     * @return byte []
     */
    public static byte[] bytesFrom(String value) {
        if (value == null)
            return new byte[]{};
        else
            return value.getBytes();
    }

    /**
     * 从 byte[] 中获取两个 byte 转为 shot 值
     *
     * @param bytes  输入byte[]
     * @param offset 偏移量
     * @return shot 值
     */
    public static short toShort(byte[] bytes, int offset) {
        if (bytes == null || bytes.length == 0 || offset >= bytes.length) {
            return 0;
        }
        if (bytes.length - offset == 1) {
            return bytes[offset];
        }
        return (short) (((bytes[offset++] & 0xFF) << 8) | (bytes[offset] & 0xFF));
    }

    /**
     * 从 byte[] 中获取 4 byte 转为 int 值
     *
     * @param bytes  输入 byte[]
     * @param offset 偏移量
     * @return int 值
     */
    public static int toInt(byte[] bytes, int offset) {
        if (bytes == null || bytes.length == 0 || offset >= bytes.length) {
            return 0;
        }
        int result = 0;
        int len = Math.min(bytes.length - offset, 4);
        for (int i = len - 1; i >= 0; i--) {
            result = result | ((bytes[offset + i] & 0xFF) << (8 * (len - i - 1)));
        }
        return result;
    }

    /**
     * 从 byte[] 中获取 8 byte 转为 long 值
     *
     * @param bytes  输入byte[]
     * @param offset 偏移量
     * @return long 值
     */
    public static long toLong(byte[] bytes, int offset) {
        if (bytes == null || bytes.length == 0 || offset >= bytes.length) {
            return 0;
        }
        int len = Math.min(bytes.length - offset, 8);
        long result = 0;
        for (int i = len - 1; i >= 0; i--) {
            result = result | ((bytes[offset + i] & 0xFFL) << (8 * (len - i - 1)));
        }
        return result;
    }

    /**
     * 从 byte[] 中获取 4 byte 转为 float 值
     *
     * @param bytes  输入byte[]
     * @param offset 偏移量
     * @return float 值
     */
    public static float toFloat(byte[] bytes, int offset) {
        if (bytes == null || bytes.length == 0 || offset >= bytes.length) {
            return 0;
        }
        int intValue = toInt(bytes, offset);
        return Float.intBitsToFloat(intValue);
    }

    /**
     * 从 byte[] 中获取 8 byte 转为 double 值
     *
     * @param bytes  输入byte[]
     * @param offset 偏移量
     * @return double 值
     */
    public static double toDouble(byte[] bytes, int offset) {
        if (bytes == null || bytes.length == 0 || offset >= bytes.length) {
            return 0;
        }
        long longValue = toLong(bytes, offset);
        return Double.longBitsToDouble(longValue);
    }

    /**
     * 从 byte[] 中获取 1 byte 转为 boolean 值
     *
     * @param bytes  输入byte[]
     * @param offset 偏移量
     * @return bytes[offset] 值不为 0 时返回 true, 否则 返回 false
     */
    public static boolean toBoolean(byte[] bytes, int offset) {
        if (bytes == null || bytes.length == 0 || offset >= bytes.length) {
            return false;
        }
        return bytes[offset] != 0;
    }

    /**
     * 获取 byte 值的二进制(补码)字符串,格式:xxxx xxxx  xxxx xxxx
     *
     * @param b byte值
     * @return 二进制(补码)字符串
     */
    public static String toBinaryString(byte b) {
        String bs = Integer.toString(b & 0xFF, 2);
        StringBuilder sb = new StringBuilder();
        int length = bs.length();
        for (int i = 0; i < 8; i++) {
            int index = i + length - 8;
            if (i == 4) {
                sb.append(' ');
            }
            if (index < 0) {
                sb.append('0');
            } else {
                sb.append(bs.charAt(index));
            }
        }
        return sb.toString();
    }

    /**
     * 获取 short 值的二进制(补码)字符串,格式:xxxx xxxx  xxxx xxxx
     *
     * @param b short 值
     * @return 二进制(补码)字符串
     */
    public static String toBinaryString(short b) {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = bytesFrom(b);
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                sb.append("  ");
            }
            sb.append(toBinaryString(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * 获取 int 值的二进制(补码)字符串,格式:xxxx xxxx  xxxx xxxx
     *
     * @param b int值
     * @return 二进制(补码)字符串
     */
    public static String toBinaryString(int b) {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = bytesFrom(b);
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                sb.append("  ");
            }
            sb.append(toBinaryString(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * 获取 long 值的二进制(补码)字符串,格式:xxxx xxxx  xxxx xxxx
     *
     * @param b long值
     * @return 二进制(补码)字符串
     */
    public static String toBinaryString(long b) {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = bytesFrom(b);
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                sb.append("  ");
            }
            sb.append(toBinaryString(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * 获取 float 值的二进制(补码)字符串,格式:xxxx xxxx  xxxx xxxx
     *
     * @param b float值
     * @return 二进制(补码)字符串
     */
    public static String toBinaryString(float b) {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = bytesFrom(b);
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                sb.append("  ");
            }
            sb.append(toBinaryString(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * 获取 double 值的二进制(补码)字符串,格式:xxxx xxxx  xxxx xxxx
     *
     * @param b double值
     * @return 二进制(补码)字符串
     */
    public static String toBinaryString(double b) {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = bytesFrom(b);
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                sb.append("  ");
            }
            sb.append(toBinaryString(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * 获取 byte[] 值的二进制(补码)字符串,格式:xxxx xxxx  xxxx xxxx
     *
     * @param bytes byte[]值
     * @return 二进制(补码)字符串
     */
    public static String toBinaryString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(toBinaryString(bytes[i]));
        }
        return sb.toString();
    }
}
