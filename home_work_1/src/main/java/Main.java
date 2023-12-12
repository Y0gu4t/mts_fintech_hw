import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // создание объектов класса Check
        Check check1 = new Check(5, 103.5f, 0.75f);
        Check check2 = new Check(2, 55.9f, 42.575f);
        Check check3 = new Check(10, 12.2f, 59.1f);
        // вызов метода calculate для объектов check1, check2, check3
        calculateAndPrintTotalCheck(check1);
        calculateAndPrintTotalCheck(check2);
        calculateAndPrintTotalCheck(check3);
    }

    // метод выводит сумму покупки без скидки и со скидкой
    public static void calculateAndPrintTotalCheck(Check check) {
        BigDecimal amount = BigDecimal.valueOf(check.getCount()).multiply(BigDecimal.valueOf(check.getProductSum()));
        BigDecimal amountWithDiscount = amount.multiply(BigDecimal.valueOf(1 - check.getProductDiscount()/100));
        System.out.printf("\tЧек\n" +
                        "Сумма покупки без скидки: %.2f\nСумма покупки со скидкой: %.2f\n\n"
                        , amount, amountWithDiscount);
    }
}
