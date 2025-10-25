package com.github.kqfall1.EmployeePayModelCompositionDemo;

import static java.math.BigDecimal.*;

/**
 * This application demonstrates the modern preference for composition over inheritance
 * using the classic, textbook example of an employee class.
 * @author Quinn Keenan
 * @since 23/10/2025
 */
class EmployeeController
{
	private EmployeeController() {}

	static void main(String[] args)
	{
		var commission = new PayModel(valueOf(3.5), ZERO, ZERO);
		var emp = new Employee("Harry Hacker", commission);
		emp.setSales(valueOf(213471.82));
		System.out.printf("%s was paid %.2f.\n", emp, emp.pay());

		var hourly = new PayModel(ZERO, valueOf(18.65), ZERO);
		emp.setHoursWorkedInPayPeriod(valueOf(52.56));
		emp.setPayModel(hourly);
		System.out.printf("%s was paid %.2f.\n", emp, emp.pay());

		var salary = new PayModel(ZERO, ZERO, valueOf(121565));
		emp.setPayModel(salary);
		System.out.printf("%s was paid %.2f.\n", emp, emp.pay());

		var commissionSalary = new PayModel(valueOf(1.75), ZERO, valueOf(95250));
		emp.setPayModel(commissionSalary);
		System.out.printf("%s was paid %.2f.\n", emp, emp.pay());
	}
}