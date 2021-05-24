package Rest;

import vehicle.Vehicle;

public class Controller {
    private final Place place;

    public Controller(Place place) {
        this.place = place;
    }

    public Vehicle findCarAvailble(int id){
        for (Vehicle vehicle : place.getAvailbleCars()) {
            if(vehicle.getCarId() == id){
                return vehicle;
            }
        }

        return null;
    }

    public Vehicle findCarRented(int id){
        for (Vehicle vehicle : place.getRentedCars()) {
            if(vehicle.getCarId() == id){
                return vehicle;
            }
        }

        return null;
    }
    public Vehicle handOverCar(int Id){
        Vehicle vehicle= findCarAvailble(Id);
        if(vehicle ==null) {
            return null;
        }
        place.handOverCar(vehicle);
        return vehicle;
    }

    public void takeCar(int Id){
        Vehicle vehicle= findCarRented(Id);
        place.takeCar(vehicle);
    }

    public String getInfoAvailbeCar() {
        return place.getInfoAvailbeCars();
    }

 }
