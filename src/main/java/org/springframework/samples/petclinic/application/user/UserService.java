package org.springframework.samples.petclinic.application.user;

import org.springframework.samples.petclinic.domain.user.User;

public interface UserService {

    void saveUser(User user) throws Exception;
}
