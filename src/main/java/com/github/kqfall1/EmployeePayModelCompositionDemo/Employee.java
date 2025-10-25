package com.github.kqfall1.EmployeePayModelCompositionDemo;

import com.github.kqfall1.handlers.input.InputHandler;
import java.math.BigDecimal;

/**
 * Encapsulates an id, name, {@code PayModel} and other fields required for payroll purposes.
 * @author Quinn Keenan
 * @since 25/10/2025
 */
final class Employee
{
	private BigDecimal bonusAmount;
	private BigDecimal hoursWorkedInPayPeriod;
	private final int id;
	private final String name;
	private static int nextId;
	private PayModel payModel;
	private BigDecimal sales;

	Employee(String name, PayModel payModel)
	{
		setBonusAmount(BigDecimal.ZERO);
		setHoursWorkedInPayPeriod(BigDecimal.ZERO);
		id = ++nextId;
		InputHandler.validateInputWasEntered(name);
		this.name = name;
		setPayModel(payModel);
		setSales(BigDecimal.ZERO);
	}

	BigDecimal getBonusAmount()
	{
		return bonusAmount;
	}

	BigDecimal getHoursWorkedInPayPeriod()
	{
		return hoursWorkedInPayPeriod;
	}

	int getId()
	{
		return id;
	}

	String getName()
	{
		return name;
	}

	PayModel getPayModel()
	{
		return payModel;
	}

	BigDecimal getSales()
	{
		return sales;
	}

	/**
 	* Invokes the {@code pay} method of this {@code Employee} object's {@code payModel}.
	 * Resets {@code bonusAmount}, {@code hoursWorkedInPayPeriod}, and {@code sales}.
 	* @return A {@code BigDecimal} representing the earnings of this {@code Employee}.
 	*/
	BigDecimal pay()
	{
		BigDecimal pay = payModel.payout(bonusAmount, hoursWorkedInPayPeriod, sales);
		bonusAmount = BigDecimal.ZERO;
		hoursWorkedInPayPeriod = BigDecimal.ZERO;
		sales = BigDecimal.ZERO;
		return pay;
	}
	void setBonusAmount(BigDecimal bonusAmount)
	{
		InputHandler.validateNumber(
			bonusAmount.doubleValue(),
			"bonusAmount",
			0,
			Float.MAX_VALUE
		);

		this.bonusAmount = bonusAmount;
	}

	void setHoursWorkedInPayPeriod(BigDecimal hoursWorkedInPayPeriod)
	{
		InputHandler.validateNumber(
			hoursWorkedInPayPeriod.doubleValue(),
			"hoursSinceLastPay",
			0,
			Float.MAX_VALUE
		);

		this.hoursWorkedInPayPeriod = hoursWorkedInPayPeriod;
	}

	void setPayModel(PayModel payModel)
	{
		InputHandler.validateObjIsNotNull("payModel", payModel);
		this.payModel = payModel;
	}

	void setSales(BigDecimal sales)
	{
		InputHandler.validateNumber(
			sales.doubleValue(),
			"sales",
			0,
			Float.MAX_VALUE
		);

		this.sales = sales;
	}

	@Override
	public String toString()
	{
		return String.format("%s[id=%d,name=%s,bonusAmount=%s,hoursWorkedInPayPeriod=%s,payModel=%s,sales=%s]",
			getClass().getName(),
			getId(),
			getName(),
			getBonusAmount(),
			getHoursWorkedInPayPeriod(),
			getPayModel(),
			getSales()
		);
	}
}