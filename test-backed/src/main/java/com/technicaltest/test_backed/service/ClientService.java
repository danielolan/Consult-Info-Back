package com.technicaltest.test_backed.service;

import com.technicaltest.test_backed.model.Client;
public interface ClientService {

    /**
     * Obtiene la información de un cliente por tipo y número de documento.
     * @param docType   tipo de documento (C, P)
     * @param docNumber número de documento
     * @return Client
     */
    Client getCustomer(String docType, String docNumber);
}