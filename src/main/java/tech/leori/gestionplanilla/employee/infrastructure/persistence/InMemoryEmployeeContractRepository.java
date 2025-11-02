package tech.leori.gestionplanilla.employee.infrastructure.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import tech.leori.gestionplanilla.employee.domain.EmployeeContract;
import tech.leori.gestionplanilla.employee.domain.EmployeeContractRepository;

public class InMemoryEmployeeContractRepository implements EmployeeContractRepository {

  private final Map<UUID, EmployeeContract> storage = new ConcurrentHashMap<>();

  @Override
  public EmployeeContract save(EmployeeContract employeeContract) {
    storage.put(employeeContract.getContractId(), employeeContract);
    return employeeContract;
  }

  @Override
  public Optional<EmployeeContract> findById(UUID contractId) {
    return Optional.ofNullable(storage.get(contractId));
  }
}
