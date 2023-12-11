/**
 * Класс чек используется для подробного описания
 * покупки, совершенной клиентом
 * */
public class Check {
    // количество товаров
    private  int count;
    // сумма товара
    private float productSum;
    // скидка на товар
    private float productDiscount;

    // конструктор класса
    public Check(int count, float productSum, float productDiscount) {
        this.count = count;
        this.productSum = productSum;
        this.productDiscount = productDiscount;
    }

    // методы get
    public int getCount() {
        return count;
    }

    public float getProductSum() {
        return productSum;
    }

    public float getProductDiscount() {
        return productDiscount;
    }
}
