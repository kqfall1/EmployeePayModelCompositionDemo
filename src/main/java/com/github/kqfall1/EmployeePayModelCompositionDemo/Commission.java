package com.github.kqfall1.EmployeePayModelCompositionDemo;

import java.math.BigDecimal;

/**
 * Defines a contract for employees to get paid by commission.
 * @author Quinn Keenan
 * @since 24/10/2025
 */
@FunctionalInterface
public interface Commission
{
	BigDecimal commissionPayout(BigDecimal sales);
}