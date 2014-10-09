package com.beeno.trucktracker.model.admin;

import android.util.SparseArray;
import com.beeno.trucktracker.activity.admin.task.TaskManagementActivity;
import com.beeno.trucktracker.activity.admin.user.UserManagementActivity;

/**
 * Created by dcharp on 10/4/14.
 */
public class AdminConsoleItems {
    static SparseArray<Class> listItems;
    public  static String[] listItemsTitle = {"Manage Pick up orders", "User Management"};

    public static void initList() {
        listItems = new SparseArray<Class>();
        listItems.put(0, TaskManagementActivity.class);
        listItems.put(1, UserManagementActivity.class);
    }

    public static Class getClass(int index) {
        if(listItems == null) {
            initList();
        }
        return listItems.get(index);
    }
}
