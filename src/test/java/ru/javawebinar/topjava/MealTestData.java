package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MealTestData {

    public static final List<Meal> ADMIN_MEALS = Arrays.asList(
            new Meal(100003,
                    LocalDateTime.of(LocalDate.of(2017, Month.APRIL, 13),
                            LocalTime.of(12, 0)),
                    "Админ обед", 600),
            new Meal(100002,
                    LocalDateTime.of(LocalDate.of(2017, Month.APRIL, 13),
                            LocalTime.of(9, 0)),
                    "Админ завтрак", 300)

    );

    public static final List<Meal> USER_MEALS = Arrays.asList(
            new Meal(100007,
                    LocalDateTime.of(LocalDate.of(2017, Month.APRIL, 13),
                            LocalTime.of(19, 0)),
                    "Юзер ужин", 400),
            new Meal(100006,
                    LocalDateTime.of(LocalDate.of(2017, Month.APRIL, 13),
                            LocalTime.of(13, 0)),
                    "Юзер обед", 550),
            new Meal(100005,
                    LocalDateTime.of(LocalDate.of(2017, Month.APRIL, 13),
                            LocalTime.of(11, 0)),
                    "Юзер полдник", 250),
            new Meal(100004,
                    LocalDateTime.of(LocalDate.of(2017, Month.APRIL, 13),
                            LocalTime.of(9, 0)),
                    "Юзер завтрак", 300)
    );

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual || (
                    Objects.equals(expected.getId(), actual.getId()) &&
                            Objects.equals(expected.getDescription(), actual.getDescription()) &&
                            Objects.equals(expected.getCalories(), actual.getCalories()) &&
                            Objects.equals(expected.getDateTime(), actual.getDateTime())
                    )
    );
}
