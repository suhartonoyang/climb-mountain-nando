package com.project.nando.climbmountain.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.nando.climbmountain.model.BookingHdr;

@Repository
public interface BookingRepository extends CrudRepository<BookingHdr, Integer> {

	BookingHdr findByBookingNumber(String bookingNumber);

}
