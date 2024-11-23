package dev.mrodriguezul.datatypes;

public class Casting {
    public static void main(String[] args) {
        testCasting1();

        /*int i = -32769;
        short s = (short)i;
        System.out.println("s: " + s);//32767
        */
    }
    public static void testCasting1(){
        //Considerate the following ranges for the primitive data types, if the value is out of the range, it will throw a compilation error
        //If you want to cast a value to a smaller data type, you must use a cast operator
        //If you want to cast a value to a larger data type, you don't need to use a cast operator
        //byte -> short -> int -> long -> float -> double
        //char -> int -> long -> float -> double
        //if we cast out of the range, the value will be truncated or wrapped around getting different values

        System.out.println("Byte: < "+ Byte.MIN_VALUE+ " : "+ Byte.MAX_VALUE+" >");//-128 : 127
        System.out.println("Char: < "+ (int)Character.MIN_VALUE+ " : "+ (int)Character.MAX_VALUE+" >");//0 : 65535
        System.out.println("Short: < "+ Short.MIN_VALUE+ " : "+ Short.MAX_VALUE+" >");//-32768 : 32767
        System.out.println("Int: < "+ Integer.MIN_VALUE+ " : "+ Integer.MAX_VALUE+" >");//-2147483648 : 2147483647
        System.out.println("Long: < "+ Long.MIN_VALUE+ " : "+ Long.MAX_VALUE+" >");//-9223372036854775808 : 9223372036854775807
        System.out.println("Float: < "+ Float.MIN_VALUE+ " : "+ Float.MAX_VALUE+" >");//1.4E-45 : 3.4028235E38
        System.out.println("Double: < "+ Double.MIN_VALUE+ " : "+ Double.MAX_VALUE+" >");//4.9E-324 : 1.7976931348623157E308
    }


    public static void testCasting2(){
        int minValue = Short.MIN_VALUE;//-32768
        int maxValue = Short.MAX_VALUE;//32767

        int loopIterations = 0;
        short s2;

        System.out.println("minValue: " + minValue);
        System.out.println("maxValue: " + maxValue);

        for (int i = minValue; i<= maxValue; i++){
             s2 = (short)i;
            if (i == minValue) System.out.println("min Short Value: " + s2);
            if (i == maxValue) System.out.println("max Short Value: " + s2);

            if(s2 == Short.MAX_VALUE) System.out.println("Max Value reached at iteration: " + (loopIterations+1));
            loopIterations++;
        }
        System.out.println("Loop Iterations: " + loopIterations);

    }
}

