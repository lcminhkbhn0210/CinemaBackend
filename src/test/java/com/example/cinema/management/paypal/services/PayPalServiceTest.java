package com.example.cinema.management.paypal.services;

import com.example.cinema.management.paypal.dto.BillDTO;
import com.example.cinema.management.paypal.dto.BillResponsePayPalDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PayPalServiceTest {

    @Mock
    private PayPalService payPalService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBillWhenBillDtoProvidedThenBillResponsePayPalDtoReturned() throws IOException, InterruptedException {
        // Arrange
        BillDTO billDTO = mock(BillDTO.class);
        BillResponsePayPalDTO expectedResponse = mock(BillResponsePayPalDTO.class);

        when(payPalService.createBill(billDTO)).thenReturn(expectedResponse);

        // Act
        BillResponsePayPalDTO actualResponse = payPalService.createBill(billDTO);

        // Assert
        verify(payPalService, times(1)).createBill(billDTO);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testCreateBillWhenIoExceptionOccursThenExceptionThrown() throws IOException, InterruptedException {
        // Arrange
        BillDTO billDTO = mock(BillDTO.class);

        when(payPalService.createBill(billDTO)).thenThrow(IOException.class);

        // Act & Assert
        assertThrows(IOException.class, () -> payPalService.createBill(billDTO));
        verify(payPalService, times(1)).createBill(billDTO);
    }

    @Test
    public void testCreateBillWhenInterruptedExceptionOccursThenExceptionThrown() throws IOException, InterruptedException {
        // Arrange
        BillDTO billDTO = mock(BillDTO.class);

        when(payPalService.createBill(billDTO)).thenThrow(InterruptedException.class);

        // Act & Assert
        assertThrows(InterruptedException.class, () -> payPalService.createBill(billDTO));
        verify(payPalService, times(1)).createBill(billDTO);
    }
}