package tech.leori.gestionplanilla.employee.application.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeAreaCommand;
import tech.leori.gestionplanilla.employee.domain.EmployeeArea;
import tech.leori.gestionplanilla.employee.domain.EmployeeContract;
import tech.leori.gestionplanilla.employee.domain.EmployeeContractRepository;

class CreateEmployeeAreaServiceTest {

  private EmployeeContractRepository employeeContractRepository;
  private CreateEmployeeAreaService service;

  @BeforeEach
  void setUp() {
    employeeContractRepository = mock(EmployeeContractRepository.class);
    service = new CreateEmployeeAreaService(employeeContractRepository);
  }

  @Test
  @DisplayName("create should persist new area assignment for an existing contract")
  void createShouldPersistNewAreaAssignmentForExistingContract() {
    UUID contractId = UUID.randomUUID();
    when(employeeContractRepository.findById(contractId))
        .thenReturn(Optional.of(mock(EmployeeContract.class)));

    CreateEmployeeAreaCommand command =
        new CreateEmployeeAreaCommand(contractId, "  Finance Operations  ");

    service.create(command);

    verify(employeeContractRepository).findById(contractId);

    ArgumentCaptor<EmployeeArea> areaCaptor = ArgumentCaptor.forClass(EmployeeArea.class);
    verify(employeeContractRepository).addArea(areaCaptor.capture());
    verifyNoMoreInteractions(employeeContractRepository);

    EmployeeArea persistedArea = areaCaptor.getValue();
    assertEquals(contractId, persistedArea.getContractId());
    assertEquals("Finance Operations", persistedArea.getAreaName());
    assertNotNull(persistedArea.getAreaId());
    assertNotNull(persistedArea.getAssignmentDate());
  }
}
