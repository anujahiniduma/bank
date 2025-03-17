package com.anu.bank.capg.web.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountCreationRequestDTOTest {

    @Test
    void testInitialCreditGetterAndSetter() {
        // Arrange
        AccountCreationRequestDTO dto = new AccountCreationRequestDTO();
        double expectedCredit = 1000.0;

        // Act
        dto.setInitialCredit(expectedCredit);
        double actualCredit = dto.getInitialCredit();

        // Assert
        assertThat(actualCredit).isEqualTo(expectedCredit);
    }

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        AccountCreationRequestDTO dto = new AccountCreationRequestDTO();

        // Assert
        assertThat(dto.getInitialCredit()).isZero(); // Default value should be 0.0
    }

}
