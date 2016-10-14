package comparator;

import models.Activity;

import java.util.Comparator;
import java.util.List;

/**
 * Created by annadowling on 14/10/2016.
 */

public class ComparatorLibrary {

    public void sortActivityByStartTime(List<Activity> activityList) {
        activityList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity activity1, Activity activity2) {
                return activity1.getStarttime().compareTo(activity2.getStarttime());
            }
        });
    }

    public void sortActivityByDuration(List<Activity> activityList) {
        activityList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity activity1, Activity activity2) {
                return activity1.getDuration().compareTo(activity2.getDuration());
            }
        });
    }

    public void sortActivityByType(List<Activity> activityList) {
        activityList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity activity1, Activity activity2) {
                return activity1.getType().compareTo(activity2.getType());
            }
        });
    }

    public void sortActivityByLocation(List<Activity> activityList) {
        activityList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity activity1, Activity activity2) {
                return activity1.getLocation().compareTo(activity2.getLocation());
            }
        });
    }

    public void sortActivityByDistance(List<Activity> activityList) {
        activityList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity activity1, Activity activity2) {
                return Double.compare(activity1.getDistance(), activity2.getDistance());
            }
        });
    }
}
