package de.firemage.autograder.core.check_tests.AvoidShadowing.code;

public class Test {
    int x;
    static int y;
    private int z;

    public static void main(String[] args) {
        int x = 4; // Not Ok
        int y = 5; // Not Ok
        final int z = 5; // Not Ok
    }
}

class A {
    protected int a;
    int x;
    static int y;
    private int z;
}

class B extends A {
    private final int b;
    private final int c;

    public B(int b, int c) { // Ok
        this.b = b;
        this.c = c;
    }

    private void foo2(int b) {} // Not Ok

    private void foo() {
        int a = 3; // Not Ok
        int x = 4; // Not Ok
        int y = 5; // Not Ok
        final int z = 5; // Ok
    }
}

class C extends A {
    protected int a; // Not Ok
    int x; // Not Ok
    static int y; // Not Ok
    private int z; // Ok
}
