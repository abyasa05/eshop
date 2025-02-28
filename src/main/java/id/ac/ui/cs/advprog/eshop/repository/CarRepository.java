package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public interface CarRepository {
    public Car create(Car car);
    public Iterator<Car> findAll();
    public Car findById(String id);
    public void update(String id, Car car);
    public void delete(String id);
}

interface IdGeneration {
    public String generateId();
}

@Component
class IdGenerator implements IdGeneration {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}

@Repository
class CarRepositoryImpl implements CarRepository {
    private final List<Car> carData = new ArrayList<>();

    @Autowired
    private IdGeneration idGenerator;

    @Override
    public Car create(Car car) {
        if (car.getCarId() == null) {
            car.setCarId(idGenerator.generateId());
        }
        carData.add(car);
        return car;
    }

    @Override
    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    @Override
    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public void update(String id, Car updatedCar) {
        for (int i = 0; i < carData.size(); i++) {
            Car car = carData.get(i);
            if (car.getCarId().equals(id)) {
                // Update the existing car with the new information
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
            }
        }
    }

    @Override
    public void delete(String id) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}
