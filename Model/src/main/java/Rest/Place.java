package Rest;

import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Place {
    List<Vehicle> availbleCars;
    List<Vehicle> rentedCars;


    public Place() {
        this.availbleCars = new ArrayList<Vehicle>();
        this.rentedCars = new ArrayList<Vehicle>();
    }

    public void addCar(Vehicle vehicle) {
        availbleCars.add(vehicle);
    }
    public void addCarRented(Vehicle vehicle) {
        rentedCars.add(vehicle);
    }
    public void takeCar(Vehicle vehicle){
        rentedCars.remove(rentedCars.indexOf(vehicle));
        availbleCars.add(vehicle);
    }

    public void handOverCar(Vehicle vehicle){
        availbleCars.remove(availbleCars.indexOf(vehicle));
        rentedCars.add(vehicle);

    }

    public List<Vehicle> getAvailbleCars() {
        return availbleCars;
    }

    public List<Vehicle> getRentedCars() {
        return rentedCars;
    }

    public String getInfoAvailbeCars(){
        String tmp="";
        for (Vehicle vehicle : availbleCars) {
            tmp+=vehicle.getInfoCar();
            tmp+=",";
        }
        return tmp;
    }

    public Vehicle getVehicle(int id) {
        for(Vehicle vehicle : availbleCars){
            if(vehicle.getCarId()==id){
                return vehicle;
            }
        }
        for(Vehicle vehicle : rentedCars){
            if(vehicle.getCarId()==id){
                return vehicle;
            }
        }
        return null;
    }
}
