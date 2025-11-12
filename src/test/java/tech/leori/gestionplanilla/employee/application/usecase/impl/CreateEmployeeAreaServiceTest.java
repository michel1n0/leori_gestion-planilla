package tech.leori.gestionplanilla.employee.application.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeAreaCommand;
import tech.leori.gestionplanilla.employee.domain.EmployeeArea;
import tech.leori.gestionplanilla.employee.domain.EmployeeContract;
import tech.leori.gestionplanilla.employee.domain.EmployeeContractRepository;
import tech.leori.gestionplanilla.employee.domain.exception.EmployeeContractNotFoundException;

class CreateEmployeeAreaServiceTest {

  private EmployeeContractRepository employeeContractRepository;
  private CreateEmployeeAreaService service;

  @BeforeEach
  void setUp() {
    employeeContractRepository = Mockito.mock(EmployeeContractRepository.class);
    service = new CreateEmployeeAreaService(employeeContractRepository);
  }

  @Test
  @DisplayName("create should add an area to an existing contract")
  void createShouldAddAreaToExistingContract() {
    UUID contractId = UUID.randomUUID();
    when(employeeContractRepository.findById(contractId))
        .thenReturn(Optional.of(Mockito.mock(EmployeeContract.class)));

    CreateEmployeeAreaCommand command = new CreateEmployeeAreaCommand(contractId, "Finance");

    service.create(command);

    verify(employeeContractRepository).findById(contractId);

    ArgumentCaptor<EmployeeArea> employeeAreaCaptor = ArgumentCaptor.forClass(EmployeeArea.class);
    verify(employeeContractRepository).addArea(employeeAreaCaptor.capture());
    verifyNoMoreInteractions(employeeContractRepository);

    EmployeeArea savedArea = employeeAreaCaptor.getValue();
    assertEquals(contractId, savedArea.getContractId(), "Area should be associated with the contract");
    assertEquals("Finance", savedArea.getAreaName(), "Area name should be trimmed by the factory");
    assertNotNull(savedArea.getAreaId(), "Area id should be generated");
    assertEquals(LocalDate.now(), savedArea.getAssignmentDate(),
        "Assignment date should be set to the current date");
  }

  @Test
  @DisplayName("create should throw when the contract does not exist")
  void createShouldThrowWhenContractDoesNotExist() {
    UUID contractId = UUID.randomUUID();
    when(employeeContractRepository.findById(contractId)).thenReturn(Optional.empty());

    CreateEmployeeAreaCommand command = new CreateEmployeeAreaCommand(contractId, "Finance");

    assertThrows(EmployeeContractNotFoundException.class, () -> service.create(command));

    verify(employeeContractRepository).findById(contractId);
    verify(employeeContractRepository, never()).addArea(Mockito.any());
    verifyNoMoreInteractions(employeeContractRepository);
  }
}
