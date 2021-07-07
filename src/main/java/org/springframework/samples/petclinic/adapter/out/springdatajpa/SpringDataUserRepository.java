package org.springframework.samples.petclinic.adapter.out.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.domain.user.User;
import org.springframework.samples.petclinic.domain.user.UserRepository;

@Profile("spring-data-jpa")
public interface SpringDataUserRepository extends UserRepository, Repository<User, Integer>  {

}
