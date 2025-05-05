package in.abc.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.abc.Model.Employee;
import in.abc.util.HibernateUtil;

public class UpdateRecordApp {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		Boolean flag = false;
		Transaction transaction = null;
		
		//updating the total object based on id
		Employee employee = new Employee();
		employee.setEmpId(18);
		employee.setEmpName("kohli");
		employee.setEmpSal(2447.8);

		try {
			transaction = session.beginTransaction();
			session.update(employee);
			flag = true;

		} catch (HibernateException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			if(flag) {
				transaction.commit();
				System.out.println("Object updated...");
			} else {
				transaction.rollback();
				System.out.println("object failed to update...");
			}
		}
	}
}
