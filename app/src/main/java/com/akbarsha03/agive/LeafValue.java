package com.akbarsha03.agive;

import android.support.annotation.IntRange;

/**
 * Created by: akbarsha03 on 1/11/17.
 */

@SuppressWarnings("WeakerAccess")
public class LeafValue {

    String color;

    private int percentage;

    public LeafValue(String color, @IntRange(from = 0, to = 100) int percentage) {
        this.color = color;
        this.percentage = percentage;
    }

    public String getColor() {
        return color;
    }

    public int getPercentage() {
        return percentage;
    }
}
