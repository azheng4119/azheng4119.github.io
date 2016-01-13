package objects;

public class Balance {

	double amount;
	long lastWorked;
	
	public Balance(){
		amount = 0;
		lastWorked = 0;
	}
	
	public void subtractLateFees(long timeOverDue){
		if(timeOverDue < 0){
			int daysLate = (int) (timeOverDue/1000)*-1;
			if (daysLate <= 1) amount -= 5;
			if (daysLate > 1) amount -= (5 + (daysLate-1));
		}
	}

	public boolean canWork(long time){
		if (time - lastWorked >= 10000) return true;
		return false;
	}

	public String earnMoney(long time){
		if (canWork(time)){
			amount += 5;
			lastWorked = time;
			return "You worked and have gain $5";
		}
		return "can not do a double shift! Wait until the first job is done!";
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getLastWorked() {
		return lastWorked;
	}

	public void setLastWorked(long lastWorked) {
		this.lastWorked = lastWorked;
	}


}
