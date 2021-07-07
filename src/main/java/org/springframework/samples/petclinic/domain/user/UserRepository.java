package org.springframework.samples.petclinic.domain.user;

import org.springframework.dao.DataAccessException;

public interface UserRepository {

    void save(User user) throws DataAccessException;
}
