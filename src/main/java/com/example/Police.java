// package com.example;

// import java.util.List;
// import java.util.stream.Collectors;

// public class Police {

//     public static List<String> getParkedWhiteCarLocations(List<ParkingLot> parkingLots) {
//         return parkingLots.stream()
//                 .flatMap(parkingLot -> parkingLot.getLocationsOfParkedWhiteCars().stream())
//                 .collect(Collectors.toList());
//     }
// }