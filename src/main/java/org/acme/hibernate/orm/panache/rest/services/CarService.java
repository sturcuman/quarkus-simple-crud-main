package org.acme.hibernate.orm.panache.rest.services;

import org.acme.hibernate.orm.panache.rest.exceptions.RestApplicationException;
import org.acme.hibernate.orm.panache.rest.models.Car;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CarService {

    public static final String ERROR_TEMPLATE = "Car with id: %s not found!";

    public void create(Car car) {
        System.out.println("Create car with name: " + car.type);
        car.persist();
    }

    public Car getById(Long id) throws RestApplicationException {
        Car car = Car.findById(id);
        if(car == null) {
            throw new RestApplicationException(String.format(ERROR_TEMPLATE, id));
        }
        return car;
    }

    public List<Car> getAll() {
        return Car.listAll();
    }

    public void updateById(Car car, Long id) throws RestApplicationException {
        Car oldCar = Car.findById(id);
        if(oldCar == null) {
            throw new RestApplicationException(String.format(ERROR_TEMPLATE, id));
        }
        oldCar.type = car.type;
        oldCar.model = car.model;
        oldCar.color = car.color;
    }

    public void deleteById(Long id) throws RestApplicationException {
        Car oldCar = Car.findById(id);
        if(oldCar == null) {
            throw new RestApplicationException(String.format(ERROR_TEMPLATE, id));
        }
        oldCar.delete();
    }

}
