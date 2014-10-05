package com.beeno.trucktracker.model;

import android.util.SparseArray;

import com.beeno.trucktracker.CreateUserActivity;
import com.beeno.trucktracker.UserManagementActivity;

/**
 * Created by dcharp on 10/4/14.
 */
public class AdminConsoleItems {
    static SparseArray<Class> listItems;
    public  static String[] listItemsTitle = {"User Management", "GPS Settings", "Settings"};

    public static void initList() {
        listItems = new SparseArray<Class>();
        listItems.put(0, UserManagementActivity.class);
    }

    public static Class getClass(int index) {
        if(listItems == null) {
            initList();
        }
        return listItems.get(index);
    }
}
