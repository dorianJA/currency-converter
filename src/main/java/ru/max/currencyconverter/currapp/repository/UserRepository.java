package ru.max.currencyconverter.currapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.max.currencyconverter.currapp.model.entity.User;

/**
 * Created by maxxii on 19.03.2021.
 */
@Component
public interface UserRepository extends JpaRepository<User,Long> {
    User findByLogin(String login);
}
