package com.github.kqfall1.EmployeePayModelCompositionDemo;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.github.kqfall1.java.validators.InputValidator.validateNumber;

/**
 * Encapsulates fields and logic needed for paying employees.
 *
 * <p>
 * Supports commissions, salaries, and wages simultaneously. {@code PayModel} objects
 * are immutable.
 * </p>
 * @author Quinn Keenan
 * @since 24/10/2025
 */
final class PayModel implements Commission, Salary, Wage
{
 	/**
 	 * Expressed as a double between 0 and 100.
 	 */
	private final BigDecimal commissionRate;
	private final BigDecimal hourlyRate;
	private final BigDecimal salary;
	private static final int SCALE = 2;

	public PayModel(BigDecimal commissionRate, BigDecimal hourlyRate, BigDecimal salary)
	{
		validateNumber(
			commissionRate.doubleValue(),
			"commissionRate",
			0,
			100
		);

		this.commissionRate = commissionRate;

		validateNumber(
			hourlyRate.doubleValue(),
			"hourlyRate",
			0,
			Float.MAX_VALUE
		);

		this.hourlyRate = hourlyRate;

		validateNumber(
			salary.doubleValue(),
			"salary",
			0,
			Float.MAX_VALUE
		);

		this.salary = salary;
	}

	@Override
	public BigDecimal commissionPayout(BigDecimal sales)
	{
		return sales
			.multiply(commissionRate)
			.divide(BigDecimal.valueOf(100), SCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getCommissionRate()
	{
		return commissionRate;
	}

	public BigDecimal getHourlyRate()
	{
		return hourlyRate;
	}

	public BigDecimal getSalary()
	{
		return salary;
	}

	/**
 	* Sums the returned values from all methods originating from implemented,
	 * payment-related interfaces.
	 * @param bonusAmount Should correspond to an {@code Employee.bonusAmount} field.
 	* @param hoursWorked Should correspond to an {@code Employee.hoursWorkedInPayPeriod} field.
 	* @param sales Should correspond to an {@code Employee.sales} field.
 	* @return The sum of all methods originating from implemented, payment-related interfaces.
 	*/
	public BigDecimal payout(BigDecimal bonusAmount, BigDecimal hoursWorked, BigDecimal sales)
	{
		return bonusAmount
			.add(commissionPayout(sales))
			.add(salaryPayout())
			.add(wagePayout(hoursWorked));
	}

	@Override
	public BigDecimal salaryPayout()
	{
		return salary.divide(BigDecimal.valueOf(12), SCALE, RoundingMode.HALF_UP);
	}

	@Override
	public String toString()
	{
		return String.format("%s[commissionRate=%s,hourlyRate=%s,salary=%s]",
			getClass().getName(),
			getCommissionRate(),
			getHourlyRate(),
			getSalary()
		);
	}

	@Override
	public BigDecimal wagePayout(BigDecimal hoursWorked)
	{
		BigDecimal overtimeHours;
		BigDecimal overtimePayout =  BigDecimal.ZERO;
		BigDecimal standardHours = hoursWorked;
		BigDecimal standardPayout;

		if (hoursWorked.doubleValue() > OVERTIME_CUTOFF)
		{
			overtimeHours = hoursWorked
				.subtract(BigDecimal.valueOf(OVERTIME_CUTOFF));
			standardHours = hoursWorked.subtract(overtimeHours);

			overtimePayout = overtimeHours
				.multiply(hourlyRate)
				.multiply(BigDecimal.valueOf(OVERTIME_MULTIPLIER));
		}

		standardPayout = standardHours.multiply(hourlyRate);
		return standardPayout.add(overtimePayout);
	}
}