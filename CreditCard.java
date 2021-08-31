import java.util.Calendar;

public class CreditCard {
    public final int expiryMonth;
    public final int expiryYear;
    public final String firstName;
    public final String lastName;
    public final String ccNumber;
    Calendar now = Calendar.getInstance();
    
    public CreditCard(int expiryMonth, int expiryYear, String firstName, String lastName, String ccNumber){
            this.expiryYear = expiryYear;
            this.expiryMonth = expiryMonth;
            this.firstName = firstName;
            this.lastName = lastName;
            this.ccNumber = ccNumber;
    }
    public String formatExpiryDate(){
        return this.expiryMonth + "/" + this.expiryYear%100;
    }
    public String formatFullName(){
        return this.firstName + " " + this.lastName;
    }
    public String formatCCNumber(){
        return this.ccNumber.substring(0,4) + " " + this.ccNumber.substring(4,8) + " " +  this.ccNumber.substring(8,12) +" " +  this.ccNumber.substring(12,16); 
    }
    public Boolean isValid(){
        boolean valid = true;
        if (this.expiryYear < now.get(Calendar.YEAR)){
                valid = false;
        }else if (this.expiryYear >= now.get(Calendar.YEAR)){
            if (this.expiryMonth -1 < now.get(Calendar.MONTH)){
                valid = false;
           }else 
                valid = true;;
            }
      return valid;
    }

    public String toString(){
        return "Number: " + formatCCNumber() + "\n Expiration date: " + formatExpiryDate()
                + "\n Account Holder: " + formatFullName() + "\n Is valid: " + isValid();
    }

    public static void main(String[] args){
        CreditCard cc1 = new CreditCard(10, 2014, "Bob", "Jones", "1234567890123456");
        StdOut.print(cc1.formatExpiryDate());
    }
    
}
