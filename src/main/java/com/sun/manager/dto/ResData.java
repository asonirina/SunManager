package com.sun.manager.dto;

import com.google.common.base.Joiner;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * User: iason
 */
public class ResData {

    private String res;
    private long count = 0L;

    public ResData(String res) {
        this.res = res;
        generateCount();
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
        generateCount();
    }

    public long getCount() {
        return count;
    }

    private void generateCount() {
        try {
            count = new Scanner(res).useDelimiter("[^\\d]+").nextInt();
        } catch (NoSuchElementException ex) {

        }
    }

}
