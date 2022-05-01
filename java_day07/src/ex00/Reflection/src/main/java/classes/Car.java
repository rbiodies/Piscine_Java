package classes;

import java.util.StringJoiner;

public class Car {
    private String  brand;
    private String  model;
    private int     price;

    public Car() {
        this.brand = "Default brand";
        this.model = "Default model";
        this.price = 0;
    }

    public Car(String brand, String model, int price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public int  increase(int value) {
        this.price = value;
        return price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("brand='" + brand + '\'')
                .add(", model='" + model + '\'')
                .add(", price=" + price)
                .toString();
    }
}
