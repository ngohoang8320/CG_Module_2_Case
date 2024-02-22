package services.userservice;

import controller.gate.GateController;
import entity.user.CurrentUser;
import view.NewPage;

public class LogOut {
    public void out() {
        CurrentUser.resetCurrentUser();
        NewPage.newPage();
        GateController.gateController();
    }
}
