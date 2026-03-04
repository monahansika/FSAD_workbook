package com.klu.app;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.klu.product.Product;
import com.klu.util.HibernateUtil;

public class mainApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("===== Retail Inventory System =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Product by ID");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    getProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    HibernateUtil.getSessionFactory().close();
                    System.out.println("Application closed.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // CREATE
    public static void addProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter name: ");
        sc.nextLine(); // clear buffer
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

        System.out.println("Product added successfully!");
    }

    // READ
    public static void getProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product product = session.get(Product.class, id);

        if (product != null) {
            System.out.println("\n--- Product Details ---");
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
        } else {
            System.out.println("Product not found!");
        }

        session.close();
    }

    // UPDATE
    public static void updateProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();

        Product product = session.get(Product.class, id);

        if (product != null) {
            System.out.print("Enter new price: ");
            double price = sc.nextDouble();

            System.out.print("Enter new quantity: ");
            int qty = sc.nextInt();

            product.setPrice(price);
            product.setQuantity(qty);

            session.update(product);
            tx.commit();
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found!");
            tx.rollback();
        }

        session.close();
    }

    // DELETE
    public static void deleteProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();

        Product product = session.get(Product.class, id);

        if (product != null) {
            session.delete(product);
            tx.commit();
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found!");
            tx.rollback();
        }

        session.close();
    }
}
