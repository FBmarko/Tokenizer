package tokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Tokenizer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dosya adini giriniz: ");
        String fileName = scanner.nextLine(); 

     
        try {
            tokenizeArithmeticExpressions(fileName);
        } catch (IOException e) {
            System.out.println("Dosya bulunamadi.");
        }
    }

   
    public static void tokenizeArithmeticExpressions(String fileName) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        int character;

        
        char[] result = new char[100];  
        int j = 0;  

       
        while ((character = br.read()) != -1) {  
           
            if (j >= result.length) {
                result = expandArray(result);  // 
            }

           
            if (character >= '0' && character <= '9') {
                while (character >= '0' && character <= '9') {
                    result[j++] = (char) character; 
                    character = br.read();  

                    
                    if (j >= result.length) {
                        result = expandArray(result);
                    }
                }
                result[j++] = ' ';  
            }

          
            if (character == '+' || character == '-' || character == '*' || 
                character == '/' || character == '(' || character == ')') {
                result[j++] = (char) character;  
                result[j++] = ' ';  

                
                if (j >= result.length) {
                    result = expandArray(result);
                }
            }

            
            if (character == '\n') {
               
                for (int k = 0; k < j; k++) {
                    System.out.print(result[k]);
                }
                System.out.println();  
                j = 0;  
            }
        }

       
        if (j > 0) {
            for (int k = 0; k < j; k++) {
                System.out.print(result[k]);
            }
            System.out.println();  
        }

        br.close();  
    }

    
    public static char[] expandArray(char[] oldArray) {
       
        char[] newArray = new char[oldArray.length * 2];
       
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        return newArray;
    }
}
