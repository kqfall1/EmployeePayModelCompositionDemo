package com.github.kqfall1.EmployeePayModelCompositionDemo;

import java.math.BigDecimal;

/**
 * Defines a contract for employees to get paid by the hour.
 * @author Quinn Keenan
 * @since 24/10/2025
 */
@FunctionalInterface
public interface Wage
{
	double OVERTIME_CUTOFF  = 40;
	double OVERTIME_MULTIPLIER = 1.5;

	BigDecimal wagePayout(BigDecimal hoursWorked);
}