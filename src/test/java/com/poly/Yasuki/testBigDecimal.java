package com.poly.Yasuki;

import javax.swing.text.NumberFormatter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class testBigDecimal {
    public static void main(String[] args) {
        BigDecimal number = new BigDecimal("344.923789");
        BigDecimal roundedNumber = number.setScale(0, RoundingMode.HALF_EVEN);
//        BigDecimal result =  roundedNumber.setScale(3, RoundingMode.HALF_EVEN);
        System.out.println(roundedNumber);

    }

}
