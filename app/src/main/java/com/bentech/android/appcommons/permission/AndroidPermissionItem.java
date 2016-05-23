package com.bentech.android.appcommons.permission;

import android.Manifest;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bendaniel on 23/05/2016.
 */
public class AndroidPermissionItem {
    private String permissionDescription;
    private String permissionConstant;


    public AndroidPermissionItem(String permissionDescription, String permissionConstant) {
        this.permissionDescription = permissionDescription;
        this.permissionConstant = permissionConstant;
    }

    public String getPermissionConstant() {
        return permissionConstant;
    }

    public void setPermissionConstant(String permissionConstant) {
        this.permissionConstant = permissionConstant;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public static List<AndroidPermissionItem> listDangerousPermissions() {
        List<AndroidPermissionItem> list = new ArrayList<>();

        list.add(new AndroidPermissionItem(PermissionUtil.getPermissionDescription(Manifest.permission.READ_PHONE_STATE), Manifest.permission.READ_PHONE_STATE));
        list.add(new AndroidPermissionItem(PermissionUtil.getPermissionDescription(Manifest.permission.ACCESS_FINE_LOCATION), Manifest.permission.ACCESS_FINE_LOCATION));
        list.add(new AndroidPermissionItem(PermissionUtil.getPermissionDescription(Manifest.permission.ACCESS_COARSE_LOCATION), Manifest.permission.ACCESS_COARSE_LOCATION));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            list.add(new AndroidPermissionItem(PermissionUtil.getPermissionDescription(Manifest.permission.READ_EXTERNAL_STORAGE), Manifest.permission.READ_EXTERNAL_STORAGE));
        }
        list.add(new AndroidPermissionItem(PermissionUtil.getPermissionDescription(Manifest.permission.WRITE_EXTERNAL_STORAGE), Manifest.permission.WRITE_EXTERNAL_STORAGE));

        return list;
    }
}
