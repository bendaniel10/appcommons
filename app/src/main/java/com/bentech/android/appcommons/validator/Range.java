package com.bentech.android.appcommons.validator;

/**
 * Created by Daniel on 9/11/2015.
 */
public class Range {
    private int high;
    private int low;

    public Range(int high, int low) {
        this.high = high;
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", low, high);
    }
}
