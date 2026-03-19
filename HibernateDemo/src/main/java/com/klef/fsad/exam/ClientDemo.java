package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.Date;

public class ClientDemo {

  public static void main(String[] args) {

    // Build SessionFactory from hibernate.cfg.xml
    SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Product.class)
        .buildSessionFactory();

    Session session = factory.openSession();
    Transaction tx = null;

    try {

      // ══════════════════════════════════
      // OPERATION I — INSERT NEW PRODUCT
      // ══════════════════════════════════
      tx = session.beginTransaction();

      Product p = new Product();
      p.setName("Laptop");
      p.setDescription("High performance laptop");
      p.setDate(new Date());
      p.setStatus("Available");
      p.setPrice(75000.00);

      session.save(p);
      tx.commit();

      System.out.println("✅ INSERT OK — ID: " + p.getId());

      // ══════════════════════════════════
      // OPERATION II — UPDATE BY ID
      // ══════════════════════════════════
      tx = session.beginTransaction();

      Product found = session.get(Product.class, p.getId());

      if (found != null) {
        found.setName("Gaming Laptop");   // update Name
        found.setStatus("Sold Out");      // update Status
        session.update(found);
        tx.commit();
        System.out.println("✅ UPDATE OK");
      }

    } catch (Exception e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
      factory.close();
    }
  }
}