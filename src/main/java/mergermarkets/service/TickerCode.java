package mergermarkets.service;


import lombok.ToString;

@ToString
public class TickerCode {
    private String code;

    public TickerCode(final String tickerCode) {
        this.code = tickerCode;
    }

    public String getCode() {
        return this.code;
    }
}
