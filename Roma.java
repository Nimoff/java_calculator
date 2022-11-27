import java.util.HashMap;
import java.util.TreeMap;
public class Roma {
    HashMap <Character, Integer> romaDijit = new HashMap<>();
    TreeMap <Integer, String> arabianDijit = new TreeMap<>();

    public Roma(){
        romaDijit.put('I', 1);
        romaDijit.put('V', 5);
        romaDijit.put('X', 10);
        romaDijit.put('L', 50);
        romaDijit.put('C', 100);
        arabianDijit.put(1, "I");
        arabianDijit.put(4, "IV");
        arabianDijit.put(5, "V");
        arabianDijit.put(9, "IX");
        arabianDijit.put(10, "X");
        arabianDijit.put(40, "XL");
        arabianDijit.put(50, "L");
        arabianDijit.put(90, "XC");
        arabianDijit.put(100, "C");
    }


    public boolean RomaCheck(String Number){
        return romaDijit.containsKey(Number.charAt(0));
    }

    //Переводим из римских цифр в арабские
    public int fromRoma(String Number){
        int result;
        if(Number.length() == 1){
            result = romaDijit.get(Number.charAt(0));
        }
        else{
            int startDijit = Number.length() - 1;
            result = romaDijit.get(Number.charAt(startDijit));
            int arabian;
            for(int i = startDijit - 1; i >= 0; i--){
                arabian = romaDijit.get(Number.charAt(i));
                if(romaDijit.get(Number.charAt(i + 1)) <= arabian){
                    result += arabian;
                }
                else{
                    result -= arabian;
                }
            }
        }
        return result;
    }

    //Переводим из арабских цифр в римские
    public String fromArabian(int Number){
        String result = "";
        int key;
        while (Number != 0){
            key = arabianDijit.floorKey(Number);
            result += arabianDijit.get(key);
            Number -= key;
        }
        return result;
    }
}
