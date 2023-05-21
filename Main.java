import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Ödeme tutarı: ");
            String paymentAmount = scanner.nextLine();
            if (!isValidPaymentAmount(paymentAmount)) {
                throw new InvalidAmountException("Ödeme tutarı geçersiz." +
                        "Negatif veya virgüllü bir değer içermeyen rakamlardan oluşmalı. ");
            }
            System.out.println("Kart numarası: ");
            String cardNumber = scanner.nextLine();
            if (!isValidCardNumber(cardNumber)) {
                throw new InvalidCardNumberException("Kart numarası geçersiz. 16 haneli rakamlardan oluşmalı.");
            }
            System.out.println("Son kullanma tarihini date/year şeklinde giriniz. ");
            String month = scanner.nextLine();
            String year = scanner.nextLine();
            if (!isValidExpirationDate(month, year)) {
                throw new InvalidExpirationDateException("Son kullanma tarihi geçersiz." +
                        "Geçmiş bir yıl veya geçersiz bir ay numarası girilemez.");
            }
            System.out.println("Güvenlik kodu: ");
            String securityCode = scanner.nextLine();
            if (!isValidSecurityCode(securityCode)) {
                throw new InvalidSecurityCodeException("Güvenlik kodu geçersiz." +
                        "Güvenlik kodu 3 haneli rakamlardan oluşmalı");
            }
            System.out.println("Girilen bilgiler doğru.");

            pay();

        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCardNumberException e) {
            System.out.println(e.getMessage());
        } catch (InvalidExpirationDateException e) {
            System.out.println(e.getMessage());
        } catch (InvalidSecurityCodeException e) {
            System.out.println(e.getMessage());
        }catch (SystemNotWorkingException e) {
            System.out.println(e.getMessage());
        }
        try {
            pay();
        }catch (SystemNotWorkingException e) {
            System.out.println(e.getMessage());
        }

    }

    private static boolean isValidExpirationDate(String month, String year) {
        for (char c : month.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        int validMonth = Integer.parseInt(month);
        if (validMonth >= 13) {
            return false;
        }
        if (year.length() != 4) {
            return false;
        }
        for (char c : month.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        int validYear = Integer.parseInt(year);
        if (validYear < 2023) {
            return false;
        }
        return true;
    }

    private static boolean isValidPaymentAmount(String amount) {
        for (char c : amount.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        if (amount.contains(".")) {
            return false;
        }
        int payAmount = Integer.parseInt(amount);
        if ((payAmount < 0)) {
            return false;
        }
        return true;
    }

    private static boolean isValidCardNumber(String number) {
        if (number.length() != 16) {
            return false;
        }
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidSecurityCode(String code) {
        if (code.length() != 3) {
            return false;
        }
        for (char c : code.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    public static void pay() throws SystemNotWorkingException {
        int randomSayi = ((int) (Math.random() * 100) + 1);
        if (randomSayi > 75) {
            throw new SystemNotWorkingException("Ödeme işlemi başarısız");
        } else
            System.out.println("Ödeme işlemi başarılı");
    }


}