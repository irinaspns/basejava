package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
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

        File dir = new File("C:\\swo\\basejava");
        printDirectoryDeeply(dir, 0);
    }

    public static void printDirectoryDeeply(File dir, int t) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(getTabs(t + 1) + "File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(getTabs(t + 1) + "Directory: " + file.getName());
                    printDirectoryDeeply(file, t + 1);
                }
            }
        }
    }

    private static String getTabs(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }
}
