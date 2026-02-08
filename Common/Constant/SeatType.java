package Constant;

public enum SeatType {

    HS("HS", "310000"),
    SS("SS", "335000"),
    SSC("SSC", "360000"),
    HB("HB", "410000"),
    SB("SB", "460000"),
    SBC("SBC", "510000");

    private final String code;
    private final String price;

    SeatType(String code, String price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getPrice() {
        return price;
    }
}
