package com.beeno.trucktracker.model.admin;

import android.util.SparseArray;

import com.beeno.trucktracker.activity.admin.task.CreatePickUpTaskActivity;
import com.beeno.trucktracker.activity.admin.task.PickupTaskViewActivity;

/**
 * Created by dcharp on 10/8/14.
 */
public class TaskManagementItems {

    static SparseArray<Class> listItems;
    public  static String[] listItemsTitle = {"Views Orders", "Create Order"};

    public static void initList() {
        listItems = new SparseArray<Class>();
        listItems.put(0, PickupTaskViewActivity.class);
        listItems.put(1, CreatePickUpTaskActivity.class);
    }

    public static Class getClass(int index) {
        if(listItems == null) {
            initList();
        }
        return listItems.get(index);
    }
}
