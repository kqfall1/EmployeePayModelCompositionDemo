package com.github.kqfall1.EmployeePayModelCompositionDemo;

import java.math.BigDecimal;

/**
 * Defines a contract for employees to get paid via a bonus.
 * @author Quinn Keenan
 * @since 25/10/2025
 */
@FunctionalInterface
public interface Bonus
{
	BigDecimal bonusPayout(BigDecimal amount);
}