package Rest;

import vehicle.Vehicle;

import java.util.List;

public class admin {
    private String password;
    private Place place;

    public admin(String password) {
        this.password = password;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }

    public void addCar(Vehicle vehicle) {
        place.addCar(vehicle);
    }

}
