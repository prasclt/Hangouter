package com.microsoft.oneweek.hangouter.Interactors;

import com.microsoft.oneweek.hangouter.Interfaces.IUsersInteractor;
import com.microsoft.oneweek.hangouter.Models.User;
import com.microsoft.oneweek.hangouter.Utils.HangouterUtils;

import java.util.List;

/**
 * Created by prmeno on 7/25/2017.
 */

public class GroupUsersInteractor implements IUsersInteractor {
    @Override
    public List<User> getUserQuery() {
        return HangouterUtils.fetchDummyUsers();
    }
}
