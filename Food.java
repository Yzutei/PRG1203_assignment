public class Food extends Item {

    // Default constructor
    public Food() {
        super();
    }

    // Full-parameter constructor - passes values up to Item
    public Food(String name, double price, String description, String category) {
        super(name, price, description, category);
    }

    // Override parent's display method
    @Override
    public void displayDetails() {
        System.out.println("Food name: " + getName() +
                "\n Price: " + getPrice() +
                "\n Description: " + getDescription() +
                "\n Category: " + getCategory());
    }
}