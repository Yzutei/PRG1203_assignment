public class Drink extends Item {

    public Drink() {
        super();
    }

    public Drink(String name, double price, String description, String category) {
        super(name, price, description, category);
    }

    @Override
    public void displayDetails() {
        System.out.println("Drink name: " + getName() +
                "\n Price: " + getPrice() +
                "\n Description: " + getDescription() +
                "\n Category: " + getCategory());
    }
}