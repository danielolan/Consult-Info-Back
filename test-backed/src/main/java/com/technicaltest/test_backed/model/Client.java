package com.technicaltest.test_backed.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor  // Genera constructor sin parámetros
@AllArgsConstructor // Genera constructor con todos los campos
public class Client {
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondSurname;
    private String mobile;
    private String address;
    private String cityResidence;

}
