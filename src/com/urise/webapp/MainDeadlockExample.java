package com.urise.webapp;

public class MainDeadlockExample {
    public static void main(String[] args) {
        final String resource1 = "my resource 1";
        final String resource2 = "my resource 2";
        // t1 tries to lock resource1 then resource2
        Thread t1 = new Thread(() -> {
            processResources(resource1, resource2, "Thread 1: locked resource 1", "Thread 1: locked resource 2");
        });

        // t2 tries to lock resource2 then resource1
        Thread t2 = new Thread(() -> {
            processResources(resource2, resource1, "Thread 2: locked resource 2", "Thread 2: locked resource 1");
        });

        t1.start();
        t2.start();
    }

    private static void processResources(String resource1, String resource2, String s, String s2) {
        synchronized (resource1) {
            System.out.println(s);

            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }

            synchronized (resource2) {
                System.out.println(s2);
            }
        }
    }

}
