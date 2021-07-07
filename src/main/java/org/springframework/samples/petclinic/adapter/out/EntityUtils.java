/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.adapter.out;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.domain.owner.Owner;
import org.springframework.samples.petclinic.domain.pet.Pet;
import org.springframework.samples.petclinic.domain.pettype.PetType;
import org.springframework.samples.petclinic.domain.speciality.Specialty;
import org.springframework.samples.petclinic.domain.vet.Vet;
import org.springframework.samples.petclinic.domain.visit.Visit;

import java.util.Collection;


public abstract class EntityUtils {

    public static Owner getOwnerById(Collection<Owner> entities, Class<Owner> entityClass, int entityId)
        throws ObjectRetrievalFailureException {
        for (Owner entity : entities) {
            if (entity.getId() == entityId && entityClass.isInstance(entity)) {
                return entity;
            }
        }
        throw new ObjectRetrievalFailureException(entityClass, entityId);
    }

    public static Visit getVisitById(Collection<Visit> entities, Class<Visit> entityClass, int entityId)
        throws ObjectRetrievalFailureException {
        for (Visit entity : entities) {
            if (entity.getId() == entityId && entityClass.isInstance(entity)) {
                return entity;
            }
        }
        throw new ObjectRetrievalFailureException(entityClass, entityId);
    }

    public static Vet getVetById(Collection<Vet> entities, Class<Vet> entityClass, int entityId)
        throws ObjectRetrievalFailureException {
        for (Vet entity : entities) {
            if (entity.getId() == entityId && entityClass.isInstance(entity)) {
                return entity;
            }
        }
        throw new ObjectRetrievalFailureException(entityClass, entityId);
    }

    public static Pet getPetById(Collection<Pet> entities, Class<Pet> entityClass, int entityId)
        throws ObjectRetrievalFailureException {
        for (Pet entity : entities) {
            if (entity.getId() == entityId && entityClass.isInstance(entity)) {
                return entity;
            }
        }
        throw new ObjectRetrievalFailureException(entityClass, entityId);
    }

    public static PetType getPetTypeById(Collection<PetType> entities, Class<PetType> entityClass, int entityId)
        throws ObjectRetrievalFailureException {
        for (PetType entity : entities) {
            if (entity.getId() == entityId && entityClass.isInstance(entity)) {
                return entity;
            }
        }
        throw new ObjectRetrievalFailureException(entityClass, entityId);
    }

    public static Specialty getSpecialtyById(Collection<Specialty> entities, Class<Specialty> entityClass, int entityId)
        throws ObjectRetrievalFailureException {
        for (Specialty entity : entities) {
            if (entity.getId() == entityId && entityClass.isInstance(entity)) {
                return entity;
            }
        }
        throw new ObjectRetrievalFailureException(entityClass, entityId);
    }

}
