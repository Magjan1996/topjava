package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            User user = entityManager.getReference(User.class, userId);
            meal.setUser(user);
            entityManager.persist(meal);
        } else {
            if (meal.getUser() == null || meal.getUser().getId() != userId) {
                return null;
            }
            return entityManager.merge(meal);
        }
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return entityManager
                .createNamedQuery(Meal.DeleteByIdAndUserId.NAME)
                .setParameter(Meal.DeleteByIdAndUserId.ID, id)
                .setParameter(Meal.DeleteByIdAndUserId.USER_ID, userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = entityManager
                .createNamedQuery(Meal.FindByIdAndUserId.NAME, Meal.class)
                .setParameter(Meal.FindByIdAndUserId.ID, id)
                .setParameter(Meal.FindByIdAndUserId.USER_ID, userId)
                .getResultList();
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return entityManager
                .createNamedQuery(Meal.FindByUserId.NAME, Meal.class)
                .setParameter(Meal.FindByUserId.USER_ID, userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return entityManager
                .createNamedQuery(Meal.FindByUserIdBetweenDates.NAME, Meal.class)
                .setParameter(Meal.FindByUserIdBetweenDates.USER_ID, userId)
                .setParameter(Meal.FindByUserIdBetweenDates.START_DATE, startDate)
                .setParameter(Meal.FindByUserIdBetweenDates.END_DATE, endDate)
                .getResultList();
    }
}