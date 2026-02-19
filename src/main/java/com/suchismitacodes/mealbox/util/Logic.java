package com.suchismitacodes.mealbox.util;

public final class Logic {

    private Logic() {
        // utility class — no instantiation
    }

    public static double countTotal(double price, int quantity) {
        return price * quantity;
    }
}
