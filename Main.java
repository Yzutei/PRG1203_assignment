import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		List<Item> menu = new ArrayList<>();
		menu.add(new Food("Nasi Lemak", 8.50, "Coconut rice with sambal, anchovies, peanuts", "Main Course"));
		menu.add(new Food("Chicken Rice", 8.00, "Steamed chicken with fragrant rice", "Main Course"));
		menu.add(new Food("Mee Goreng", 7.50, "Stir-fried noodles with vegetables and egg", "Main Course"));
		menu.add( new Food("Spring Rolls", 5.00, "Crispy fried rolls with vegetable filling", "Appetizer"));
		menu.add(new Food("French Fries", 4.50, "Crispy golden fries", "Appetizer"));
		menu.add(new Food ("Chocolate Cake", 6.50, "Rich chocolate layered cake", "Dessert"));

		menu.add(new Drink("Ice Lemon Tea", 3.50, "Chilled tea with fresh lemon","Beverage"));
		menu.add(new Drink("Teh Tarik", 3.00, "Pulled milk tea", "Beverage"));
		menu.add(new Drink("Orange Juice", 4.00, "Freshly squeezed orange juice", "Beverage"));
		menu.add( new Drink("Mineral Water", 2.00, "Bottled still water", "Beverage"));
		menu.add(new Drink("Iced Coffee", 4.50, "Chilled coffee with milk", "Beverage"));
		menu.add(new Drink("Soft Drinks", 3.00, "Carbonated soft drink (Coke/Sprite/etc.)", "Beverage"));

		Order order = new Order(101);   //placeholder number 
		Payment payment = null;
		boolean running = true;

		while (running) {
			System.out.println("\n======MENU======");
			System.out.println("1. View Menu");
			System.out.println("2. Add Item to Cart");
			System.out.println("3. Remove Item");
			System.out.println("4. Update Quantity");
			System.out.println("5. View Order");
			System.out.println("6. Checkout & Payment");
			System.out.println("7. Print Receipt");
			System.out.println("8. Exit");
			System.out.print("Choose an option: ");

			try {
				int choice = input.nextInt();
				input.nextLine();
				switch (choice) {

				case 1: // View Menu
					System.out.println("\n--- Menu ---");
					for (int i = 0; i < menu.size(); i++) {
						Item item = menu.get(i);
						System.out.printf("%d. %s | RM%.2f | %s | %s%n", (i+1), item.getName(),  item.getPrice(),  item.getDescription(), item.getCategory());
					}
					break;

				case 2: // Add Item
					try {
						System.out.print("Enter item number to add: ");
						int addIndex = input.nextInt() - 1;
						System.out.print("Enter quantity: ");
						int qty = input.nextInt();
						input.nextLine();

						if (addIndex >= 0 && addIndex < menu.size()) {
							if (qty > 0) {
								order.addItem(menu.get(addIndex), qty);
								System.out.println("Item added!");
							}  else {
								System.out.println("Quantity must be greater than zero.");
							}
						}  else {
							System.out.println("Invalid item number.");
						}
					}  catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter numeric values.");
						input.nextLine();
					} catch (Exception e) {
						System.out.println("Error adding item: " + e.getMessage());
					}
					break;


				case 3: // Remove Item
					try { 
						System.out.print("Enter item number to remove: ");
						int remIndex = input.nextInt() - 1;
						input.nextLine();

						if (remIndex >= 0 && remIndex < menu.size()) {
							order.removeItem(menu.get(remIndex));
							System.out.println("Item removed!");
						} else { 
							System.out.println("Invalid item number.");
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter a number.");
						input.nextLine();
					} catch (Exception e) {
						System.out.println("Error removing item: " + e.getMessage());
					}
					break;

				case 4:   //Quantity update
					try {
						System.out.print("Enter item number to update: ");
						int updIndex = input.nextInt() - 1;
						System.out.print("Enter new quantity: ");
						int newQty = input.nextInt();
						input.nextLine();

						if (updIndex >= 0 && updIndex < menu.size()) {
							if (newQty > 0) {
								order.updateQuantity(menu.get(updIndex), newQty);
								System.out.println("Quantity updated!");
							} else {
								System.out.println("Quantity cannot be negative.");
							}
						} else { 
							System.out.println("Invalid item number.");
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter numeric values.");
						input.nextLine();
					} catch (Exception e) {
						System.out.println("Error updating quantity: " + e.getMessage());
					}
					break;

				case 5: // View Order
					order.displayOrder();
					break;

				case 6: // Checkout & Payment
					try {
						if (order.isEmpty()) {
							System.out.println("Your cart is empty. Please add items before checkout.");
							break;
						}
						System.out.print("Enter payment ID: ");
						int pID = input.nextInt();
						input.nextLine();

						System.out.print("Enter payment method (Cash/Card): ");
						String method = input.nextLine();

						System.out.print("Enter amount paid: ");
						double amtPaid = input.nextDouble();
						input.nextLine();

						if (amtPaid < 0) { 
							System.out.println("Amount paid cannot be negative.");
							break;
						}

						payment = new Payment(pID, order, method, order.calculateTotal(), amtPaid);
						if (payment.processPayment()) {
							System.out.println("Checkout successful!");
						} else {
							payment = null;
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter numeric values for ID and amount.");
						input.nextLine(); // clear bad input
					} catch (Exception e) {
						System.out.println("Error during payment: " + e.getMessage());
					}

					break;

				case 7: // Print Receipt
					if (payment != null) {
						payment.printReceipt();
					} else {
						System.out.println("No payment has been processed yet.");
					}
					break;

				case 8: // Exit
					running = false;
					System.out.println("Exiting program...");
					break;

				default:
					System.out.println("Invalid choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				input.nextLine(); //Clear invalid input
			}
		}

		input.close();

	}
}