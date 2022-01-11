package org.launchcode.jobsearchtracker.Data;

import org.launchcode.jobsearchtracker.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    // https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
    User findByUsername(String username);
    Optional<User> findById(Integer id);
}
