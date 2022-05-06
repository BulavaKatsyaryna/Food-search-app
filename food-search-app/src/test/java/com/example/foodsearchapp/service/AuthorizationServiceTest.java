//package com.example.foodsearchapp.service;
//
//import com.example.foodsearchapp.repo.Restaurant;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class AuthorizationServiceTest {
//
//    AuthorizationService authorizationService;
//    Restaurant restaurant;
//    Restaurant blockedRestaurant;
//    String positiveRestaurantInput = "password";
//    String negativeRestaurantInput = "wrong";
//
//    @BeforeEach
//    public void setUp() {
//        this.authorizationService = new AuthorizationService();
//        this.restaurant = getRestaurant();
//        this.blockedRestaurant = getBlockedRestaurant();
//
//        Assertions.assertEquals(3, restaurant.getLoginAttempts());
//        Assertions.assertFalse(restaurant.isBlocked());
//
//        Assertions.assertEquals(0, blockedRestaurant.getLoginAttempts());
//        Assertions.assertTrue(blockedRestaurant.isBlocked());
//    }
//
//    @Test
//    void checkUserPassword_positive() {
//        boolean actualResult = authorizationService.login(restaurant, positiveRestaurantInput);
//        Assertions.assertTrue(actualResult);
//    }
//
//    @Test
//    void checkUserPassword_negative() {
//        boolean actualResult = authorizationService.login(restaurant, negativeRestaurantInput);
//        Assertions.assertFalse(actualResult);
//
//    }
//
//    @Test
//    void reduceLoginAttempts() {
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        Assertions.assertEquals(2, restaurant.getLoginAttempts());
//    }
//
//    @Test
//    void login_positive() {
//        boolean actualResult = authorizationService.login(restaurant, positiveRestaurantInput);
//        Assertions.assertTrue(actualResult);
//    }
//
//    @Test
//    void login_negative() {
//        boolean actualResult = authorizationService.login(restaurant, negativeRestaurantInput);
//        Assertions.assertFalse(actualResult);
//        Assertions.assertEquals(2, restaurant.getLoginAttempts());
//    }
//
//    @Test
//    void blockedRestaurant() {
//        restaurant.setLoginAttempts(1);
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        Assertions.assertTrue(restaurant.isBlocked());
//    }
//
//    @Test
//    void after3WrongPassword_ShouldBlockUser() {
//
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        Assertions.assertEquals(2, restaurant.getLoginAttempts());
//        Assertions.assertFalse(restaurant.isBlocked());
//
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        Assertions.assertEquals(1, restaurant.getLoginAttempts());
//        Assertions.assertFalse(restaurant.isBlocked());
//
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        Assertions.assertEquals(0, restaurant.getLoginAttempts());
//        Assertions.assertTrue(restaurant.isBlocked());
//
//    }
//
//    @Test
//    void blockRestaurantLogin_ShouldReturnFalse() {
//        boolean actualResult = authorizationService.login(blockedRestaurant, positiveRestaurantInput);
//        Assertions.assertFalse(actualResult);
//    }
//
//    @Test
//    void restoreAttempts() {
//        restaurant.setLoginAttempts(1);
//        authorizationService.login(restaurant, positiveRestaurantInput);
//        Assertions.assertEquals(3, restaurant.getLoginAttempts());
//    }
//
//    @Test
//    void after1Incorrect_shouldRestoreAttempts() {
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        authorizationService.login(restaurant, positiveRestaurantInput);
//        Assertions.assertEquals(3, restaurant.getLoginAttempts());
//    }
//
//    @Test
//    void after3Incorrect_shouldRestoreAttempts() {
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        boolean actualResult = authorizationService.login(restaurant, positiveRestaurantInput);
//        Assertions.assertEquals(0, restaurant.getLoginAttempts());
//        Assertions.assertTrue(restaurant.isBlocked());
//        Assertions.assertFalse(actualResult);
//    }
//
//    @Test
//    void after2Incorrect_shouldRestoreAttempts() {
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        authorizationService.login(restaurant, negativeRestaurantInput);
//        authorizationService.login(restaurant, positiveRestaurantInput);
//        Assertions.assertEquals(3, restaurant.getLoginAttempts());
//    }
//
//
//    private Restaurant getRestaurant() {
//        Restaurant restaurant = new Restaurant();
//        restaurant.setPassword("password");
//        return restaurant;
//    }
//
//    private Restaurant getBlockedRestaurant() {
//        Restaurant restaurant = new Restaurant();
//        restaurant.setPassword("password");
//        restaurant.setLoginAttempts(0);
//        restaurant.setBlocked(true);
//        return restaurant;
//    }
//}