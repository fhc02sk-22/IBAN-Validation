import java.util.Arrays;
import java.util.Scanner;

public class DemoApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input IBAN:");

        //String iban = scanner.next();
        String iban = "AT611904300234573201";

        IbanValidationService ibanValidationService = new IbanValidationService(iban);

        System.out.println("ibanValidationService.getCountryCode() = " + ibanValidationService.getCountryCode());
        System.out.println("ibanValidationService.getCheckSum() = " + ibanValidationService.getCheckSum());
        System.out.println("ibanValidationService.getAccountNumbervalueOf() = " + ibanValidationService.getAccountNumber());
        System.out.println("ibanValidationService.getCountryCodeNumber() = " + ibanValidationService.getCountryCodeNumber());

        int[] arr = ibanValidationService.buildArray();
        System.out.println("arr = " + Arrays.toString(arr));
        int calculatedIban = ibanValidationService.calcCheckSum();
        System.out.println("ibanValidationService.calcCheckSum() = " + calculatedIban);

        System.out.println("ibanValidationService.isIbanCorrect() = " + ibanValidationService.isIbanCorrect());
    }
}
