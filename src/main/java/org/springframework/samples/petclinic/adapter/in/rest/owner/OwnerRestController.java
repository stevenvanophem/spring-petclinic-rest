/*
 * Copyright 2016-2017 the original author or authors.
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

package org.springframework.samples.petclinic.adapter.in.rest.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.adapter.in.rest.BindingErrorsResponse;
import org.springframework.samples.petclinic.application.clinic.ClinicService;
import org.springframework.samples.petclinic.domain.owner.Owner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/owners")
public class OwnerRestController {

	private final ClinicService clinicService;

	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
    @GetMapping(value = "/*/lastname/{lastName}")
	public ResponseEntity<Collection<Owner>> getOwnersList(@PathVariable("lastName") String ownerLastName) {
		if (ownerLastName == null) {
			ownerLastName = "";
		}
		Collection<Owner> owners = this.clinicService.findOwnerByLastName(ownerLastName);
		if (owners.isEmpty()) {
			return new ResponseEntity<Collection<Owner>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Owner>>(owners, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@GetMapping
	public ResponseEntity<Collection<Owner>> getOwners() {
		Collection<Owner> owners = this.clinicService.findAllOwners();
		if (owners.isEmpty()) {
			return new ResponseEntity<Collection<Owner>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Owner>>(owners, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@GetMapping(value = "/{ownerId}")
	public ResponseEntity<Owner> getOwner(@PathVariable("ownerId") int ownerId) {
		Owner owner = null;
		owner = this.clinicService.findOwnerById(ownerId);
		if (owner == null) {
			return new ResponseEntity<Owner>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Owner>(owner, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@PostMapping
	public ResponseEntity<Owner> addOwner(@RequestBody @Valid Owner owner, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || owner.getId() != null) {
            BindingErrorsResponse errors = new BindingErrorsResponse(owner.getId());
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Owner>(headers, HttpStatus.BAD_REQUEST);
		}
		this.clinicService.saveOwner(owner);
		headers.setLocation(ucBuilder.path("/api/owners/{id}").buildAndExpand(owner.getId()).toUri());
		return new ResponseEntity<Owner>(owner, headers, HttpStatus.CREATED);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@PutMapping(value = "/{ownerId}")
	public ResponseEntity<Owner> updateOwner(@PathVariable("ownerId") int ownerId, @RequestBody @Valid Owner owner,
			BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
	    boolean bodyIdMatchesPathId = owner.getId() == null || ownerId == owner.getId();
		if (bindingResult.hasErrors() || !bodyIdMatchesPathId) {
            BindingErrorsResponse errors = new BindingErrorsResponse(ownerId, owner.getId());
			errors.addAllErrors(bindingResult);
            HttpHeaders headers = new HttpHeaders();
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Owner>(headers, HttpStatus.BAD_REQUEST);
		}
		Owner currentOwner = this.clinicService.findOwnerById(ownerId);
		if (currentOwner == null) {
			return new ResponseEntity<Owner>(HttpStatus.NOT_FOUND);
		}
		currentOwner.setAddress(owner.getAddress());
		currentOwner.setCity(owner.getCity());
		currentOwner.setFirstName(owner.getFirstName());
		currentOwner.setLastName(owner.getLastName());
		currentOwner.setTelephone(owner.getTelephone());
		this.clinicService.saveOwner(currentOwner);
		return new ResponseEntity<Owner>(currentOwner, HttpStatus.NO_CONTENT);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@DeleteMapping(value = "/{ownerId}")
	@Transactional
	public ResponseEntity<Void> deleteOwner(@PathVariable("ownerId") int ownerId) {
		Owner owner = this.clinicService.findOwnerById(ownerId);
		if (owner == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.clinicService.deleteOwner(owner);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
