package application;

public class Payment {

    double determineTotaslPaid(double intRate, double monthlyPay, int noMonths, double principal){
        double totalPaid = 0.0;
        if (intRate !=0) {
            totalPaid = monthlyPay*noMonths;
        }
        else {
            totalPaid = principal;
        }
        return totalPaid;
    }
}
