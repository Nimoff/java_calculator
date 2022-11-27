import java.util.Scanner;

public class Main {
    public static String calc(String input) throws ScannerException{
        //Массив для поиска доступных мат. операций
        String[] sign = {"+", "-", "*", "/"};
        //Массив для разделения страки на цифры
        String[] findDijit = {"\\+", "-", "\\*", "/"};
        int numberArraySign = -1;
        //Поиск математического символа
        for(int i = 0; i < sign.length; i++){
            if(input.contains(sign[i])){
                numberArraySign = i;
                break;
            }
        }
        // Если символ отсутствует останавливаем выполнение
        if(numberArraySign < 0){
            throw new ScannerException("Операция не поддерживается");
        }
        // Создаем массив из цифр примера
        String[] dijit = input.split(findDijit[numberArraySign]);

        //Проверка чтобы было только две цифры
        if(dijit.length != 2){
            throw new ScannerException("Возможна операция только с 2 цифрами");
        }
        Roma roma = new Roma();
        //Проверяем какие цифры в примере
        if(roma.RomaCheck(dijit[0].trim()) == roma.RomaCheck(dijit[1].trim())){
            boolean isRoma = roma.RomaCheck(dijit[0]);
            // Если римские цифры переводим их в арабские
            int a, b, c = 0;
            if(isRoma){
                a = roma.fromRoma(dijit[0].trim());
                b = roma.fromRoma(dijit[1].trim());
            }
            else {
                //Если арабские цифры, то оставляем как есть
                a = Integer.parseInt(dijit[0].trim());
                b = Integer.parseInt(dijit[1].trim());
            }
            //Проверяем цифры чтобы были не меньше 1 и небольше 10
            if(a > 10 || a < 1 || b > 10 || b < 1){
                throw new ScannerException("Неправильный диапазон");
            }
            // Производим математическую операцию
            switch (sign[numberArraySign]){
                case "+":
                    c = a + b;
                    break;
                case "-":
                    c = a - b;
                    break;
                case "*":
                    c = a * b;
                    break;
                case "/":
                    c = a / b;
                    break;
            }
            // Если римские цифры проверяем на 0, если больше нуля то переводим в арабские цифры в римские
            if(isRoma){
                if(c <= 0){
                    throw new ScannerException("Неправильные значения");
                }
                else{
                    String total = roma.fromArabian(c);
                    return total;
                }
            }
            else {
                String total = Integer.toString(c);
                return total;
            }
        }
        else{
            throw new ScannerException("Введены разные системы счисления");
        }
    }
    public static void main(String[] args) throws ScannerException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите пример, числа от 1 до 10");
        String example = scan.nextLine();
        System.out.println(calc(example));
    }
}
