public class IbanValidationService {
    private String iBan;
    private int calculatedCheckSum;

    public IbanValidationService(String iBan) {
        this.iBan = iBan;
        this.calculatedCheckSum = -1;
    }

    public String getCountryCode() {
        return iBan.substring(0,2); // gets first two characters of String
    }

    public int getCheckSum() {
        String part = iBan.substring(2,4); // gets characters at position 2,3
        return Integer.valueOf(part);   // Integer.valueOf(String) converts a String to an int
    }

    public String getAccountNumber() {
        String part = "";
        // TODO: Extract all characters from 4 to the end "AT611904300234573201" should be "1904300234573201"
        return part;
    }


    public String getCountryCodeNumber() {
        // Converts an 'A' to 10, a 'B' to 11 an makes out of "AT" = 102900
        return "" +
                ((getCountryCode().charAt(0) - 'A' + 10) * 100
                + getCountryCode().charAt(1) - 'A' + 10) * 100;
    }

    public String getFullCode() {
        // AccountNumber and CountryCode need to be married again
        return getAccountNumber() + getCountryCodeNumber();
    }

    private int charNumberToInt(char c){
        // Converts a character number to its number-value
        // '0' is 48 in Ascii Table, for this reason, always deduct value of '0' (48)
        // https://www.torsten-horn.de/techdocs/ascii.htm
        return c - '0';
    }

    public int[] buildArray() {
        String fullCode = getFullCode();
        int[] arr = new int[fullCode.length()];

        /*
            TODO: Convert a string, step by step to an array
            Transform every character with charNumberToInt(char c) to its number value
         */



        return arr;
    }

    public int calcCheckSum() {
        int[] arr = buildArray();

        int value = 0;
        // TODO: Implement a modulo calculation, digit by digit


        // TODO: Calculate the new checksum
        return calculatedCheckSum;
    }

    public boolean isIbanCorrect(){
        // TODO: Compare calculatedCheckSum and getCheckSum()
        return false;
    }
}
