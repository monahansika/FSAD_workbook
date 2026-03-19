package inventory.app;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import inventory.entity.Product;
import inventory.util.HibernateUtil;

public class ProductApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- INVENTORY MENU ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    HibernateUtil.getSessionFactory().close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // CREATE
    static void addProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter description: ");
        String desc = sc.nextLine();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        Product product = new Product(name, desc, price, qty);
        session.save(product);

        tx.commit();
        session.close();

        System.out.println("Product added successfully");
    }

    // READ
    static void viewProduct() {
        System.out.print("Enter product ID: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Product p = session.get(Product.class, id);
        session.close();

        if (p != null) {
            System.out.println("\nName: " + p.getName());
            System.out.println("Description: " + p.getDescription());
            System.out.println("Price: ₹" + p.getPrice());
            System.out.println("Quantity: " + p.getQuantity());
        } else {
            System.out.println("Product not found");
        }
    }

    // UPDATE
    static void updateProduct() {
        System.out.print("Enter product ID: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p = session.get(Product.class, id);
        if (p != null) {
            System.out.print("Enter new price: ");
            p.setPrice(sc.nextDouble());

            System.out.print("Enter new quantity: ");
            p.setQuantity(sc.nextInt());

            session.update(p);
            System.out.println("Product updated");
        } else {
            System.out.println("Product not found");
        }

        tx.commit();
        session.close();
    }

    // DELETE
    static void deleteProduct() {
        System.out.print("Enter product ID: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p = session.get(Product.class, id);
        if (p != null) {
            session.delete(p);
            System.out.println("Product deleted");
        } else {
            System.out.println("Product not found");
        }

        tx.commit();
        session.close();
    }
}