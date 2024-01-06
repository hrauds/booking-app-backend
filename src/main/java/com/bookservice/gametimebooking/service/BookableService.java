package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.BookableDto;
import com.bookservice.gametimebooking.exceptions.UserException;
import com.bookservice.gametimebooking.mapper.BookableMapper;
import com.bookservice.gametimebooking.model.Bookable;
import com.bookservice.gametimebooking.model.Resource;
import com.bookservice.gametimebooking.repository.BookableRepository;
import com.bookservice.gametimebooking.repository.ResourceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class BookableService {

    private final BookableRepository bookableRepository;
    private final ResourceRepository resourceRepository;
    private BookableMapper bookableMapper;

    public void addBookableForResource(Long resourceId, String date, String startTime, String endTime) {

        Bookable bookable = new Bookable();

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new UserException("Provided resource id is wrong", HttpStatus.NOT_FOUND));

        LocalDate bookableDate = LocalDate.parse(date);
        if (bookableRepository.existsByResourceAndDate(resource, bookableDate)) {
            log.error("Bookable time corresponding to provided date already exists");
            throw new UserException("Bookable time corresponding to provided date exists", HttpStatus.CONFLICT);
        }
        bookable.setDate(bookableDate);

        if (LocalTime.parse(startTime).isAfter(LocalTime.parse(endTime))) {
            log.error("Start time was not before end time");
            throw new UserException("Start time should be before end time", HttpStatus.BAD_REQUEST);
        }
        bookable.setStartTime(LocalTime.parse(startTime));
        bookable.setEndTime(LocalTime.parse(endTime));

        resource.addBookable(bookable);
        bookable.setResource(resource);

        log.info("Bookable was saved");
        bookableRepository.save(bookable);
    }

    public List<BookableDto> getBookablesForServiceAndDate(Long serviceId, String date) {
        List<Resource> resources = resourceRepository.findAllByServiceId(serviceId);

        List<BookableDto> result = new ArrayList<>();
        for (Resource resource : resources) {
            List<Bookable> bookablesForResource = bookableRepository.findByResourceIdAndDate(resource.getId(), LocalDate.parse(date));
            result.addAll(bookablesForResource.stream().map(bookableMapper::toDto).toList());
        }

        return result;
    }
}
