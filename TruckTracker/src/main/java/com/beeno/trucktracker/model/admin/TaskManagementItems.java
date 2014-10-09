package com.beeno.trucktracker.model.admin;

import android.util.SparseArray;

import com.beeno.trucktracker.activity.admin.task.CreatePickUpTaskActivity;

/**
 * Created by dcharp on 10/8/14.
 */
public class TaskManagementItems {

    static SparseArray<Class> listItems;
    public  static String[] listItemsTitle = {"Create Order"};

    public static void initList() {
        listItems = new SparseArray<Class>();
        listItems.put(0, CreatePickUpTaskActivity.class);
    }

    public static Class getClass(int index) {
        if(listItems == null) {
            initList();
        }
        return listItems.get(index);
    }
}
