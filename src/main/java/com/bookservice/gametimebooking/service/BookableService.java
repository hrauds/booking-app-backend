package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.exceptions.UserException;
import com.bookservice.gametimebooking.model.Bookable;
import com.bookservice.gametimebooking.model.Resource;
import com.bookservice.gametimebooking.repository.BookableRepository;
import com.bookservice.gametimebooking.repository.ResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@AllArgsConstructor
@Transactional
public class BookableService {

    private final BookableRepository bookableRepository;
    private final ResourceRepository resourceRepository;

    public void addBookableForResource(Long resourceId, String date, String startTime, String endTime) {

        Bookable bookable = new Bookable();

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new UserException("Provided resource id is wrong", HttpStatus.NOT_FOUND));

        LocalDate bookableDate = LocalDate.parse(date);
        if (bookableRepository.existsByResourceAndDate(resource, bookableDate)) {
            throw new UserException("Bookable time corresponding to provided date exists", HttpStatus.CONFLICT);
        }
        bookable.setDate(bookableDate);

        if (LocalTime.parse(startTime).isAfter(LocalTime.parse(endTime))) {
            throw new UserException("Start time should be before end time", HttpStatus.BAD_REQUEST);
        }
        bookable.setStartTime(LocalTime.parse(startTime));
        bookable.setEndTime(LocalTime.parse(endTime));

        resource.addBookable(bookable);
        bookable.setResource(resource);

        bookableRepository.save(bookable);
    }
}
