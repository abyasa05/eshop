package id.ac.ui.cs.advprog.eshop.repository;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

interface IdGeneration {
    public String generateId();
}

interface UpdateCarRepository {
    public void update(String id, Car car);
    public void delete(String id);
}

@Repository
public class CarRepository implements UpdateCarRepository {
    private List<Car> carData = new ArrayList<>();
    private IdGenerator idGenerator = new IdGenerator();

    public Car create(Car car) {
        if (car.getCarId() == null) {
            car.setCarId(idGenerator.generateId());
        }
        carData.add(car);
        return car;
    }

    public Iterator<Car> findAll() {
        return carData.iterator();
    }

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

class IdGenerator implements IdGeneration {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
