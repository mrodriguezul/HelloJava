package dev.mrodriguezul.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Strings {

    static String s;
    static int uno;
    public static void main(String[] args) {
        //testStrings1();
        //testStrings2();
        //testString3();
        //testString4();
        //testString5();
        //testString6();
        //testString7();
        testStrings8();
    }
    public static void testStrings1(){
        String value1 = "miguel";
        String value2 = "miguel";

        if(value1 == value2)System.out.println("value1 == value2");
        else System.out.println("value1 != value2");

        String value3 = new String("miguel");
        String value4 = new String("miguel");
        //new operator creates a new object in the heap memory
        if(value3 == value4)System.out.println("value3 == value4");
        else System.out.println("value3 != value4");

        //intern method returns the canonical representation of the string object
        if(value3.intern() == value4.intern())System.out.println("value3 == value4");
        else System.out.println("value3 != value4");
    }

    public static void testStrings2(){
        //Creating string from bytes, chars and ints

        var arrayLenght = 19;
        var arrayBytes = new byte[arrayLenght];
        var arrayChars = new char[arrayLenght];
        var arrayInts = new int[arrayLenght];
        int i = 0;
        for (int c = 64; c < arrayLenght + 64; c++, i++) {
            arrayBytes[i] = (byte)c;
            arrayChars[i] = (char)c;
            arrayInts[i] = c;
        }

        String strFromBytes = new String(arrayBytes);
        String strFromChars = new String(arrayChars);
        String strFromInts = new String(arrayInts, 0, arrayInts.length);

        System.out.println("strFromBytes: " + strFromBytes);
        System.out.println("strFromChars: " + strFromChars);
        System.out.println("strFromInts: " + strFromInts);

    }

    public static void testString3(){
        String str1 = "Hello";
        String str2 = "Hello";

        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println("str1.equals(str2): " + str1.equals(str2));
        System.out.println("str1.equalsIgnoreCase(str2): " + str1.equalsIgnoreCase(str2));
        System.out.println("str1.compareTo(str2): " + str1.compareTo(str2));
        System.out.println("str1.compareToIgnoreCase(str2): " + str1.compareToIgnoreCase(str2));
        System.out.println("str1.contentEquals(str2): " + str1.contentEquals(str2));

        String str3 = new String(new char[]{'H','e','l','l','o'});
        String str4 = "Hello";

        System.out.println("str3 == str4: " + (str3 == str4));
        System.out.println("str3.equals(str4): " + str3.equals(str4));
        System.out.println("str3.equalsIgnoreCase(str4): " + str3.equalsIgnoreCase(str4));
        System.out.println("str3.compareTo(str4): " + str3.compareTo(str4));
        System.out.println("str3.compareToIgnoreCase(str4): " + str3.compareToIgnoreCase(str4));
        System.out.println("str3.contentEquals(str4): " + str3.contentEquals(str4));


    }

    public static void testString4(){
        String arrayStrings[] = new String[]{"Hello", "this", "is", "my", "list"};
        ArrayList<String> listStrings = new ArrayList<>(Arrays.asList(arrayStrings));
        StringJoiner joiner = new StringJoiner(" ");

        String str0 = String.join(" ", "Hello", "this", "is", "my", "list");//we can use varargs
        String str1 = String.join(" ", listStrings);
        String str2 = String.join(" ", arrayStrings);
        for(String str : arrayStrings){
            joiner.add(str);
        }
        String str3 = joiner.toString();

        System.out.println("str0: " + str0);
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
        System.out.println("str3: " + str3);
    }

    public static void testString5(){
        //test split method
        String str = "Hello   this    is a  test";
        String[] arrayStrings = str.split("\\s+");//split by one or more spaces
        for(String s : arrayStrings){
            System.out.println(s);
        }
    }

    public static void testString6(){
        String str = "anitalavalatina";

        String str1 = str.replaceAll("a(t|l)","xx");

        System.out.println("str.replaceAll: "+str1);
    }

    public static void testString7(){
        String str = "anitalavalatina";

        String str1 = str.substring(1,4);
        System.out.println("str.substring: "+str1);

        String str2 = str1.repeat(10);
        System.out.println("str.repeat: "+str2);
        String str3 = new String(str.toCharArray(), 1, 4);//this declaration create a string with 4 characters
        //different from substring method
        System.out.println("new String: "+str3);

        //testing static null variable
        String strTest = Strings.s+"";//transforming null to string
        System.out.println(strTest);//this prints "null"
        strTest.concat("Hello");
        System.out.println(strTest.concat("Hello"));
        System.out.println(Strings.uno);
    }
    public static void testStrings8(){
        //using stringbuilder
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        System.out.println(sb.length());
        System.out.println(sb.capacity());//capacity start with 16
        sb.append(" world! this is the new world MDF! XD waiting for the new world ");
        System.out.println(sb.length());
        System.out.println(sb.capacity());//capacity is new number
        sb.appendCodePoint(64);
        System.out.println(sb);

    }
}

