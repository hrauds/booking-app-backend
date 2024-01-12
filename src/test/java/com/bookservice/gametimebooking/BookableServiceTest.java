package com.bookservice.gametimebooking;

import com.bookservice.gametimebooking.dto.BookableDto;
import com.bookservice.gametimebooking.mapper.BookableMapper;
import com.bookservice.gametimebooking.model.Bookable;
import com.bookservice.gametimebooking.model.Resource;
import com.bookservice.gametimebooking.repository.BookableRepository;
import com.bookservice.gametimebooking.repository.ResourceRepository;
import com.bookservice.gametimebooking.service.BookableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookableServiceTest {

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private BookableRepository bookableRepository;

    @Mock
    private BookableMapper bookableMapper;

    @Captor
    private ArgumentCaptor<Bookable> bookableArgumentCaptor;

    @InjectMocks
    private BookableService bookableService;

    @Test
    void addBookableForResourceSuccess() {
        Long mockResourceId = 1L;
        String mockDate = "2024-01-10";
        String mockStartTime = "15:30:45";
        String mockEndTime = "16:30:45";
        Resource resource = new Resource();

        when(resourceRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(resource));
        when(bookableRepository.existsByResourceAndDate(Mockito.any(Resource.class), Mockito.any(LocalDate.class))).thenReturn(false);
        when(bookableRepository.save(Mockito.any(Bookable.class))).thenAnswer(
                invocation -> invocation.getArguments()[0]);

        bookableService.addBookableForResource(mockResourceId, mockDate, mockStartTime, mockEndTime);

        verify(resourceRepository, times(1)).findById(mockResourceId);
        verify(bookableRepository, times(1)).existsByResourceAndDate(resource, LocalDate.parse(mockDate));
        verify(bookableRepository).save(bookableArgumentCaptor.capture());
        Bookable capturedBookable = bookableArgumentCaptor.getValue();

        assertThat(capturedBookable.getResource()).isEqualTo(resource);
    }

    @Test
    void getBookablesForServiceAndDateSuccess() {
        Long mockServiceId = 1L;
        String mockDate = "2024-01-10";
        Resource resource = new Resource();
        Bookable bookable = new Bookable();
        BookableDto bookableDto = new BookableDto();

        when(resourceRepository.findAllByServiceId(Mockito.anyLong())).thenReturn(List.of(resource));
        when(bookableRepository.findByResourceIdAndDate(Mockito.any(), Mockito.any(LocalDate.class)))
                .thenReturn(List.of(bookable));
        when(bookableMapper.toDto(Mockito.any(Bookable.class))).thenReturn(bookableDto);

        List<BookableDto> result = bookableService.getBookablesForServiceAndDate(mockServiceId, mockDate);

        verify(resourceRepository, times(1)).findAllByServiceId(mockServiceId);
        verify(bookableRepository, times(1))
                .findByResourceIdAndDate(resource.getId(), LocalDate.parse(mockDate));
        verify(bookableMapper, times(1)).toDto(bookable);

        assertThat(result.contains(bookableDto)).isTrue();
    }

    @Test
    void deleteBookableSuccess() {
        Long mockBookableId = 1L;

        doNothing().when(bookableRepository).deleteById(Mockito.anyLong());

        bookableService.deleteBookable(mockBookableId);

        verify(bookableRepository, times(1)).deleteById(mockBookableId);
    }
}
