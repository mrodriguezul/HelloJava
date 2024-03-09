package dev.mrodriguezu.operaciones;

public class Calculos {
    public static void main(String[] args) {
        //usoMathFunction();
        casteoDatos();
    }

    public static void usoMathFunction(){
        double n1 = 3.55;
        double n2 = 4.78;

        int n3 = 3;
        int n4 = 2;

        System.out.println(Math.abs(n1));//Valor absoluto de un decimal (Quita el signo)
        System.out.println(Math.abs(n2));

        System.out.println(Math.nextDown(n1));//valor siguiente hacia abajo
        System.out.println(Math.nextUp(n1));//valor siguiente hacia arriba

        System.out.println(Math.nextDown(n3));
        System.out.println(Math.nextUp(n3));

        System.out.println(Math.incrementExact(n4));//Incrementa en 1 el valor de un entero
        System.out.println(Math.decrementExact(n4));//Decrementa en 1 el valor de un entero

        System.out.println(Math.ceil(n1));//redondea al entero mayor (hacia arriba)
        System.out.println(Math.floor(n1));//redondea al entero menor (hacia abajo)
        System.out.println(Math.round(n1));//redondea de manera natural hacia abajo o hacia arriba

        System.out.println(Math.max(n1, n3));//maximo de 2 decimales
        System.out.println(Math.min(n1, n3));//mínimo de 2 decimales

        System.out.println(Math.sqrt(81));//raís cuadrada
        System.out.println(Math.pow(n1,n2));//exponencial
    }

    public static void casteoDatos(){
        int n1 = 20;
        int n2 = 3;
        double n3 = 2.0;
        double n4 = 26.9;

        System.out.println(n1+n2);//ambos enteros devuelve un valor entero
        System.out.println(n1+n3);//decimal + entero devuelve decimal
        System.out.println(n4/n3);//decimal y entero devuelve decimal
        System.out.println(n1/n2);//ambos enteros devuelve un valor entero
        System.out.println(n1/n3);//entero / decimal devuelve decimal

        System.out.println((double) n1+n2);//fuerza decimal
        System.out.println((int) n4/n3);//fuerza entero (trunca el valor, no redondea)

        short n5 = 64;
        char c1 = (char)n5;
        System.out.println(n5);
        System.out.println((char) n5);
        System.out.println(c1);

        long l1 = (long) n4;
        System.out.println(l1);
    }
}
