public class Drink extends Item {

    // Default constructor
    public Drink() {
        super();
    }

    // Full-parameter constructor - passes values up to Item
    public Drink(String name, double price, String description, String category) {
        super(name, price, description, category);
    }

    // Override parent's display method
    @Override
    public void displayDetails() {
        System.out.println("Drink name: " + getName() +
                "\n Price: " + getPrice() +
                "\n Description: " + getDescription() +
                "\n Category: " + getCategory());
    }
}