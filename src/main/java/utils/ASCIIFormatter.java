package utils;/***************************************************************
 * Copyright (c) 2016 Errigal Inc.
 * <p>
 * This software is the confidential and proprietary information
 * of Errigal, Inc.  You shall not disclose such confidential
 * information and shall use it only in accordance with the
 * license agreement you entered into with Errigal.
 * <p>
 * **************************************************************
 */

import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.impl.CollectionASCIITableAware;
import com.bethecoder.ascii_table.spec.IASCIITableAware;
import models.Activity;
import models.User;

import java.util.List;

/**
 * Created by annadowling on 09/10/2016.
 */

public class ASCIIFormatter {

    public String formatUsers(List<User> userList){
        String asciiTable = "";

        if (!userList.isEmpty()) {
            IASCIITableAware asciiTableAware = new CollectionASCIITableAware<User>(userList, "id", "firstname", "lastname", "email", "password");
            asciiTable = ASCIITable.getInstance().getTable(asciiTableAware);
        }
        return asciiTable;
    }

    public String formatActivity(List<Activity> activityList){
        String asciiTable = "";

        if (!activityList.isEmpty()) {
            IASCIITableAware asciiTableAware = new CollectionASCIITableAware<Activity>(activityList, "id", "type", "location", "distance", "route");
            asciiTable = ASCIITable.getInstance().getTable(asciiTableAware);
        }
        return asciiTable;
    }
}
