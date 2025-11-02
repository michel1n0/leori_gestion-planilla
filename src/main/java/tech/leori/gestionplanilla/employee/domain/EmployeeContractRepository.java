package tech.leori.gestionplanilla.employee.domain;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeContractRepository {

  EmployeeContract save(EmployeeContract employeeContract);

  Optional<EmployeeContract> findById(UUID contractId);
}
