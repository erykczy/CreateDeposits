package com.thecode.createdeposits;

public class Mathf {
    public static double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    public static double clamp01(double value) {
        return clamp(value, 0, 1);
    }
}
