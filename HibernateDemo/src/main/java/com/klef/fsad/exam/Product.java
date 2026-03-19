package com.klef.fsad.exam;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "price")
    private double price;

    // ── Getters ──────────────────────────────
    public int    getId()          { return id; }
    public String getName()        { return name; }
    public String getDescription() { return description; }
    public Date   getDate()         { return date; }
    public String getStatus()      { return status; }
    public double getPrice()       { return price; }

    // ── Setters ──────────────────────────────
    public void setId(int id)                    { this.id = id; }
    public void setName(String name)              { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(Date date)                { this.date = date; }
    public void setStatus(String status)          { this.status = status; }
    public void setPrice(double price)            { this.price = price; }
}