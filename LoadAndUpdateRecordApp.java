package in.abc.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.abc.Model.Employee;
import in.abc.util.HibernateUtil;

public class LoadAndUpdateRecordApp {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		Boolean flag = false;
		Transaction transaction = null;
		Employee employee = null;

		try {
			int id = 10;
			employee = session.get(Employee.class, id);
			if (employee != null) {
				transaction = session.beginTransaction();
				employee.setEmpName("msd");
				employee.setEmpSal(4532.5);
				session.update(employee);
				flag = true;

			} else {
				System.out.println("Record not found for the id ::" + id);
				System.exit(0);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object updated...");
			} else {
				transaction.rollback();
				System.out.println("object failed to update...");
			}
		}
	}
}
