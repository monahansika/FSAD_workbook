package com.inventory.main;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class MainApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int ch;

        do {
            System.out.println("\n===== HQL MENU =====");
            System.out.println("1. Insert Sample Products");
            System.out.println("2. Sort by Price (Ascending)");
            System.out.println("3. Sort by Price (Descending)");
            System.out.println("4. Sort by Quantity (High to Low)");
            System.out.println("5. Pagination (First 3 / Next 3)");
            System.out.println("6. Aggregate Queries");
            System.out.println("7. Group By Description");
            System.out.println("8. Filter by Price Range");
            System.out.println("9. LIKE Queries");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            ch = sc.nextInt();

            switch (ch) {
                case 1: insertProducts(); break;
                case 2: sortByPriceAsc(); break;
                case 3: sortByPriceDesc(); break;
                case 4: sortByQuantity(); break;
                case 5: pagination(); break;
                case 6: aggregateQueries(); break;
                case 7: groupByDescription(); break;
                case 8: priceRange(); break;
                case 9: likeQueries(); break;
            }

        } while (ch != 0);

        HibernateUtil.getSessionFactory().close();
    }

    static void insertProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(new Product("Laptop", "Electronics", 70000, 10));
        session.save(new Product("Mouse", "Electronics", 1200, 40));
        session.save(new Product("Keyboard", "Electronics", 2500, 25));
        session.save(new Product("Chair", "Furniture", 6000, 15));
        session.save(new Product("Table", "Furniture", 12000, 5));
        session.save(new Product("Pen", "Stationery", 50, 100));

        session.getTransaction().commit();
        session.close();

        System.out.println("Products Inserted");
    }

    static void sortByPriceAsc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery(
                "from Product order by price asc", Product.class);
        q.list().forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
        session.close();
    }

    static void sortByPriceDesc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery(
                "from Product order by price desc", Product.class);
        q.list().forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
        session.close();
    }

    static void sortByQuantity() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery(
                "from Product order by quantity desc", Product.class);
        q.list().forEach(p -> System.out.println(p.getName() + " " + p.getQuantity()));
        session.close();
    }

    static void pagination() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q1 = session.createQuery("from Product", Product.class);
        q1.setFirstResult(0);
        q1.setMaxResults(3);
        System.out.println("First 3 Products:");
        q1.list().forEach(p -> System.out.println(p.getName()));

        Query<Product> q2 = session.createQuery("from Product", Product.class);
        q2.setFirstResult(3);
        q2.setMaxResults(3);
        System.out.println("Next 3 Products:");
        q2.list().forEach(p -> System.out.println(p.getName()));

        session.close();
    }

    static void aggregateQueries() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Long total = session.createQuery(
                "select count(*) from Product", Long.class).uniqueResult();
        System.out.println("Total Products: " + total);

        Long available = session.createQuery(
                "select count(*) from Product where quantity > 0", Long.class).uniqueResult();
        System.out.println("Products with Quantity > 0: " + available);

        List<Object[]> list = session.createQuery(
                "select description, count(*) from Product group by description").list();
        list.forEach(o -> System.out.println(o[0] + " : " + o[1]));

        Object[] price = (Object[]) session.createQuery(
                "select min(price), max(price) from Product").uniqueResult();
        System.out.println("Min Price: " + price[0]);
        System.out.println("Max Price: " + price[1]);

        session.close();
    }

    static void groupByDescription() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> list = session.createQuery(
                "select description, count(*) from Product group by description").list();
        list.forEach(o -> System.out.println(o[0] + " -> " + o[1]));
        session.close();
    }

    static void priceRange() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery(
                "from Product where price between 1000 and 20000", Product.class);
        q.list().forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
        session.close();
    }

    static void likeQueries() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        System.out.println("Starts with 'L'");
        session.createQuery(
                "from Product where name like 'L%'", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        System.out.println("Ends with 'r'");
        session.createQuery(
                "from Product where name like '%r'", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        System.out.println("Contains 'ea'");
        session.createQuery(
                "from Product where name like '%ea%'", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        System.out.println("Name length = 5");
        session.createQuery(
                "from Product where length(name) = 5", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        session.close();
    }
}