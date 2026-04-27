// Day 17 - Simple Error Finding Quiz

public class Day17ErrorQuiz {

    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot(3);
        lot.parkCar("AB12CDE");
        lot.parkCar("XY99ZZZ");
        System.out.println(lot.isSpaceAvailable());
        System.out.println(lot.getOccupiedSpaces());

    }

}