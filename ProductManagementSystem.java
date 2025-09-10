import java.util.*; 
// Product class implementing Comparable 
class Product implements Comparable<Product> { 
private int id; 
private String name; 
private double price; 
public Product(int id, String name, double price) { 
this.id = id; 
this.name = name; 
this.price = price; 
} 
public int getId() { return id; } 
public String getName() { return name; } 
public double getPrice() { return price; } 
public void setName(String name) { this.name = name; } 
public void setPrice(double price) { this.price = price; } 
// Comparable: default sorting by price 
@Override 
public int compareTo(Product other) { 
return Double.compare(this.price, other.price); 
} 
@Override 
public String toString() { 
return "Product{" + "id=" + id + ", name='" + name + "', price=" + price + '}'; 
} 
} 
// Comparator to sort by ID 
class SortById implements Comparator<Product> { 
public int compare(Product p1, Product p2) { 
return Integer.compare(p1.getId(), p2.getId()); 
} 
} 
// Comparator to sort by Name 
class SortByName implements Comparator<Product> { 
public int compare(Product p1, Product p2) { 
return p1.getName().compareToIgnoreCase(p2.getName()); 
} 
} 
 
 
 
public class ProductManagementSystem { 
    private static final Scanner sc = new Scanner(System.in); 
    private static final List<Product> products = new ArrayList<>(); 
 
    public static void main(String[] args) { 
        // Preloaded Products 
        products.add(new Product(101, "Laptop", 55000)); 
        products.add(new Product(102, "Mobile", 25000)); 
        products.add(new Product(103, "Headphones", 2000)); 
        products.add(new Product(104, "Tablet", 30000)); 
 
        int choice; 
        do { 
            try { 
                System.out.println("\n===== Product Management Menu ====="); 
                System.out.println("1. Display Products"); 
                System.out.println("2. Add Product"); 
                System.out.println("3. Update Product"); 
                System.out.println("4. Delete Product"); 
                System.out.println("5. Sort by Price (Comparable)"); 
                System.out.println("6. Sort by ID (Comparator)"); 
                System.out.println("7. Sort by Name (Comparator)"); 
                System.out.println("8. Exit"); 
                System.out.print("Enter your choice: "); 
 
                choice = Integer.parseInt(sc.nextLine()); 
 
                switch (choice) { 
                    case 1 -> displayProducts(); 
                    case 2 -> addProduct(); 
                    case 3 -> updateProduct(); 
                    case 4 -> deleteProduct(); 
                    case 5 -> sortByPrice(); 
                    case 6 -> sortById(); 
                    case 7 -> sortByName(); 
                    case 8 -> System.out.println("Exiting program..."); 
                    default -> System.out.println("  Invalid choice! Try again."); 
                } 
            } catch (NumberFormatException e) { 
                System.out.println("  Invalid input! Please enter a number between 1-8."); 
                choice = 0; // reset 
            } 
        } while (choice != 8); 
 
        sc.close(); 
    } 
 
    // Display all products 
    private static void displayProducts() { 
        if (products.isEmpty()) { 
            System.out.println("No products available."); 
        } else { 
            System.out.println("\nProducts:"); 
            for (Product p : products) { 
                System.out.println(p); 
            } 
        } 
    } 
 
    // Add product with duplicate check 
    private static void addProduct() { 
        try { 
            System.out.print("Enter Product ID: "); 
            int id = Integer.parseInt(sc.nextLine()); 
 
            for (Product p : products) { 
                if (p.getId() == id) { 
                    System.out.println("  Product with ID " + id + " already exists!"); 
                    return; 
                } 
            } 
 
            System.out.print("Enter Product Name: "); 
            String name = sc.nextLine(); 
 
            System.out.print("Enter Product Price: "); 
            double price = Double.parseDouble(sc.nextLine()); 
 
            products.add(new Product(id, name, price)); 
            System.out.println("   Product added successfully!"); 
        } catch (NumberFormatException e) { 
            System.out.println("  Invalid input! ID and Price must be numeric."); 
        } 
    } 
 
    // Update product by ID 
    private static void updateProduct() { 
        try { 
            System.out.print("Enter Product ID to Update: "); 
            int id = Integer.parseInt(sc.nextLine()); 
 
            Product found = null; 
            for (Product p : products) { 
                if (p.getId() == id) { 
                    found = p; 
                    break; 
                } 
            } 
 
            if (found == null) { 
                System.out.println("  Product not found with ID: " + id); 
                return; 
            } 
 
            System.out.print("Enter New Name: "); 
            String newName = sc.nextLine(); 
            System.out.print("Enter New Price: "); 
            double newPrice = Double.parseDouble(sc.nextLine()); 
 
            found.setName(newName); 
            found.setPrice(newPrice); 
 
            System.out.println("   Product updated successfully!"); 
        } catch (NumberFormatException e) { 
            System.out.println("  Invalid input! Please enter numeric values where required."); 
        } 
    } 
 
    // Delete product by ID 
    private static void deleteProduct() { 
        try { 
            System.out.print("Enter Product ID to Delete: "); 
            int id = Integer.parseInt(sc.nextLine()); 
 
            Product found = null; 
            for (Product p : products) { 
                if (p.getId() == id) { 
                    found = p; 
                    break; 
                } 
            } 
 
            if (found != null) { 
                products.remove(found); 
                System.out.println("   Product deleted successfully!"); 
            } else { 
                System.out.println("  No product found with ID: " + id); 
} 
} catch (NumberFormatException e) { 
System.out.println("  Invalid input! Please enter a numeric ID."); 
} 
} 
// Sort methods 

private static void sortByPrice() { 
if (products.isEmpty()) { 
System.out.println("No products to sort."); 
} else { 
Collections.sort(products); 
System.out.println("\nProducts Sorted by Price:"); 
for (Product p : products) 
	System.out.println(p); 
} 
} 
private static void sortById() { 
if (products.isEmpty()) { 
System.out.println("No products to sort."); 
} else { 
Collections.sort(products, new SortById()); 
System.out.println("\nProducts Sorted by ID:"); 
for (Product p : products) System.out.println(p); 
} 
} 
private static void sortByName() { 
if (products.isEmpty()) { 
System.out.println("No products to sort."); 
} else { 
Collections.sort(products, new SortByName()); 
System.out.println("\nProducts Sorted by Name:"); 
for (Product p : products) System.out.println(p); 
} 
} 
} 