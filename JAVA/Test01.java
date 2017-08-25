
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test01 {
	public static void main(String[] args) {
		Account account = new Account();
		ExecutorService service = Executors.newFixedThreadPool(100);

		for (int i = 1; i <= 100; i++) {
			service.execute(new AddMoneyThread(account, 1,i));
		}
		service.shutdown();
		while (!service.isTerminated()) {
		}
		System.out.println("�˻���: " + account.getBalance());
	}
}

class AddMoneyThread implements Runnable {
	private Account account; // �����˻�
	private double money; //������
    private int index;
	public AddMoneyThread(Account account, double money,int index) {
		this.account = account;
		this.money = money;
		this.index = index;
	}

	@Override
	public void run() {
		//System.out.println("AddMoneyThread��money: " + money);
		synchronized (account){
			//System.out.println("AddMoneyThread��in ");
		account.deposit(money,index);
		}
	}
}

class Account {
	private double balance; //  �˻���

	/**
	* ���
	* @param money ������
	*/
	public void deposit(double money,int index) {
		
		double newBalance = balance + money;
		System.out.println("deposit success : " + index+"  newBalance="+ newBalance +"  balance="+ balance+"   money="+ money);
		try {
			Thread.sleep(10);  // ģ���ҵ����Ҫһ�δ���ʱ��
			
		} catch (InterruptedException ex) {
			ex.printStackTrace();
			System.out.println("Account��: " + ex);
		}
		
		balance = newBalance;
		
	}

	/**
	 * ����˻���
	 */
	public double getBalance() {
		return balance;
	}
}