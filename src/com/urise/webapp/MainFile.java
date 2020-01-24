package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        System.out.println("test");
//        String filePath = ".\\.gitignore";

//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }

//        File dir = new File("./src/com/urise/webapp");
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        File dir = new File("C:\\Workspaces\\IDEA-IZO_BASE\\basejava");
        printDirectoryDeeply(dir, "");
    }

    public static void printDirectoryDeeply(File dir, String t) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(t + "\t" + "File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(t + "\t" + "Directory: " + file.getName());
                    printDirectoryDeeply(file, t + "\t");
                }
            }
        }
    }

}
