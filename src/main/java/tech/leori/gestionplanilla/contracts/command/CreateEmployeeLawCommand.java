package tech.leori.gestionplanilla.contracts.command;

import java.math.BigDecimal;

public class CreateEmployeeLawCommand {

    private final Long contractId;
    private final String lawName;
    private final BigDecimal contributionPercentage;

    public CreateEmployeeLawCommand(Long contractId, String lawName, BigDecimal contributionPercentage) {
        this.contractId = contractId;
        this.lawName = lawName;
        this.contributionPercentage = contributionPercentage;
    }

    public Long getContractId() {
        return contractId;
    }

    public String getLawName() {
        return lawName;
    }

    public BigDecimal getContributionPercentage() {
        return contributionPercentage;
    }
}
