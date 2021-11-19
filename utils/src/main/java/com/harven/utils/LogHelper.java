package com.harven.utils;

import java.util.Locale;

/**
 * Logger 日志帮助类<br/>
 *
 * @author harven
 * @date 2021/11/17 16:54
 */
public class LogHelper {
    /**
     * 获取调用栈信息
     *
     * @param indent 缩进
     * @return 方法的调用栈信息
     */
    public static String track(String indent) {
        StringBuilder appender = new StringBuilder();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement invokeElement : stackTrace) {
            appendElement(appender, indent, invokeElement);
        }
        return appender.toString();
    }

    /**
     * 方法的调用栈信息
     *
     * @param indent 缩进
     * @param line   需要追溯的行数
     * @return 方法调用栈信息
     */
    public static String track(int line, String indent) {
        StringBuilder appender = new StringBuilder();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0, counter = -1; i < stackTrace.length && counter < line; i++) {
            StackTraceElement invokeElement = stackTrace[i];
            if (LogHelper.class.getName().equals(invokeElement.getClassName())) {
                counter = 0;
            } else if (counter >= 0) {
                appendElement(appender, indent, invokeElement);
                counter++;
            }
        }
        return appender.toString();
    }

    private static void appendElement(StringBuilder sb, String indent, StackTraceElement invokeElement) {
        String fullClassName = invokeElement.getClassName();
        /*int index = fullClassName.lastIndexOf(".") + 1;
        String className;
        if (index < fullClassName.length()) {
            className = fullClassName.substring(index);
        } else {
            className = fullClassName;
        }*/
        String methodName = invokeElement.getMethodName();
        int lineNumber = invokeElement.getLineNumber();
        if (sb.length() > 0) {
            if (sb.charAt(sb.length() - 1) != '\n') {
                sb.append("\r\n");
            }
        }
        sb.append(indent);
        sb.append(String.format(Locale.US, "%s.%s():%s", fullClassName, methodName, lineNumber));
    }

    /**
     * 获取当前方法信息
     *
     * @return 全类名.方法名():行数
     */
    public static String currentMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = stackTrace.length - 1; i >= 0; i--) {
            StackTraceElement invokeElement = stackTrace[i];
            if (LogHelper.class.getName().equals(invokeElement.getClassName())) {
                i++;
                if (i < stackTrace.length - 1) {
                    invokeElement = stackTrace[i];
                    String fullClassName = invokeElement.getClassName();
                    /*int index = fullClassName.lastIndexOf(".") + 1;
                    String className;
                    if (index < fullClassName.length()) {
                        className = fullClassName.substring(index);
                    } else {
                        className = fullClassName;
                    }*/
                    String methodName = invokeElement.getMethodName();
                    int lineNumber = invokeElement.getLineNumber();
                    return String.format(Locale.US, "%s.%s():%s",
                            fullClassName,
                            methodName,
                            lineNumber);
                }
                break;
            }
        }
        return String.format("%s.currentMethod():90", LogHelper.class.getName());
    }

}
