package services.userservice.owner_account;

import entity.user.Owner;
import entity.user.UserClass;
import repo.convertdata.user.MapUsernameUser;
import repo.getsetdata.user.FormatUserData;
import repo.getsetdata.user.GetSetAccount;
import services.userservice.CheckUsername;

import java.util.Map;

public class SetOwnerAccount extends Owner {
    private static Owner ownerAccount;

    private SetOwnerAccount() {
    }

    public static Owner getInstance() {
        if (ownerAccount == null) {
            ownerAccount = new Owner();
            ownerAccount.setUserName("Ngohoang8320");
            if (CheckUsername.check(ownerAccount.getUserName())) {
                setOwnerExisted();
            } else {
                ownerAccount.setPassword("Ngohoang8320@");
                ownerAccount.setPhoneNumber("0123456789");
                ownerAccount.setEmail("ngohoang8320@gmail.com");
            }
            String entity = FormatUserData.formatted(ownerAccount.getUserName(),
                    ownerAccount.getPassword(),
                    ownerAccount.getPhoneNumber(),
                    ownerAccount.getEmail(),
                    ownerAccount.getRole(),
                    ownerAccount.getMoney());

            GetSetAccount account = new GetSetAccount();
            if (!CheckUsername.check(ownerAccount.getUserName())) {
                account.setData(entity);
            }
        }
        return ownerAccount;
    }

    private static void setOwnerExisted() {
        MapUsernameUser mapUsernameUser = new MapUsernameUser();
        Map<String, UserClass> mapUser = mapUsernameUser.getMapUsernameUser();
        ownerAccount.setPassword(mapUser.get(ownerAccount.getUserName()).getPassword());
        ownerAccount.setPhoneNumber(mapUser.get(ownerAccount.getUserName()).getPhoneNumber());
        ownerAccount.setEmail(mapUser.get(ownerAccount.getUserName()).getEmail());
        ownerAccount.setMoney(mapUser.get(ownerAccount.getUserName()).getMoney());
    }
}
