package dev.mrodriguezul.bucles;

public class ForLoop {
    public static void main(String[] args) {
        //testForLoop1();
    }

    public static void testForLoop1(){
        int c = 0;
        for (int i=1;(i>0 && i<10) | c++==1;i++){
            System.out.println("i: "+i+" - "+c);
        }
    }

    public static void testForLoop2(){
        //i and j variables could be declared inside the for loop or outside the for loop
        //if it is outside, the variables should not be declared again inside the for loop
        //int i = 0;
        //int j = 0;
        for (int i=0, j = 1; i<10; i++){
            System.out.println("i: "+i+" - j: "+j);
        }
    }
}
