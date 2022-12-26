package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {

//        File file = new File(".\\.gitignore");
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error ", e);
//        }

        String path = "E:\\java\\Projects\\BaseJava\\basejava\\src";
        String space = "";
        recursion(path, space);

//        File file1 = new File("E:\\java\\Projects\\BaseJava\\basejava\\src\\com\\urise\\webapp");
//        String[] list1 = file1.list();
//        if (list1 != null) {
//            for (String f : list1) {
//                System.out.println(f);
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream("E:\\java\\Projects\\BaseJava\\basejava\\.gitignore")) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private static void recursion(String path, String space) {
        File dir = new File(path);
        File[] fileList = dir.listFiles();
        if (fileList != null) {
            for (File f : fileList) {
                System.out.println(space + f.getName());
                if (f.isDirectory()) {
                    recursion(path + "\\" + f.getName(), space + "  ");
                }
            }
        }
    }
}
