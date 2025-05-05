package in.abc.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.abc.Model.Employee;
import in.abc.util.HibernateUtil;

public class SaveOrUpdateApp {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		Boolean flag = false;
		Transaction transaction = null;
		
		Employee employee = new Employee();
		employee.setEmpId(20);
		employee.setEmpName("tendulkar");
		employee.setEmpSal(44445.5);
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(employee);
			flag = true;
		
		} catch (HibernateException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			if(flag) {
				transaction.commit();
				System.out.println("Object is saved/updated...");
			} else {
				transaction.rollback();
				System.out.println("object is not saved/updated...");
			}
		}
	}
}
