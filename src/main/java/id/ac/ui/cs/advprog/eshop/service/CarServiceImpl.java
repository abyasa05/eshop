package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl {
    @Autowired
    CarRepository carRepository;
}

@Service
class CarAccessImpl extends CarServiceImpl implements CarAccessService {
    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(String carId) {
        return carRepository.findById(carId);
    }
}

@Service
class CarUpdateImpl extends CarServiceImpl implements CarUpdateService{
    @Override
    public Car create(Car car) {
        // TODO Auto-generated method stub
        carRepository.create(car);
        return car;
    }

    @Override
    public void update(String carId, Car car) {
        // TODO Auto-generated method stub
        carRepository.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId) {
        // TODO Auto-generated method stub
        carRepository.delete(carId);
    }
}
