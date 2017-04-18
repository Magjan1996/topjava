package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;

/**
 * Created by Tolegen Izbassar on 18.04.2017.
 */
@ContextConfiguration({"classpath:spring/spring-app.xml",
                        "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(MealService.class);

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() {
        dbPopulator.execute();
    }

    @Test
    public void getAllAdminMealTest() {
        MealTestData.MATCHER.assertCollectionEquals(
                MealTestData.ADMIN_MEALS, mealService.getAll(UserTestData.ADMIN_ID)
        );
    }

    @Test
    public void getAllUserMealTest() {
        MealTestData.MATCHER.assertCollectionEquals(
                MealTestData.USER_MEALS, mealService.getAll(UserTestData.USER_ID)
        );
    }

    @Test(expected = NotFoundException.class)
    public void tryGetAnotherUsersMeal() {
        mealService.get(MealTestData.ADMIN_MEALS.get(0).getId(), UserTestData.USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void tryUpdateAnotherUsersMeal() {
        Meal meal = mealService.get(MealTestData.ADMIN_MEALS.get(1).getId(), UserTestData.ADMIN_ID);
        logger.debug(meal.toString());
        meal.setDateTime(LocalDateTime.now());
        mealService.update(meal, UserTestData.USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void tryDeleteAnotherUsersMeal() {
        mealService.delete(MealTestData.ADMIN_MEALS.get(0).getId(), UserTestData.USER_ID);
    }
}