package services.userservice.owner_account;

import entity.voucher.VoucherInMultiDate;

import java.util.Scanner;

public class CreateNewVoucher {
    Scanner input = new Scanner(System.in);

    public void voucherForMultiDate() {
        VoucherInMultiDate voucherInMultiDate = new VoucherInMultiDate();

        System.out.print("Enter code: ");
        String code = input.nextLine();
        voucherInMultiDate.setCode(code);

        System.out.print("Enter percentage discount: ");
        double percent = input.nextDouble();
        input.nextLine();
        voucherInMultiDate.setPercentageDiscount(percent);

        System.out.print("Enter quantity: ");
        int quantity = input.nextInt();
        input.nextLine();
        voucherInMultiDate.setQuantity(quantity);


    }
}
