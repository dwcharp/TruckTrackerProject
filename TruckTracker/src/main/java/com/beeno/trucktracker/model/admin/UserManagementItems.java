package com.beeno.trucktracker.model.admin;

import android.util.SparseArray;

import com.beeno.trucktracker.activity.admin.user.CreateUserActivity;

/**
 * Created by dcharp on 10/4/14.
 */
public class UserManagementItems {
    static SparseArray<Class> listItems;
    public  static String[] listItemsTitle = {"Create User"};

    public static void initList() {
        listItems = new SparseArray<Class>();
        listItems.put(0, CreateUserActivity.class);
    }

    public static Class getClass(int index) {
        if(listItems == null) {
            initList();
        }
        return listItems.get(index);
    }
}
