package com.example.cinema.testController;

import com.example.cinema.management.controllers.BillController;
import com.example.cinema.management.dto.BillRequestDTO;
import com.example.cinema.management.dto.BillResponseDTO;
import com.example.cinema.management.model.Message;
import com.example.cinema.management.services.BillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BillControllerTest {

    @Mock
    private BillService billService;

    @InjectMocks
    private BillController billController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateBill() {
        // Arrange
        BillRequestDTO billRequestDTO = new BillRequestDTO();
        BillResponseDTO expectedResponse = new BillResponseDTO();
        when(billService.createBillOffLine(billRequestDTO)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<BillResponseDTO> response = billController.createBillOffLine(billRequestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(billService, times(1)).createBillOffLine(billRequestDTO);
    }

    @Test
    public void testGetBillById() {
        // Arrange
        long id = 1L;
        BillResponseDTO expectedResponse = new BillResponseDTO();
        when(billService.getBillById(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<BillResponseDTO> response = billController.getBillById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(billService, times(1)).getBillById(id);
    }

    @Test
    public void testGetAllBill() {
        // Arrange
        List<BillResponseDTO> expectedResponse = new ArrayList<>();
        when(billService.getAllBill()).thenReturn(expectedResponse);

        // Act
        List<BillResponseDTO> response = billController.getAllBill();

        // Assert
        assertEquals(expectedResponse, response);
        verify(billService, times(1)).getAllBill();
    }

    @Test
    public void testDeleteBill() {
        // Arrange
        long id = 1L;
        Message expectedResponse = new Message("Success", "Bill deleted");
        when(billService.deletebill(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Message> response = billController.deleteBill(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(billService, times(1)).deletebill(id);
    }
}