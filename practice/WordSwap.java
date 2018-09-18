package gyak20171202;

import java.util.Scanner;

public class WordSwap {
  
    // egyszerű String-es szócserélő algoritmus
    
    public static String swapWords(String oldString, String word1, String word2 ) {
        //oldString = a beolvasandó szöveg  //word1 == a kicserélendő szó   //word2 = amire kicserélje
        String stringToEdit = oldString;    // a régi szöveg ebbe másolva
        String newString = ""; //az új szöveg
        String actualWord = ""; //az éppen vizsgált szó
        int awLenght;   //az éppen vizsgált szó hossza
        Scanner sc = new Scanner(stringToEdit);
        while (sc.hasNext()){
            actualWord = sc.next();
            if (actualWord.equals(word1)) {
                actualWord = word2;
                awLenght = word1.length();
            } else {
                awLenght = actualWord.length();
            }
            if (newString.equals("")) {
                newString = actualWord;
            } else {
                newString = newString.concat(" ");
                newString = newString.concat(actualWord);
            }
            stringToEdit = stringToEdit.substring(awLenght);
            if(stringToEdit.equals("")) {
                break;
            }
            stringToEdit = stringToEdit.substring(1);
            sc = new Scanner(stringToEdit);
        }
        return newString;
    }

    


    
}
