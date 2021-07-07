package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.domain.user.User;

public interface UserRepository {

    void save(User user) throws DataAccessException;
}
