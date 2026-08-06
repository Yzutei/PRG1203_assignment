import java.time.LocalDateTime;
import java.util.ArrayList;

//Represents a customer order
public class Order {
	
	private int orderID;
	private LocalDateTime orderDate;
	private ArrayList<Item> items;
	private ArrayList<Integer> quantities;
	
	public Order() {
		this.items = new ArrayList<>();
		this.quantities = new ArrayList<>();
		this.orderDate = LocalDateTime.now();
	}
	
	public Order(int orderID) {
		this();
		setOrderID(orderID);
	}
	
	public int getOrderID() {
		return orderID;
	}
	
	public void setOrderID(int orderID) {
		if(orderID <= 0){
	        throw new IllegalArgumentException("Order ID must be positive.");
		}
		this.orderID = orderID;
	}
	
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	//Returns a copy so callers cannot modify the internal list directly
	public ArrayList<Item> getItems() {
		return new ArrayList<>(items);
	}
	
	public int getQuantity(Item item) {
		int index = items.indexOf(item);
		if (index == -1) {
			return 0;
		}
		return quantities.get(index);
	}

	//Adds a new item, or increases quantity if it is already in the order
	public void addItem(Item item, int quantity) {
		if (item == null) {
			throw new IllegalArgumentException("Item cannot be null.");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero.");
		}
		
		int index = items.indexOf(item);
		if (index == -1) {
			items.add(item);
			quantities.add(quantity);
		} else {
			int currentQty = quantities.get(index);
			quantities.set(index, currentQty + quantity);
		}
	}

	public void removeItem(Item item) {
		int index = items.indexOf(item);
		if (index == -1) {
			throw new IllegalArgumentException("Item not found in this order.");
		}
		items.remove(item);
		quantities.remove(index);
	}

	//Setting quantity to 0 removes the item entirely
	public void updateQuantity(Item item, int quantity) {
        int index = items.indexOf(item);
        if (index == -1) {
            throw new IllegalArgumentException("Item not found in this order.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        if (quantity == 0) {
            items.remove(index);
            quantities.remove(index);
        } else {
            quantities.set(index, quantity);
        }
    }
	
	public double calculateTotal() {
		double total = 0.0;
		for(int i = 0; i < items.size(); i++) {
			total += items.get(i).getPrice() * quantities.get(i);
		}
		return total;
	}
	
	public boolean isEmpty() {
		return items.isEmpty();

	}

	//Prints a formatted summary of the order to the console
	public void displayOrder( ) {
		System.out.println("===== Order #" + orderID + " =====");
		System.out.println("Date: " + orderDate);
		
		if (items.isEmpty()) {
			System.out.println("(No items in this order)");
			return;
		}
		
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			int qty = quantities.get(i);
			double subtotal = item.getPrice() * qty;
			System.out.printf("%-20s x%-3d RM%-8.2f = RM%.2f%n",
					item.getName(), qty, item.getPrice(), subtotal);
		}
		
		System.out.printf("TOTAL: RM%.2f%n", calculateTotal());
		System.out.println("=============================");
	}
}
