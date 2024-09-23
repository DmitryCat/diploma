package com.ads_online.diploma.utils;
public class MethodLog {
    public static String getMethodName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return stackTraceElements[2].getMethodName();
    }
}