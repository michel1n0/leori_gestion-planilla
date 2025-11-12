package tech.leori.gestionplanilla.employee.application.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeContractCommand;
import tech.leori.gestionplanilla.employee.domain.Employee;
import tech.leori.gestionplanilla.employee.domain.EmployeeContract;
import tech.leori.gestionplanilla.employee.domain.EmployeeContractRepository;
import tech.leori.gestionplanilla.employee.domain.EmployeeFactory;

@ExtendWith(MockitoExtension.class)
class CreateEmployeeContractServiceTest {

  @Mock
  private EmployeeContractRepository employeeContractRepository;

  @InjectMocks
  private CreateEmployeeContractService createEmployeeContractService;

  @Test
  void createShouldPersistEmployeeContractWhenCommandIsValid() {
    Employee employee = EmployeeFactory.createEmployee("12345678", "John Doe");
    LocalDate startDate = LocalDate.now().minusDays(1);
    BigDecimal salary = new BigDecimal("1500.00");
    CreateEmployeeContractCommand command =
        new CreateEmployeeContractCommand(employee, startDate, salary);

    when(employeeContractRepository.save(any(EmployeeContract.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    createEmployeeContractService.create(command);

    ArgumentCaptor<EmployeeContract> contractCaptor = ArgumentCaptor.forClass(EmployeeContract.class);
    verify(employeeContractRepository).save(contractCaptor.capture());

    EmployeeContract savedContract = contractCaptor.getValue();

    assertNotNull(savedContract);
    assertEquals(employee.getId(), savedContract.getEmployeeId());
    assertEquals(startDate, savedContract.getStartDate());
    assertEquals(salary, savedContract.getSalary());
  }
}

