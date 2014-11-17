package com.sun.manager.dto;

import com.google.common.base.Joiner;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * User: iason
 */
public class ResData {

    private String res;
    private long count = 0L;
    private double l2 = 0.;

    public ResData(String res) {
        this.res = res;
        generateCount();
        generateL2();
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
        generateCount();
        generateL2();
    }

    public long getCount() {
        return count;
    }

    public double getL2() {
        return l2;
    }

    private void generateCount() {
        try {
            count = new Scanner(res).useDelimiter("[^\\d]+").nextInt();
        } catch (NoSuchElementException ex) {

        }
    }

    private void generateL2() {
        try {
            if (res.startsWith("L2=")) {

                NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
                double d = nf.parse(res.substring(4)).doubleValue();
                l2 = Math.floor(d*100) / 100;
            }
        } catch (ParseException ex) {

        }
    }
}
