package ApiTests.ObjectClasses;

public class Account {
    public int accountId;
    public int userId;
    public String accountNumber;
    public int accountType;
    public boolean status;
    public String accountInfo;
    public double amount;
    public String updatedDate;

    public Account(int aId, String aNumber, int aType, boolean aStatus, String aInfo, double aAmount)
    {
        this.setAccountId(aId);
        this.setAccountNumber(aNumber);
        this.setAccountType(aType);
        this.setStatus(aStatus);
        this.setAccountInfo(aInfo);
        this.setAmount(aAmount);
    }

    public int getAccountId() {return accountId;}
    public int getUserId() {return userId;}
    public String getAccountNumber() {return accountNumber;}
    public int getAccountType() {return accountType;}
    public boolean getStatus() {return status;}
    public String getAccountInfo() {return accountInfo;}
    public double getAmount() {return amount;}
    public String getUpdatedDate() {return updatedDate;}

    public void setAccountId(int a) {this.accountId = a;}
    public void setUserId(int a) {this.userId = a;}
    public void setAccountNumber(String a) {this.accountNumber = a;}
    public void setAccountType(int a) {this.accountType = a;}
    public void setStatus(boolean a) {this.status = a;}
    public void setAccountInfo(String a) {this.accountInfo = a;}
    public void setAmount(double a) {this.amount = a;}
    public void setUpdatedDate(String a) {this.updatedDate = a;}

    public boolean equalsExceptUpdatedDate(Account a)
    {
        if (this == a)
            return true;
        if (a == null){
            System.out.println("1");
            return false;
        }
        if (getClass() != a.getClass()) {
            System.out.println("2");
            return false;
        }
        if (getAccountId() != a.getAccountId()) {
            System.out.println("3");
            return false;
        }
        if (getAmount() != a.getAmount()) {
            System.out.println("4");
            return false;
        }
        if (!getAccountInfo().equals(a.getAccountInfo())) {
            System.out.println("5");
            return false;
        }
        if (!getAccountNumber().equals(a.getAccountNumber())) {
            System.out.println("6");
            return false;
        }
        if (getAccountType() != a.getAccountType()) {
            System.out.println("7");
            return false;
        }
        if (getStatus() != a.getStatus()) {
            System.out.println("8");
            return false;
        }
        if (getUserId() != a.getUserId()) {
            System.out.println("9");
            return false;
        }
        return true;
    }
}