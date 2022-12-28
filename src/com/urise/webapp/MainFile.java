package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        String path = "E:\\java\\Projects\\BaseJava\\basejava\\src";
        String spaces = "";
        recursion(path, spaces);
    }

    private static void recursion(String path, String spaces) {
        File dir = new File(path);
        File[] fileList = dir.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                System.out.println(spaces + file.getName());
                if (file.isDirectory()) {
                    recursion(path + "\\" + file.getName(), spaces + "  ");
                }
            }
        }
    }
}
