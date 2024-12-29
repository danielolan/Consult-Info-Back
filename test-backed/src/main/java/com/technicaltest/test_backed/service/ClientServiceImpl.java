package com.technicaltest.test_backed.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.technicaltest.test_backed.model.Client;
import com.technicaltest.test_backed.exception.InternalServerErrorException;
import com.technicaltest.test_backed.exception.BadRequestException;
import com.technicaltest.test_backed.exception.ClientNotFoundException;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public Client getCustomer(String docType, String docNumber) {
        // Validaciones iniciales
        if (docType == null || docType.isBlank()
                || docNumber == null || docNumber.isBlank()) {
            log.error("Faltan parámetros: docType o docNumber es nulo/vacío.");
            throw new BadRequestException("Los parámetros docType y docNumber son obligatorios.");
        }

        if (!docType.equalsIgnoreCase("C") && !docType.equalsIgnoreCase("P")) {
            log.error("Tipo de documento inválido: {}", docType);
            throw new BadRequestException("Tipo de documento no válido. Valores permitidos: C o P.");
        }

        try {
            // Datos "mockeados": solo existe un cliente con Cédula 23445322
            if (docType.equalsIgnoreCase("C") && "23445322".equals(docNumber)) {
                log.info("Cliente encontrado. docType={}, docNumber={}", docType, docNumber);
                return new Client(
                        "Juan",
                        "Carlos",
                        "Pérez",
                        "Gómez",
                        "3001234567",
                        "Calle Falsa 123",
                        "Bogotá"
                );
            } else {
                log.info("No se encontró el cliente con docType={}, docNumber={}", docType, docNumber);
                throw new ClientNotFoundException("Cliente no encontrado.");
            }
        } catch (ClientNotFoundException e) {
            // Si realmente quieres que sea ClientNotFoundException, 
            // NO lo conviertas en InternalServerError. Simplemente relánzalo:
            throw e;
        } catch (Exception e) {
            // Cualquier otra excepción no contemplada -> InternalServerError
            log.error("Error interno al consultar el cliente", e);
            throw new InternalServerErrorException("Error interno en el servidor.");
        }
        
    }
    
}
