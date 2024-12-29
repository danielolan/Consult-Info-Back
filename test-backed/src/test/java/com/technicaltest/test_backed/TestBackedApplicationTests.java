package com.technicaltest.test_backed;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.technicaltest.test_backed.service.ClientService;
import com.technicaltest.test_backed.service.ClientServiceImpl;
import com.technicaltest.test_backed.exception.ClientNotFoundException;
import com.technicaltest.test_backed.model.Client;
import org.junit.jupiter.api.Assertions;
import com.technicaltest.test_backed.exception.BadRequestException;
@SpringBootTest
class TestBackedApplicationTests {
    private final ClientService clientService = new ClientServiceImpl();

	@Test
    public void testGetExistingCustomer() {
        Client client = clientService.getCustomer("C", "23445322");
        Assertions.assertNotNull(client, "El cliente no debe ser nulo");
        Assertions.assertEquals("Juan", client.getFirstName(), 
                "El primer nombre debe ser 'Juan'");
    }
	@Test
    void testGetNonExistingCustomer() {
        Assertions.assertThrows(ClientNotFoundException.class,
                () -> clientService.getCustomer("C", "99999999"));
    }

	@Test
    void testDocumentTypeNotValid() {
        Assertions.assertThrows(BadRequestException.class,
                () -> clientService.getCustomer("X", "23445322"));
    }

	@Test
    void testParametersNull() {
        Assertions.assertThrows(BadRequestException.class,
                () -> clientService.getCustomer(null, null));
    }

}
