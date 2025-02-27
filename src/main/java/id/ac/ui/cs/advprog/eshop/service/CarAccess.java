package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarAccess {
    public List<Car> findAll();
    public Car findById(String carId);
}

interface CarUpdate {
    public Car create(Car car);
    public void update(String carId, Car car);
    public void deleteCarById(String carId);
}
