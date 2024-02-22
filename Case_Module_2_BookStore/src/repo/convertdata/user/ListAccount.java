package repo.convertdata.user;

import entity.user.Customer;
import entity.user.Owner;
import entity.user.UserClass;
import repo.getsetdata.GetSetData;
import repo.getsetdata.user.GetSetAccount;

import java.util.ArrayList;
import java.util.List;

public class ListAccount {
    private GetSetData accounts = new GetSetAccount();
    private List<String> stringAccountList = accounts.getData();
    private List<UserClass> accountList = new ArrayList<>();

    public ListAccount() {
        setAccountList();
    }

    public List<UserClass> getAccountList() {
        return accountList;
    }

    public void setAccountList() {
        for (String acc : stringAccountList) {
            String[] accInfor = acc.split(",");
            UserClass userClass;

            if (accInfor[4].trim().equals("Owner")) {
                userClass = new Owner();
            } else {
                userClass = new Customer();
            }

            userClass.setUserName(accInfor[0].trim());
            userClass.setPassword(accInfor[1].trim());
            userClass.setPhoneNumber(accInfor[2].trim());
            userClass.setEmail(accInfor[3].trim());
            userClass.setMoney(Double.parseDouble(accInfor[5].trim()));

            accountList.add(userClass);
        }
    }
}
