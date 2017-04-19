package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Meal.FindByIdAndUserId.NAME,
                    query = Meal.FindByIdAndUserId.QUERY),
        @NamedQuery(name = Meal.FindByUserId.NAME,
                    query = Meal.FindByUserId.QUERY),
        @NamedQuery(name = Meal.FindByUserIdBetweenDates.NAME,
                    query = Meal.FindByUserIdBetweenDates.QUERY),
        @NamedQuery(name = Meal.DeleteByIdAndUserId.NAME,
                    query = Meal.DeleteByIdAndUserId.QUERY)
})
@Table(name = "MEALS", uniqueConstraints = {
        @UniqueConstraint(name = "meals_unique_user_datetime_idx",
                            columnNames = {"user_id", "date_time"})
})
public class Meal extends BaseEntity {

    public static final class DeleteByIdAndUserId {
        public static final String NAME = "Meal.DeleteByIdAndUserId";
        public static final String ID = "id";
        public static final String USER_ID = "userId";
        static final String QUERY = "DELETE FROM Meal meal" +
                " WHERE meal.id = :" + ID +
                " AND meal.user.id = :" + USER_ID;
    }

    public static final class FindByUserIdBetweenDates {
        public static final String NAME = "Meal.FindByUserIdBetweenDates";
        public static final String START_DATE = "startDate";
        public static final String END_DATE = "endDate";
        public static final String USER_ID = "userId";
        static final String QUERY = "SELECT meal FROM Meal meal" +
                " WHERE meal.user.id = :" + USER_ID +
                " AND meal.dateTime between :" + START_DATE + "" +
                " AND :" + END_DATE +
                " ORDER BY meal.dateTime DESC";
    }

    public static final class FindByUserId {
        public static final String NAME = "Meal.FindByUserId";
        public static final String USER_ID = "userId";
        static final String QUERY = "SELECT meal FROM Meal meal" +
                " WHERE meal.user.id = :" + USER_ID +
                " ORDER BY meal.dateTime DESC";
    }

    public static final class FindByIdAndUserId {
        public static final String NAME = "Meal.FindByIdAndUserId";
        public static final String ID = "id";
        public static final String USER_ID = "userId";
        static final String QUERY = "SELECT meal FROM Meal meal" +
                " WHERE meal.id = :" + ID + "" +
                " AND meal.user.id = :" + USER_ID;
    }

    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
