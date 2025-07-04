package dev.mrodriguezul.datatypes;

import dev.mrodriguezul.entity.Mensaje;
import dev.mrodriguezul.entity.Person;

public class LocalVariable {

    static int referece1;
    //cannot use var for fields or class variables
    //static var reference2 = 10; //compilation error
    String texto1;
    //var texto2 = "algo"; //compilation error

    public static void main(String[] args) {
        testLocalVariable1();
    }
    public static void testLocalVariable1(){
        //using inferred types with var
        var i = 10;// integer
        System.out.println(++i);

        var array = new int[]{1,2,3,4,5};
        System.out.println(array[0]);

        var respMethod = new Mensaje().lanzarMensaje("Hello BNB!");
        System.out.println(respMethod.concat(" - ").concat("This is a test"));

        var persona = new Person("Miguel", "miguel@gmail.com");
        System.out.println(persona);
        //considerations when we use this type of variable
        //var is not a keyword, it is a reserved type name
        //var can be used only for local variables

        //var cannot be used for fields in a class
        //example: var x = 10;//compilation error

        //var cannot be used for method parameters
        //example: public void testMethod(var x){}//compilation error

        //var cannot be used for return types
        //example: public var testMethod(){return 10;}//compilation error

        //var cannot be used for catch parameters
        // example: try{}catch(var e){}//compilation error

        //var cannot be used for array initializers
        //var[] array = new int[]{1,2,3,4,5};//compilation error
        //var array2 = {1,2,3,4,5};//compilation error

        //var cannot be used for lambda expressions
        //var x = (a,b) -> a+b;//compilation error

        //var cannot be used for method references
        //var x = System.out::println;//compilation error

        //var cannot be used for null values
        //var x = null;//compilation error

        //var cannot be used for multiple variables
        //var x = 10, y = 20;//compilation error

        //var cannot be used for a variable without an initializer
        //var x;//compilation error
    }
}
