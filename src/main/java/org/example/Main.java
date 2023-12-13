package org.example;



public class Main {
    public static void main(String[] args) {
        Global.initScanner();
        new App().run();
        Global.exitScanner();
    }
}