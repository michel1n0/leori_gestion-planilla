package tech.leori.gestionplanilla.employee.application.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeLawCommand;
import tech.leori.gestionplanilla.employee.domain.EmployeeContract;
import tech.leori.gestionplanilla.employee.domain.EmployeeContractRepository;
import tech.leori.gestionplanilla.employee.domain.EmployeeLaw;
import tech.leori.gestionplanilla.employee.domain.exception.EmployeeContractNotFoundException;

class CreateEmployeeLawServiceTest {

  private EmployeeContractRepository employeeContractRepository;
  private CreateEmployeeLawService service;

  @BeforeEach
  void setUp() {
    employeeContractRepository = Mockito.mock(EmployeeContractRepository.class);
    service = new CreateEmployeeLawService(employeeContractRepository);
  }

  @Test
  @DisplayName("create should link a law regime to an existing contract")
  void createShouldLinkLawToExistingContract() {
    UUID contractId = UUID.randomUUID();
    when(employeeContractRepository.findById(contractId))
        .thenReturn(Optional.of(Mockito.mock(EmployeeContract.class)));

    CreateEmployeeLawCommand command =
        new CreateEmployeeLawCommand(contractId, "RL", "  General Regime  ", new BigDecimal("13.50"));

    service.create(command);

    verify(employeeContractRepository).findById(contractId);

    ArgumentCaptor<EmployeeLaw> employeeLawCaptor = ArgumentCaptor.forClass(EmployeeLaw.class);
    verify(employeeContractRepository).addLaw(contractId, employeeLawCaptor.capture());
    verifyNoMoreInteractions(employeeContractRepository);

    EmployeeLaw savedLaw = employeeLawCaptor.getValue();
    assertEquals("RL", savedLaw.getCode(), "Law code should match the command value");
    assertEquals(
        "General Regime",
        savedLaw.getDescription(),
        "Law description should be trimmed by the factory");
    assertEquals(
        new BigDecimal("13.50"),
        savedLaw.getContributionPercentage(),
        "Law contribution should match the command value");
  }

  @Test
  @DisplayName("create should throw when the contract does not exist")
  void createShouldThrowWhenContractDoesNotExist() {
    UUID contractId = UUID.randomUUID();
    when(employeeContractRepository.findById(contractId)).thenReturn(Optional.empty());

    CreateEmployeeLawCommand command =
        new CreateEmployeeLawCommand(contractId, "RL", "General Regime", new BigDecimal("13.50"));

    assertThrows(EmployeeContractNotFoundException.class, () -> service.create(command));

    verify(employeeContractRepository).findById(contractId);
    verify(employeeContractRepository, never()).addLaw(Mockito.any(), Mockito.any());
    verifyNoMoreInteractions(employeeContractRepository);
  }
}
