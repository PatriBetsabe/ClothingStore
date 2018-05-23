package com.lamadrid.store.persistence;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.Dress;

interface HelperDressRepository extends CrudRepository<Dress, Integer> {

}
