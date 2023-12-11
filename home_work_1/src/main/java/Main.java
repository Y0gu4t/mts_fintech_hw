public class Main {
    public static void main(String[] args) {
        // создание объектов класса Check
        Check check1 = new Check(5, 103.5f, 0.75f);
        Check check2 = new Check(2, 55.9f, 42.575f);
        Check check3 = new Check(10, 12.2f, 59.1f);
        // вызов метода calculate для объектов check1, check2, check3
        calculate(check1);
        calculate(check2);
        calculate(check3);
    }

    // метод выводит сумму покупки без скидки и со скидкой
    public static void calculate(Check check) {
        float amount = check.getCount() * check.getProductSum();
        float amountWithDiscount = amount * (1 - check.getProductDiscount()/100);
        System.out.printf("\tЧек\n" +
                        "Сумма покупки без скидки: %.2f\nСумма покупки со скидкой: %.2f\n\n"
                        , amount, amountWithDiscount);
    }
}
