package com.sun.manager.dto;

import com.google.common.base.Joiner;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * User: iason
 */
public class ResData {

    private String res;
    private int count = 0;

    public ResData(String res) {
        this.res = res;
        try {
            count = new Scanner(res).useDelimiter("[^\\d]+").nextInt();
        } catch (NoSuchElementException ex) {

        }
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public int getCount() {
        return count;
    }

}
