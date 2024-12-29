package com.technicaltest.test_backed.controller;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.technicaltest.test_backed.service.ClientService;
import com.technicaltest.test_backed.exception.InternalServerErrorException;
import com.technicaltest.test_backed.model.Client;
import com.technicaltest.test_backed.exception.BadRequestException;
import com.technicaltest.test_backed.exception.ClientNotFoundException;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {
    private final ClientService clientService;
     /**
     * GET /api/v1/clientes?tipoDocumento=C&numeroDocumento=23445322
     */

      @GetMapping
    public ResponseEntity<Client> getCustomer(
            @RequestParam String docType,
            @RequestParam String docNumber) {

        Client client = clientService.getCustomer(docType, docNumber);
        return ResponseEntity.ok(client);
    }
  // Manejadores de excepciones para retornar los códigos HTTP correspondientes

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClienteNoEncontrado(ClientNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<String> handleInternalServerError(InternalServerErrorException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
