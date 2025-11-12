package tech.leori.gestionplanilla.employee.domain;

import java.util.Optional;
import java.util.UUID;

import tech.leori.gestionplanilla.employee.domain.EmployeeArea;
import tech.leori.gestionplanilla.employee.domain.EmployeeLaw;

public interface EmployeeContractRepository {

  EmployeeContract save(EmployeeContract employeeContract);

  Optional<EmployeeContract> findById(UUID contractId);

  void addArea(EmployeeArea employeeArea);

  void addLaw(UUID contractId, EmployeeLaw employeeLaw);
}
