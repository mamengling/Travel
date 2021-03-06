package com.jcool.dev.travel.utils;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * ActivityCollector
 *
 * @author W_X
 * @date 2018/5/25 0025 15:16
 */
public class ActivityCollector {

    private static ActivityCollector activityCollector;

    public synchronized static ActivityCollector getInstance() {
        if (activityCollector == null) {
            activityCollector = new ActivityCollector();
        }
        return activityCollector;
    }

    private Set<Activity> allActivities;
    private Set<Activity> allExitActivities;

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }


    public void flagActivity(Activity act) {
        if (allExitActivities == null) {
            allExitActivities = new HashSet<>();
        }
        allExitActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void finishAllActivities() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
    }

    public void finishFlagActivities() {
        if (allExitActivities != null) {
            synchronized (allExitActivities) {
                for (Activity act : allExitActivities) {
                    act.finish();
                }
            }
        }
    }

}
