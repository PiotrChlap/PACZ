package Rest;

import Client.Business.BasicBussinessClient;
import Client.Business.PremiumBusinessClient;
import Client.Client;
import Client.Individual.BasicIndClient;
import Client.Individual.DiamondIndClient;
import Client.Individual.SilverIndClient;
import vehicle.Car.*;
import vehicle.Motorcycle.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class DataBaseMenager {
    private DAO dao;
    private Connection conn;

    public DataBaseMenager() {
        dao=new DAO();
        conn = dao.connect();
    }

    public void checkEndedRent(){
        try {
            String ask = "SELECT * FROM rent";
            Statement pst1 = conn.createStatement();
            ResultSet set = pst1.executeQuery(ask);
            String[] tabela = {"truck", "special", "sportPassCar", "premiumPassCar", "familyPassCar", "chopper", "cross_M", "sportMotorcycle", "touristMotorcycle"};
            while (set.next()) {
                if (!set.getBoolean(5)) {
                    if (LocalDate.now().isAfter(LocalDate.parse(set.getString(3)))) {
                        String query = "update rent set closed=true where id_r = " + set.getInt(1);
                        PreparedStatement pst = conn.prepareStatement(query);
                        pst.executeUpdate();

                        String ask1 = "SELECT * FROM  (SELECT id_t as idx FROM special UNION ALL\n" +
                                "SELECT id_t as idx FROM truck\n" +
                                "UNION ALL\n" +
                                "SELECT id_t as idx FROM  sportPassCar\n" +
                                "UNION ALL\n" +
                                "SELECT id_t as idx FROM premiumPassCar\n" +
                                "UNION ALL\n" +
                                "SELECT id_t as idx FROM familyPassCar\n" +
                                "UNION ALL\n" +
                                "SELECT id_t as idx FROM chopper\n" +
                                "UNION ALL\n" +
                                "SELECT id_t as idx FROM cross_M\n" +
                                "UNION ALL\n" +
                                "SELECT id_t as idx FROM sportMotorcycle\n" +
                                "UNION ALL\n" +
                                "SELECT id_t as idx FROM touristMotorcycle) as t";
                        Statement pst3 = conn.createStatement();
                        ResultSet set2 = pst3.executeQuery(ask1);
                        while (set2.next()) {
                            if (set2.getInt(1) == set.getInt(6)) {
                                for (String string : tabela) {
                                    String query1 = "update " + string + " set rented=false where id_t = " + set2.getInt(1);
                                    PreparedStatement pst2 = conn.prepareStatement(query1);
                                    pst2.executeUpdate();
                                }
                                break;
                            }
                        }
                    }
                }
            }
        } catch (SQLException e){
            System.out.println("Error: nieudana aktualizacja danych.."+e.getMessage());
        }
    }

    public admin getAdminBase(String admin_pass){
        try {
            String ask = "SELECT pass_admin FROM admin";
            Statement pst1 = conn.createStatement();
            ResultSet set = pst1.executeQuery(ask);
            String base_haslo = "";
            while (set.next()) {
                base_haslo = set.getString(1);
            }
            if (admin_pass.equals(base_haslo)) {
                return new admin(admin_pass);
            }
        }catch (SQLException e){
            System.out.println("Error: błąd odczytu danych z bazy danych..."+e.getMessage());
        }
        return null;
    }

    public Client getClientDataBase(String login, String pass){
        try{
            String ask = "SELECT * FROM client";
            Statement pst1 = conn.createStatement();
            ResultSet set = pst1.executeQuery(ask);
            String base_login;
            String base_haslo;
            while(set.next()){
                base_haslo = set.getString(3);
                base_login = set.getString(4);
                if(base_haslo.equals(pass) && base_login.equals(login)){
                    if(set.getInt(12)==1){
                        BasicBussinessClient basicBussinessClient = new BasicBussinessClient(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),
                                set.getString(6),set.getString(7),set.getString(8));
                        basicBussinessClient.addLoyaltyPoints(set.getInt(13));
                        return  basicBussinessClient;
                    } else if(set.getInt(12)==2){
                        PremiumBusinessClient premiumBusinessClient = new PremiumBusinessClient(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),
                                set.getString(6),set.getString(7),set.getString(8));
                        premiumBusinessClient.setLoyaltyPoints(set.getInt(13));
                        return premiumBusinessClient;
                    } else if(set.getInt(12)==3){
                        BasicIndClient basicIndClient = new BasicIndClient(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(9),set.getString(10),
                                set.getString(11));
                        basicIndClient.setLoyaltyPoints(set.getInt(13));
                        return basicIndClient;
                    } else if(set.getInt(12)==4){
                        SilverIndClient silverIndClient = new SilverIndClient(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(9),set.getString(10),
                                set.getString(11));
                        silverIndClient.setLoyaltyPoints(set.getInt(13));
                        return silverIndClient;
                    } else {
                        DiamondIndClient diamondIndClient = new DiamondIndClient(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(9),set.getString(10),
                                set.getString(11));
                        diamondIndClient.setLoyaltyPoints(set.getInt(13));
                        return diamondIndClient;
                    }

                }
            }
        }catch (SQLException e){
            System.out.println("Error: błąd odczytu danych z bazy danych..."+e.getMessage());
        }
        return null;
    }

    public void updatePlaceDataBase(Place place){
        try {
            String[] tabela = {"truck", "special", "sportPassCar", "premiumPassCar", "familyPassCar", "chopper", "cross_M", "sportMotorcycle", "touristMotorcycle"};
            int[] tabela2 = {10, 10, 13, 13, 13, 10, 10, 10, 10};
            place.getAvailbleCars().clear();
            place.getRentedCars().clear();
            for (int i = 0; i < 9; i++) {
                String ask = "SELECT * FROM " + tabela[i];
                Statement pst1 = conn.createStatement();
                ResultSet set = pst1.executeQuery(ask);
                int j = 0;
                while (set.next()) {
                    String[] tmp = new String[tabela2[i] + 1];
                    for (int z = 0; z < tabela2[i] + 1; z++) {
                        tmp[z] = set.getString(z + 1);
                    }
                    if (!set.getBoolean(tmp.length)) {
                        if (tabela[i].equals("truck")) {
                            place.addCar(new Truck(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    Integer.parseInt(tmp[8]), Integer.parseInt(tmp[9])));
                        } else if (tabela[i].equals("special")) {
                            place.addCar(new Special(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    Integer.parseInt(tmp[8]), tmp[9]));
                        } else if (tabela[i].equals("sportPassCar")) {
                            place.addCar(new SportPassCar(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    Integer.parseInt(tmp[8]), Integer.parseInt(tmp[9]), Boolean.parseBoolean(tmp[10]), Boolean.parseBoolean(tmp[11]), Integer.parseInt(tmp[12])));
                        } else if (tabela[i].equals("premiumPassCar")) {
                            place.addCar(new PremiumPassCar(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    Integer.parseInt(tmp[8]), Integer.parseInt(tmp[9]), Boolean.parseBoolean(tmp[10]), Boolean.parseBoolean(tmp[11]), Boolean.parseBoolean(tmp[12])));
                        } else if (tabela[i].equals("familyPassCar")) {
                            place.addCar(new FamilyPassCar(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    Integer.parseInt(tmp[8]), Integer.parseInt(tmp[9]), typeFamilyCar.valueOf(tmp[10]), Integer.parseInt(tmp[11]), Boolean.parseBoolean(tmp[12])));
                        } else if (tabela[i].equals("chopper")) {
                            place.addCar(new Chopper(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    driveTypeMotorcycle.valueOf(tmp[8]), Float.parseFloat(tmp[9])));
                        } else if (tabela[i].equals("cross_M")) {
                            place.addCar(new Cross(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    driveTypeMotorcycle.valueOf(tmp[8]), Integer.parseInt(tmp[9])));
                        } else if (tabela[i].equals("sportMotorcycle")) {
                            place.addCar(new SportMotorcycle(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    driveTypeMotorcycle.valueOf(tmp[8]), Integer.parseInt(tmp[9])));
                        } else {
                            place.addCar(new TouristMotorcycle(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    driveTypeMotorcycle.valueOf(tmp[8]), Integer.parseInt(tmp[9])));
                        }
                    } else {
                        if (tabela[i].equals("truck")) {
                            place.addCarRented(new Truck(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    Integer.parseInt(tmp[8]), Integer.parseInt(tmp[9])));
                        } else if (tabela[i].equals("special")) {
                            place.addCarRented(new Special(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    Integer.parseInt(tmp[8]), tmp[9]));
                        } else if (tabela[i].equals("sportPassCar")) {
                            place.addCarRented(new SportPassCar(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    Integer.parseInt(tmp[8]), Integer.parseInt(tmp[9]), Boolean.parseBoolean(tmp[10]), Boolean.parseBoolean(tmp[11]), Integer.parseInt(tmp[12])));
                        } else if (tabela[i].equals("premiumPassCar")) {
                            place.addCarRented(new PremiumPassCar(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    Integer.parseInt(tmp[8]), Integer.parseInt(tmp[9]), Boolean.parseBoolean(tmp[10]), Boolean.parseBoolean(tmp[11]), Boolean.parseBoolean(tmp[12])));
                        } else if (tabela[i].equals("familyPassCar")) {
                            place.addCarRented(new FamilyPassCar(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    Integer.parseInt(tmp[8]), Integer.parseInt(tmp[9]), typeFamilyCar.valueOf(tmp[10]), Integer.parseInt(tmp[11]), Boolean.parseBoolean(tmp[12])));
                        } else if (tabela[i].equals("chopper")) {
                            place.addCarRented(new Chopper(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    driveTypeMotorcycle.valueOf(tmp[8]), Float.parseFloat(tmp[9])));
                        } else if (tabela[i].equals("cross_M")) {
                            place.addCarRented(new Cross(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    driveTypeMotorcycle.valueOf(tmp[8]), Integer.parseInt(tmp[9])));
                        } else if (tabela[i].equals("sportMotorcycle")) {
                            place.addCarRented(new SportMotorcycle(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    driveTypeMotorcycle.valueOf(tmp[8]), Integer.parseInt(tmp[9])));
                        } else {
                            place.addCarRented(new TouristMotorcycle(Integer.parseInt(tmp[0]), tmp[1], tmp[2], Float.parseFloat(tmp[3]), Integer.parseInt(tmp[4]), Boolean.parseBoolean(tmp[5]), Integer.parseInt(tmp[6]), Integer.parseInt(tmp[7]),
                                    driveTypeMotorcycle.valueOf(tmp[8]), Integer.parseInt(tmp[9])));
                        }
                    }

                }
            }
        } catch (SQLException e){
            System.out.println("Error: błąd odczytu danych z bazy danych..."+e.getMessage());
        }
    }

    public int getHighestIDVehicle(){
        int data = 0;
        try {
            String ask ="SELECT max(idx) FROM  (SELECT id_t as idx FROM special UNION ALL\n" +
                    "SELECT id_t as idx FROM truck\n" +
                    "UNION ALL\n" +
                    "SELECT id_t as idx FROM  sportPassCar\n" +
                    "UNION ALL\n" +
                    "SELECT id_t as idx FROM premiumPassCar\n" +
                    "UNION ALL\n" +
                    "SELECT id_t as idx FROM familyPassCar\n" +
                    "UNION ALL\n" +
                    "SELECT id_t as idx FROM chopper\n" +
                    "UNION ALL\n" +
                    "SELECT id_t as idx FROM cross_M\n" +
                    "UNION ALL\n" +
                    "SELECT id_t as idx FROM sportMotorcycle\n" +
                    "UNION ALL\n" +
                    "SELECT id_t as idx FROM touristMotorcycle) as t";
            Statement pst1 = conn.createStatement();
            ResultSet set = pst1.executeQuery(ask);
            while (set.next()) {
                data = set.getInt(1);
            }
        } catch (SQLException e){

        }
        return data;
    }

    public void addNewSpecial(List<String> arg){
        try {
            boolean tmp;
            if(arg.get(4).equals("Tak")){
                tmp=true;
            } else {
                tmp=false;
            }
            String query = "INSERT INTO special values(?,?,?,?,?,?, ?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, getHighestIDVehicle() + 1);
            pst.setString(2, arg.get(0));
            pst.setString(3,  arg.get(1));
            pst.setFloat(4, Float.parseFloat( arg.get(2)));
            pst.setInt(5, Integer.parseInt( arg.get(3)));
            pst.setBoolean(6, tmp);
            pst.setInt(7, Integer.parseInt(arg.get(5)));
            pst.setInt(8, Integer.parseInt(arg.get(6)));
            pst.setInt(9,Integer.parseInt(arg.get(7)));
            pst.setString(10,arg.get(8));
            pst.executeUpdate();
        } catch (SQLException e){

        }
    }

    public void addNewChopper(List<String> arg){
        try {
            boolean tmp;
            if(arg.get(4).equals("Tak")){
                tmp=true;
            } else {
                tmp=false;
            }
            String query = "INSERT INTO chopper values(?,?,?,?,?,?, ?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, getHighestIDVehicle() + 1);
            pst.setString(2, arg.get(0));
            pst.setString(3,  arg.get(1));
            pst.setFloat(4, Float.parseFloat( arg.get(2)));
            pst.setInt(5, Integer.parseInt( arg.get(3)));
            pst.setBoolean(6, tmp);
            pst.setInt(7, Integer.parseInt(arg.get(5)));
            pst.setInt(8, Integer.parseInt(arg.get(6)));
            pst.setString(9,arg.get(7));
            pst.setInt(10,Integer.parseInt(arg.get(8)));
            pst.executeUpdate();
        } catch (SQLException e){

        }
    }

    public void addNewTruck(List<String> arg){
        try {
            boolean tmp;
            if(arg.get(4).equals("Tak")){
                tmp=true;
            } else {
                tmp=false;
            }
            String query = "INSERT INTO truck values(?,?,?,?,?,?, ?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, getHighestIDVehicle() + 1);
            pst.setString(2, arg.get(0));
            pst.setString(3,  arg.get(1));
            pst.setFloat(4, Float.parseFloat( arg.get(2)));
            pst.setInt(5, Integer.parseInt( arg.get(3)));
            pst.setBoolean(6, tmp);
            pst.setInt(7, Integer.parseInt(arg.get(5)));
            pst.setInt(8, Integer.parseInt(arg.get(6)));
            pst.setInt(9,Integer.parseInt(arg.get(7)));
            pst.setInt(10,Integer.parseInt(arg.get(8)));
            pst.executeUpdate();
        } catch (SQLException e){

        }
    }

    public void addNewCross(List<String> arg){
        try {
            boolean tmp;
            if(arg.get(4).equals("Tak")){
                tmp=true;
            } else {
                tmp=false;
            }
            String query = "INSERT INTO cross_M values(?,?,?,?,?,?, ?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, getHighestIDVehicle() + 1);
            pst.setString(2, arg.get(0));
            pst.setString(3,  arg.get(1));
            pst.setFloat(4, Float.parseFloat( arg.get(2)));
            pst.setInt(5, Integer.parseInt( arg.get(3)));
            pst.setBoolean(6, tmp);
            pst.setInt(7, Integer.parseInt(arg.get(5)));
            pst.setInt(8, Integer.parseInt(arg.get(6)));
            pst.setString(9,arg.get(7));
            pst.setInt(10,Integer.parseInt(arg.get(8)));
            pst.executeUpdate();
        } catch (SQLException e){

        }
    }

    public void addNewPrem(List<String> arg){
        try {
            boolean tmp;
            boolean a;
            boolean d;
            boolean b;
            if(arg.get(4).equals("Tak")){
                tmp=true;
            } else {
                tmp=false;
            }
            if(arg.get(9).equals("Tak")){
                a=true;
            } else {
                a=false;
            }
            if(arg.get(10).equals("Tak")){
                d=true;
            } else {
                d=false;
            }
            if(arg.get(11).equals("Tak")){
                b=true;
            } else {
                b=false;
            }
            String query = "INSERT INTO premiumPassCar values(?,?,?,?,?,?, ?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, getHighestIDVehicle() + 1);
            pst.setString(2, arg.get(0));
            pst.setString(3,  arg.get(1));
            pst.setFloat(4, Float.parseFloat( arg.get(2)));
            pst.setInt(5, Integer.parseInt( arg.get(3)));
            pst.setBoolean(6, tmp);
            pst.setInt(7, Integer.parseInt(arg.get(5)));
            pst.setInt(8, Integer.parseInt(arg.get(6)));
            pst.setInt(9,Integer.parseInt(arg.get(7)));
            pst.setInt(10,Integer.parseInt(arg.get(8)));
            pst.setBoolean(11,a);
            pst.setBoolean(12,d);
            pst.setBoolean(13,b);
            pst.executeUpdate();
        } catch (SQLException e){

        }
    }

    public void addNewTouristic(List<String> arg){
        try {
            boolean tmp;
            if(arg.get(4).equals("Tak")){
                tmp=true;
            } else {
                tmp=false;
            }
            String query = "INSERT INTO touristMotorcycle values(?,?,?,?,?,?, ?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, getHighestIDVehicle() + 1);
            pst.setString(2, arg.get(0));
            pst.setString(3,  arg.get(1));
            pst.setFloat(4, Float.parseFloat( arg.get(2)));
            pst.setInt(5, Integer.parseInt( arg.get(3)));
            pst.setBoolean(6, tmp);
            pst.setInt(7, Integer.parseInt(arg.get(5)));
            pst.setInt(8, Integer.parseInt(arg.get(6)));
            pst.setString(9,arg.get(7));
            pst.setInt(10,Integer.parseInt(arg.get(8)));
            pst.executeUpdate();
        } catch (SQLException e){

        }
    }

}
