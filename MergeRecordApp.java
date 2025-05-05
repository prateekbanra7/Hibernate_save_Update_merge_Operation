package in.abc.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.abc.Model.Employee;
import in.abc.util.HibernateUtil;

public class MergeRecordApp {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		Boolean flag = false;
		Employee emp1 = null;
		Employee emp2 = null;
		Employee emp3 = null;
		Transaction transaction = null;

		emp1 = session.get(Employee.class, 18);//[18,'virat',2447.8] ==> L1 Cache(available insise session)

		try {
			transaction = session.beginTransaction();//con.setAutocommit(false)

			emp2 = new Employee();
			emp2.setEmpId(18);
			emp2.setEmpName("viratkohli");
			emp2.setEmpSal(3334.5);//[18,'virat,2447.8][emp2]
			
			emp3 = (Employee) session.merge(emp2);//To merge given object data to already loaded same object data.
													//[18,'virat,2447.8]==>L1 Cache(emp1,emp3)
			flag = true;
			
			System.out.println(emp1);
			System.out.println(emp2);
			System.out.println(emp3);
			System.out.println(emp1.hashCode() + ":" + emp2.hashCode() + ":" + emp3.hashCode());

		} catch (HibernateException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			if (flag) {
				transaction.commit();//con.commit()
				System.out.println("Object updated...");
			} else {
				transaction.rollback();//con.rollback()
				System.out.println("object failed to update...");
			}
		}
	}
}
