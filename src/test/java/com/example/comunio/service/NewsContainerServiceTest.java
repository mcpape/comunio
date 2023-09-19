package com.example.comunio.service;

import com.example.comunio.repository.NewsContainerEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class NewsContainerServiceTest {

    @Mock
    private NewsContainerEntityRepository newsContainerEntityRepository;
    @InjectMocks
    private NewsContainerService testee;

    @Test
    void testThatLastStopDateIsCorrect() {
        //GIVEN

        //WHEN
        String stopDate = testee.getStopDate();

        //THEN
        assertNotNull(stopDate);
    }
}