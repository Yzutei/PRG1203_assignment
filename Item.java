public abstract class Item {
    private String name;
    private double price;
    private String description;
    private String category;

    public Item() {

    }

    public Item(String name, double price, String description, String category) {
        setName(name);
        setPrice(price);
        setDescription(description);
        setCategory(category);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
    }
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void displayDetails() {
        System.out.println("Item name: " + name +
                "\n Price:" + price +
                "\n Description:" + description +
                "\n Category:" + category);
    }

}
