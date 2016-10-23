package comparator;

import models.Activity;

import java.util.Comparator;
import java.util.List;

/**
 * Created by annadowling on 14/10/2016.
 * ComparatorLibrary class handles sorting used for listing Activities by certain parameters.
 */

public class ComparatorLibrary {

    /**
     * Sorts a list of Activity by comparing the starttime field of one instance to another.
     * @param activityList
     */
    public void sortActivityByStartTime(List<Activity> activityList) {
        activityList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity activity1, Activity activity2) {
                return activity1.getStarttime().compareTo(activity2.getStarttime());
            }
        });
    }

    /**
     * Sorts a list of Activity by comparing the duration field of one instance to another.
     * @param activityList
     */
    public void sortActivityByDuration(List<Activity> activityList) {
        activityList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity activity1, Activity activity2) {
                return activity1.getDuration().compareTo(activity2.getDuration());
            }
        });
    }

    /**
     * Sorts a list of Activity by comparing the type field of one instance to another.
     * @param activityList
     */
    public void sortActivityByType(List<Activity> activityList) {
        activityList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity activity1, Activity activity2) {
                return activity1.getType().compareTo(activity2.getType());
            }
        });
    }

    /**
     * Sorts a list of Activity by comparing the location field of one instance to another.
     * @param activityList
     */
    public void sortActivityByLocation(List<Activity> activityList) {
        activityList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity activity1, Activity activity2) {
                return activity1.getLocation().compareTo(activity2.getLocation());
            }
        });
    }

    /**
     * Sorts a list of Activity by comparing the distance field of one instance to another.
     * @param activityList
     */
    public void sortActivityByDistance(List<Activity> activityList) {
        activityList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity activity1, Activity activity2) {
                return Double.compare(activity1.getDistance(), activity2.getDistance());
            }
        });
    }
}
