package dev.mrodriguezul.datatypes;

public class Primitives {
    public static void main(String[] args) {
        //showPrimitives1();
        //showPrimitives2();
        //showPrimitives3();
        //showPrimitives4();
        //showPrimitives5();
        showPrimitives6();
    }

    static void showPrimitives1(){
        // Primitives
        byte byteVar = 127;
        short shortVar = 32767;
        int intVar = 2147483647;
        long longVar = 9223372036854775807L;
        float floatVar = 3.4028235E38f;
        double doubleVar = 1.7976931348623157E308;
        char charVar = 'A';
        boolean booleanVar = true;

        // Print
        System.out.println("byteVar: " + byteVar);
        System.out.println("shortVar: " + shortVar);
        System.out.println("intVar: " + intVar);
        System.out.println("longVar: " + longVar);
        System.out.println("floatVar: " + floatVar);
        System.out.println("doubleVar: " + doubleVar);
        System.out.println("charVar: " + charVar);
        System.out.println("booleanVar: " + booleanVar);
    }

    static void showPrimitives2(){
        //using suffix for long, float and double
        long l1 = 10l, l2 = 10L;
        float f1 = 10.0f, f2 = 10.0F;
        double d1 = 10.0d, d2 = 10.0D;

        System.out.println("l1: " + l1);
        System.out.println("l2: " + l2);
        System.out.println("f1: " + f1);
        System.out.println("f2: " + f2);
        System.out.println("d1: " + d1);
        System.out.println("d2: " + d2);

    }

    static void showPrimitives3(){
        //using underscore for better readability
        //also we can use underscore in the middle of the number
        // (not at the beginning or at the end or before the suffix or the base (prefix))
        //we use the same syntax with binary, octal and hexadecimal literals
        int i1 = 1_000_000;
        long l1 = 1_000_000_000_000L;
        float f1 = 3.1415_9265f;
        double d1 = 3.1415_9265;

        System.out.println("i1: " + i1);
        System.out.println("l1: " + l1);
        System.out.println("f1: " + f1);
        System.out.println("d1: " + d1);
    }

    static void showPrimitives4(){
        //using binary, octal and hexadecimal literals
        int i1 = 0b1010; // 0b or 0B for binary literals (base 2) (0-1) value = 10
        int i2 = 012; // 0 for octal literals (base 8) (0-7) value = 10
        int i3 = 0xA; // 0x or 0X for hexadecimal literals (base 16) (0-9, A-F) value = 10

        System.out.println("i1: " + i1);
        System.out.println("i2: " + i2);
        System.out.println("i3: " + i3);
    }

    static void showPrimitives5(){
        //asaigning a character into an int, double, byte and short

        byte b = 99;
        char c = 99;
        short s = 99;
        int i = 99;
        double d = 99;

        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println("s: " + s);
        System.out.println("i: " + i);
        System.out.println("d: " + d);

        System.out.println("the opposite");

        byte b2 = 'c';
        char c2 = 'c';
        short s2 = 'c';
        int i2 = 'c';
        double d2 = 'c';

        System.out.println("b2: " + b2);
        System.out.println("c2: " + c2);
        System.out.println("s2: " + s2);
        System.out.println("i2: " + i2);
        System.out.println("d2: " + d2);


    }

    static void showPrimitives6(){
        // using unicode characters, similar to the previous example (showPrimitives5)
        byte b1 = '\u0041'; // A
        char c1 = '\u0041'; // A
        int i1 = '\u0041'; // A
        double d1 = '\u0041'; // A

        System.out.println("b1: " + b1);
        System.out.println("c1: " + c1);
        System.out.println("i1: " + i1);
        System.out.println("d1: " + d1);

    }


}
