//package com.example.foodsearchapp.service;
//
//import com.example.foodsearchapp.repo.Restaurant;
//
//public class AuthorizationService {
//
//    public boolean login(Restaurant restaurant, String restaurantInput) {
//        if (!restaurant.isBlocked()) {
//            boolean result = checkRestaurantPassword(restaurant, restaurantInput);
//            updateRestaurantStatus(restaurant, result);
//            return result;
//        }
//        return false;
//    }
//
//    private void updateRestaurantStatus(Restaurant restaurant, boolean result) {
//        if (result) {
//            restoreAttempts(restaurant);
//        } else {
//            reduceLoginAttempts(restaurant);
//            blockIfLoginAttemptsExceeded(restaurant);
//        }
//    }
//
//    private void blockIfLoginAttemptsExceeded(Restaurant restaurant) {
//        if (restaurant.getLoginAttempts() == 0) {
//            blockRestaurant(restaurant);
//        }
//    }
//
//    private static boolean checkRestaurantPassword(Restaurant restaurant, String restaurantInput) {
//        return restaurant.getPassword().equals(restaurantInput);
//
//    }
//
//    private void reduceLoginAttempts(Restaurant restaurant) {
//        restaurant.setLoginAttempts(restaurant.getLoginAttempts() - 1);
//
//    }
//
//    private void blockRestaurant(Restaurant restaurant) {
//        restaurant.setBlocked(true);
//    }
//
//    private void restoreAttempts(Restaurant restaurant) {
//        restaurant.setLoginAttempts(3);
//    }
//}
