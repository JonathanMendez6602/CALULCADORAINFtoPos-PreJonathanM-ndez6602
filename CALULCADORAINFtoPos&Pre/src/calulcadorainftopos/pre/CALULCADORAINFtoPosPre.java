/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calulcadorainftopos.pre;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Jhonatan
 */

//Esta Calculadora se maneja por el infijo y resuelve prefijo y postfijo
public class CALULCADORAINFtoPosPre {

    /**
     * @param args the command line arguments
     */
    public String ToPostfijo(String infix)
    {
        Stack stack=new Stack();
        String postfix=new String();
        for(int i=0; infix!=null && i<infix.length(); i++)
        {
            char c=infix.charAt(i);
                         // El juez c no es un espacio
            if(c!=' ')
            {
                                 // Si c es un paréntesis izquierdo, empuja la pila
                if(c=='(')
                {
                    stack.push(c);
                }
                                 // Si c es el paréntesis derecho, saca el elemento superior de la pila hasta el paréntesis izquierdo
                else if(c==')')
                {
                                         char top = (char) stack.pop (); // saca el elemento superior de la pila
                    while(top!='(')
                    {
                        postfix=postfix.concat(String.valueOf(top));
                        top=(char)stack.pop();
                    }
                }
                                 // Si c es un operador
                else if(isOperator(c))
                {
                                         if (! stack.isEmpty ()) // la pila no está vacía
                    {
                                                 char top = (char) stack.pop (); // Elimina la parte superior de la pila cada vez
                                                 if (getPriority (c)> getPriority (top)) // Si la prioridad del operador es mayor que la prioridad de la parte superior de la pila, se devolverá a la pila
                            stack.push(top);
                                                 else // Si la prioridad del operador es menor o igual que la prioridad de la parte superior de la pila, siga eliminando los operadores de la pila
                        {
                            while (getPriority(c) <= getPriority(top)) {
                                postfix = postfix.concat(String.valueOf(top));
                                if (stack.isEmpty())
                                    break;
                                top = (char) stack.pop();
                            }
                        }
                    }
                    stack.push(c);
                }
                                 // Si c es el operando
                else
                {
                    postfix=postfix.concat(String.valueOf(c));
                }
            }
        }
        while(!stack.isEmpty())
        {
            postfix=postfix.concat(String.valueOf(stack.pop()));
        }
        return postfix;
    }
    //Prefijo
    public static void  ToPrefijo(String input){                       //System.out.println("La cadena de lectura es: "+ input +" La longitud es: "+ len);
                       char c, tempChar; // Estas dos son variables intermedias, c se utiliza para almacenar el carácter en la posición correspondiente en el bucle,
                     Stack <Character> s1 = new Stack <Character> (); // Instancia una pila de símbolos
                     Stack <Integer> s2 = new Stack <Integer> (); // Instancia una pila de números
                     Stack <Object> expression = new Stack <Object> (); // Instanciar una pila de expresiones de prefijo
                       // escanea la expresión de derecha a izquierda
           for (int i=input.length()-1; i>=0; --i) { 
             c = input.charAt(i);
                           // Determine si la lectura es un número, si lo es, inserte el número en la pila de operandos y la pila de expresiones
             if (Character.isDigit(c)) {
               String s = String. valueOf(c);
                               // Convertir a tipo Int:
               int j = Integer.parseInt(s);
                   s2.push(j); 
                   expression.push(j); 
             } else if (isOperator2(c)) {
                               // Si el personaje actual es un operador (incluidos paréntesis)
                   while (!s1.isEmpty() 
                               && s1.peek() != ')' 
                               && priorityCompare(c, s1.peek()) < 0) { 
                                             // La pila del operador actual no está vacía y el operador en la parte superior de la pila del operador no es un corchete derecho y la prioridad del operador actual es menor que la prioridad del operador en la parte superior de la pila,
                                             // Luego tome el elemento superior de la pila de operadores y realice una operación con los dos elementos superiores de la pila de operandos e inserte el resultado de la operación en la pila de operandos
                         expression.push(s1.peek()); 
                         s2.push( calc(s2.pop(), s2.pop(), s1.pop())); 
                   } 
                   s1.push(c); 
             } else if (c == ')' ) {
                               // Debido a que escaneamos la expresión infija de derecha a izquierda, para un "()" primero debe leerse en el corchete derecho
                   s1.push(c); 
             } else if (c == '(' ) { 
                               // Si es un paréntesis izquierdo "(", entonces el operador en la parte superior de la pila S1 aparece en secuencia y se inserta en la pila de expresión hasta que se encuentra el paréntesis izquierdo, en cuyo punto se descarta el par de paréntesis;
                   while ((tempChar=s1.pop()) != ')' ) { 
                         expression.push(tempChar); 
                         s2.push( calc(s2.pop(), s2.pop(), tempChar)); 
                         if (s1.isEmpty()) { 
                               throw new IllegalArgumentException( 
                                     "bracket dosen't match, missing right bracket ')'."); 
                         } 
                   } 
             } else if (c == ' ' ) { 
                                       // Si la expresión contiene espacios, no maneje espacios
             } else { 
                   throw new IllegalArgumentException( 
                               "wrong character '" + c + "'" ); 
             } 
           }
           while (!s1.isEmpty()) { 
             tempChar = s1.pop(); 
             expression.push(tempChar); 
             s2.push( calc(s2.pop(), s2.pop(), tempChar)); 
           } 
           while (!expression.isEmpty()) { 
             System. out .print(expression.pop() + " " ); 
           } 
           int result = s2.pop(); 
           if (!s2.isEmpty()) 
             throw new IllegalArgumentException( "input is a wrong expression.");  
           System. out .println(); 
          
     }
      /**
             * Calcular los dos operandos y operadores dados
      * @param num1
      * @param num2
      * @param op
             * @return devuelve el resultado de calcular un entero
      */
      private static Integer calc( int num1, int num2, char op) {
       
            switch (op) { 
            case '+' : 
                  return num1 + num2; 
            case '-' : 
                  return num1 - num2; 
            case '*' : 
                  return num1 * num2; 
            case '/' : 
                  if (num2 == 0) throw new IllegalArgumentException("divisor can't be 0." ); 
                  return num1 / num2; 
            default : 
                  return 0; // will never catch up here 
            } 
       
     }
      /**
             * Método para juzgar si un personaje es un operador
      * @param c
      * @return
      */
      private static boolean isOperator2( char c) {
                return (c=='+' || c=='-' || c=='*' || c=='/' ); 
     }
      /**
             * Método de juzgar la prioridad del operador
      * @param op1
      * @param op2
             * @return si el valor de retorno es:
             * -1 significa que op1 tiene menor prioridad que op2,
             * 0 significa que la prioridad de op1 es igual a op2,
             * 1 significa que op1 tiene mayor prioridad que op2.
      */
      private static int priorityCompare( char op1, char op2) { 
         switch (op1) { 
         case '+' : case '-': 
               return (op2 == '*' || op2 == '/' ? -1 : 0); 
         case '*' : case '/': 
               return (op2 == '+' || op2 == '-' ? 1 : 0); 
         } 
         return 1; 
   }
    
         // Determinar si el personaje es un operador
    public boolean isOperator(char c)
    {
        if(c=='+' || c=='-' || c=='*' || c=='/' || c=='%' || c=='^')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
 
         // Devuelve la prioridad del operador
    public int getPriority(char c)
    {
        if(c=='+' || c=='-')
            return 1;
        else if(c=='*' || c=='/' || c=='%')
            return 2;
        else if(c=='^')
            return 3;
        else
            return 0;
    }
 
         // Evaluar la expresión de sufijo
    public double numberCalculate(String result)
    {
        Stack stack=new Stack();
        for(int i=0; result!=null && i<result.length(); i++)
        {
            char c=(char)result.charAt(i);
                         // Si es un operando, saca los dos números superiores de la pila para realizar operaciones y empuja el resultado a la pila
            if(isOperator(c))
            {
                double d2=Double.valueOf(stack.pop().toString());
                double d1=Double.valueOf(stack.pop().toString());
                double d3=0;
 
                if(c=='+')
                {
                    d3=d1+d2;
                }
                else if(c=='-')
                {
                    d3=d1-d2;
                }
                else if(c=='*')
                {
                    d3=d1*d2;
                }
                else if(c=='/')
                {
                    d3=d1/d2;
                }
                else if(c=='%')
                {
                    d3=d1%d2;
                }
                else if(c=='^')
                {
                    d3=Math.pow(d1,d2);
                }
                stack.push(d3);
            }
            else
            {
                stack.push(c);
            }
        }
        return (Double)stack.pop();
    }
    
    public static void main(String[] args) {
        int sa;
        // TODO code application logic here
        do{
            CALULCADORAINFtoPosPre calculate=new CALULCADORAINFtoPosPre();
        Scanner escribir = new Scanner(System.in);
        Scanner enteros = new Scanner(System.in);
        System.out.println("Dijite la operacion en infijo");
        String infijo = escribir.nextLine();
        String postfijo = calculate.ToPostfijo(infijo);
        //String prefijo = calculate.ToPrefijo(infijo);
        double resultado=calculate.numberCalculate(postfijo);
        System.out.println ("Infijo al resultado del sufijo:" + postfijo);
        System.out.print ("Infijo al resultado del prefijo:");
        ToPrefijo(infijo);
        System.out.println ("Resultado de cálculo de expresión de sufijo:" + resultado);
        System.out.println("Desea Realizar otra operacion Y=1 N=0");
        sa=enteros.nextInt();
        }while(sa!=0);
        

    }
    
}
