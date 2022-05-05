package com.example.foodsearchapp.service;

import com.example.foodsearchapp.repo.Restaurant;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthorizationServiceTest {

    AuthorizationService authorizationService;
    Restaurant restaurant;
    Restaurant blockedRestaurant;
    String positiveRestaurantInput = "password";
    String negativeRestaurantInput = "wrong";

    @BeforeEach
    public void setUp() {
        this.authorizationService = new AuthorizationService();
        this.restaurant = getRestaurant();
        this.blockedRestaurant = getBlockedRestaurant();

        Assert.assertEquals(3, restaurant.getLoginAttempts());
        Assert.assertFalse(restaurant.isBlocked());

        Assert.assertEquals(0, blockedRestaurant.getLoginAttempts());
        Assert.assertTrue(blockedRestaurant.isBlocked());
    }

    @Test
    public void checkUserPassword_positive() {
        boolean actualResult = authorizationService.login(restaurant, positiveRestaurantInput);
        Assert.assertTrue(actualResult);
    }

    @Test
    public void checkUserPassword_negative() {
        boolean actualResult = authorizationService.login(restaurant, negativeRestaurantInput);
        Assert.assertFalse(actualResult);

    }

    @Test
    public void reduceLoginAttempts() {
        authorizationService.login(restaurant, negativeRestaurantInput);
        Assert.assertEquals(2, restaurant.getLoginAttempts());
    }

    @Test
    public void login_positive() {
        boolean actualResult = authorizationService.login(restaurant, positiveRestaurantInput);
        Assert.assertTrue(actualResult);
    }

    @Test
    public void login_negative() {
        boolean actualResult = authorizationService.login(restaurant, negativeRestaurantInput);
        Assert.assertFalse(actualResult);
        Assert.assertEquals(2, restaurant.getLoginAttempts());
    }

    @Test
    public void blockedRestaurant() {
        restaurant.setLoginAttempts(1);
        authorizationService.login(restaurant, negativeRestaurantInput);
        Assert.assertTrue(restaurant.isBlocked());
    }

    @Test
    public void after3WrongPassword_ShouldBlockUser() {

        authorizationService.login(restaurant, negativeRestaurantInput);
        Assert.assertEquals(2, restaurant.getLoginAttempts());
        Assert.assertFalse(restaurant.isBlocked());

        authorizationService.login(restaurant, negativeRestaurantInput);
        Assert.assertEquals(1, restaurant.getLoginAttempts());
        Assert.assertFalse(restaurant.isBlocked());

        authorizationService.login(restaurant, negativeRestaurantInput);
        Assert.assertEquals(0, restaurant.getLoginAttempts());
        Assert.assertTrue(restaurant.isBlocked());

    }

    @Test
    public void blockRestaurantLogin_ShouldReturnFalse() {
        boolean actualResult = authorizationService.login(blockedRestaurant, positiveRestaurantInput);
        Assert.assertFalse(actualResult);
    }

    @Test
    public void restoreAttempts() {
        restaurant.setLoginAttempts(1);
        authorizationService.login(restaurant, positiveRestaurantInput);
        Assert.assertEquals(3, restaurant.getLoginAttempts());
    }

    @Test
    public void after1Incorrect_shouldRestoreAttempts() {
        authorizationService.login(restaurant, negativeRestaurantInput);
        authorizationService.login(restaurant, positiveRestaurantInput);
        Assert.assertEquals(3, restaurant.getLoginAttempts());
    }

    @Test
    public void after3Incorrect_shouldRestoreAttempts() {
        authorizationService.login(restaurant, negativeRestaurantInput);
        authorizationService.login(restaurant, negativeRestaurantInput);
        authorizationService.login(restaurant, negativeRestaurantInput);
        boolean actualResult = authorizationService.login(restaurant, positiveRestaurantInput);
        Assert.assertEquals(0, restaurant.getLoginAttempts());
        Assert.assertTrue(restaurant.isBlocked());
        Assert.assertFalse(actualResult);
    }

    @Test
    public void after2Incorrect_shouldRestoreAttempts() {
        authorizationService.login(restaurant, negativeRestaurantInput);
        authorizationService.login(restaurant, negativeRestaurantInput);
        authorizationService.login(restaurant, positiveRestaurantInput);
        Assert.assertEquals(3, restaurant.getLoginAttempts());
    }


    private Restaurant getRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setPassword("password");
        return restaurant;
    }

    private Restaurant getBlockedRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setPassword("password");
        restaurant.setLoginAttempts(0);
        restaurant.setBlocked(true);
        return restaurant;
    }

}