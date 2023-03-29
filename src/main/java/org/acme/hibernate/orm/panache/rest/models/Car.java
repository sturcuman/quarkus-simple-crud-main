package org.acme.hibernate.orm.panache.rest.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String type;
    @Column(nullable = false)
    public String model;
    @Column(nullable = false)
    public String color;

    public Car(){}


    public Car(String type, String model, String color) {
        this.type = type;
        this.model = model;
        this.color = color;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color +
                '}';
    }
}
