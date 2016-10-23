package utils;

import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.impl.CollectionASCIITableAware;
import com.bethecoder.ascii_table.spec.IASCIITableAware;
import models.Activity;
import models.User;

import java.util.List;

/**
 * Created by annadowling on 09/10/2016.
 * The ASCIIFormatter class provides methods which format the command line output into ascii tables.
 * Methods are available for both User and Activity formats.
 */

public class ASCIIFormatter {

    /**
     * @param userList
     * @return String asciiTable
     * Method takes a list of users as parameters.
     * The user fields are then retrieved using CollectionASCIITableAware, which takes the list and the specified fields.
     * The output is then formatted as an instance of ASCIITable.
     */
    public String formatUsers(List<User> userList) {
        String asciiTable = "";

        if (!userList.isEmpty()) {
            IASCIITableAware asciiTableAware = new CollectionASCIITableAware<User>(userList, "id", "firstname", "lastname", "email", "password");
            asciiTable = ASCIITable.getInstance().getTable(asciiTableAware);
        }
        return asciiTable;
    }

    /**
     * @param  activityList
     * @return String asciiTable
     * Method takes a list of activities as parameters.
     * The activity fields are then retrieved using CollectionASCIITableAware, which takes the list and the specified fields.
     * The output is then formatted as an instance of ASCIITable.
     */
    public String formatActivity(List<Activity> activityList) {
        String asciiTable = "";

        if (!activityList.isEmpty()) {
            IASCIITableAware asciiTableAware = new CollectionASCIITableAware<Activity>(activityList, "id", "type", "location", "distance", "starttime", "duration", "route");
            asciiTable = ASCIITable.getInstance().getTable(asciiTableAware);
        }
        return asciiTable;
    }
}
