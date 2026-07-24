public class Food extends Item {

    public Food() {
        super();
    }

    public Food(String name, double price, String description, String category) {
        super(name, price, description, category);
    }

    @Override
    public void displayDetails() {
        System.out.println("Food name: " + getName() +
                "\n Price: " + getPrice() +
                "\n Description: " + getDescription() +
                "\n Category: " + getCategory());
    }
}