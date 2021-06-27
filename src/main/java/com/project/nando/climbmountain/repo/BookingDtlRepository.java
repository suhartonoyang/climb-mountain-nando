package com.project.nando.climbmountain.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.nando.climbmountain.model.BookingDtl;
import com.project.nando.climbmountain.model.BookingHdr;

@Repository
public interface BookingDtlRepository extends CrudRepository<BookingDtl, Integer> {

}
