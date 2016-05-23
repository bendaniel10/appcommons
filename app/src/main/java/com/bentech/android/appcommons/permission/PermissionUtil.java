package com.bentech.android.appcommons.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.bentech.android.appcommons.AppCommons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bendaniel on 23/05/2016.
 */
public class PermissionUtil {

    private static final String TAG = PermissionUtil.class.getSimpleName();


    public static List<AndroidPermissionItem> listNotGrantedPermissions(List<AndroidPermissionItem> androidPermissionItemList) {
        List<AndroidPermissionItem> notGrantedPermissions = new ArrayList<>();
        Context context = AppCommons.getApplication().getApplicationContext();

        if (androidPermissionItemList == null || androidPermissionItemList.isEmpty()) {
            return notGrantedPermissions;
        }

        for (AndroidPermissionItem androidPermissionItem : androidPermissionItemList) {
            if (checkIfPermissionIsNotGranted(context, androidPermissionItem.getPermissionConstant())) {
                notGrantedPermissions.add(androidPermissionItem);
            }
        }

        return notGrantedPermissions;
    }

    /**
     * Iterate through the requested permissions and returns true if all the permissions are not granted.
     *
     * @param context
     * @param requestedPermissions
     * @return
     */
    public static boolean checkIfPermissionIsNotGranted(Context context, String... requestedPermissions) {
        boolean isNotGranted = false;

        for (String onePermission : requestedPermissions) {
            isNotGranted = ActivityCompat.checkSelfPermission(context, onePermission) != PackageManager.PERMISSION_GRANTED;
            Log.d(TAG, onePermission + " : " + !isNotGranted);
            if (isNotGranted) {
                break;
            }
        }

        return isNotGranted;
    }

    //http://stackoverflow.com/a/16872728
    public static String getPermissionDescription(String permissionId) {
        PackageManager packageManager = AppCommons.getApplication().getPackageManager();
        PermissionInfo pinfo;
        try {
            pinfo = packageManager.getPermissionInfo(permissionId, PackageManager.GET_META_DATA);
            return pinfo.loadLabel(packageManager).toString();

        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, e.getMessage());
        }
        return permissionId;
    }
}
