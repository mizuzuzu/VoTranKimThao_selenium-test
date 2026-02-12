package Railway;

import Common.Utilities;

public enum Tickets {

    TC12(5, "Nha Trang", "Huế", "Soft bed with air conditioner", "1"),

    TC13(28, "Nha Trang", "Sài Gòn", "Soft seat with air conditioner", "5"),

    TC16(3, "Nha Trang", "Huế", "Soft bed with air conditioner", "1");

    private final int daysFromNow;
    private final String departFrom;
    private final String arriveAt;
    private final String seatType;
    private final String amount;

    Tickets(int daysFromNow, String departFrom, String arriveAt,String seatType, String amount) {

        this.daysFromNow = daysFromNow;
        this.departFrom = departFrom;
        this.arriveAt = arriveAt;
        this.seatType = seatType;
        this.amount = amount;
    }
    
    public Ticket toTicket() {
        return new Ticket(Utilities.getNextDay(daysFromNow), departFrom, arriveAt, seatType, amount);
    }
    
    
}
