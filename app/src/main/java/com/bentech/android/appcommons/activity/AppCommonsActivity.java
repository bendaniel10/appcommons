package com.bentech.android.appcommons.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.AppCommonsContext;
import com.bentech.android.appcommons.R;
import com.bentech.android.appcommons.activity.operations.ActivityOperations;
import com.bentech.android.appcommons.activity.operations.FeedbackOperations;
import com.bentech.android.appcommons.activity.operations.FragmentOperations;
import com.bentech.android.appcommons.config.AppCommonsConfiguration;
import com.bentech.android.appcommons.constants.alert.AlertLevel;
import com.bentech.android.appcommons.permission.AndroidPermissionItem;
import com.bentech.android.appcommons.permission.PermissionUtil;
import com.bentech.android.appcommons.utils.DeferredFragmentTransaction;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Created by Daniel on 07/11/2015.
 */
public class AppCommonsActivity extends AppCompatActivity implements ActivityOperations, FragmentOperations, FeedbackOperations {
    //PERMISSIONS
    private static final int PERMISSION_REQUEST_CODE = 2;
    private final AppCommonsConfiguration appCommonsConfiguration;
    Queue<DeferredFragmentTransaction> deferredFragmentTransactions = new ArrayDeque<>();
    private AppCommonsContext appCommonsContext;
    private String TAG = AppCommonsActivity.class.getSimpleName();
    private Snackbar currentlyDisplayedSnackbar;
    private boolean isRunning;

    public AppCommonsActivity() {
        appCommonsConfiguration = AppCommons.getAppCommonsConfiguration();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            setAppCommonsContext((AppCommonsContext) savedInstanceState.getSerializable(AppCommonsContext.class.getSimpleName()));
        }
        super.onCreate(savedInstanceState);

    }

    public AppCommonsConfiguration getAppCommonsConfiguration() {
        return appCommonsConfiguration;
    }

    @Override
    public AppCommonsContext getAppCommonsContext() {
        return appCommonsContext;
    }

    @Override
    public void setAppCommonsContext(AppCommonsContext appCommonsContext) {
        this.appCommonsContext = appCommonsContext;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void restartApp(Context context, Class starupClass) {
        Intent mStartActivity = new Intent(context, starupClass);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }

    @Override
    public void clearBackStack() {
        hideCurrentlyDisplayedSnackbar();
        // if there is a fragment and the back stack of this fragment is not empty,
        // then emulate 'onBackPressed' behaviour, because in default, it is not working
        if (!isRunning) {
            DeferredFragmentTransaction deferredFragmentTransaction = new DeferredFragmentTransaction() {
                @Override
                public void commit(AppCommonsActivity appCommonsActivity) {
                    clearBackStackInternal();
                }
            };
            deferredFragmentTransactions.add(deferredFragmentTransaction);
        } else {
            clearBackStackInternal();
        }
    }

    private void clearBackStackInternal() {

        FragmentManager fm = getSupportFragmentManager();
        if (fm.getFragments() == null) {
            Log.d(TAG, "No fragments to clear from back stack");
            return;
        }
        for (Fragment frag : fm.getFragments()) {
            if (frag != null && frag.isAdded()) {
                FragmentManager childFm = frag.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 0) {
                    Log.d(TAG, "Popping child fragment");
                    childFm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }
        }

        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void hideCurrentlyDisplayedSnackbar() {
        if (currentlyDisplayedSnackbar != null && currentlyDisplayedSnackbar.isShown()) {
            currentlyDisplayedSnackbar.dismiss();
        }
    }

    @Override
    public void popBackStack(FragmentManager fragmentManager) {
        if (fragmentManager.getBackStackEntryCount() > 0)
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void switchFragmentsAddToBackStack(int contentFrameId, android.support.v4.app.Fragment replacingFragment) {
        hideKeyBoard();
        hideCurrentlyDisplayedSnackbar();


        if (!isRunning) {
            //This will handle switching of fragments when the activity is paused. To prevent IllegalSTateExecption.
            //This transaction will be used in the resume part too.
            DeferredFragmentTransaction deferredFragmentTransaction = new DeferredFragmentTransaction() {
                @Override
                public void commit(AppCommonsActivity appCommonsActivity) {
                    switchFragmentsAddToBackStackInternal(getContentFrameId(), getReplacingFragment(), appCommonsActivity);
                }
            };

            deferredFragmentTransaction.setContentFrameId(contentFrameId);
            deferredFragmentTransaction.setReplacingFragment(replacingFragment);

            deferredFragmentTransactions.add(deferredFragmentTransaction);
        } else {
            switchFragmentsAddToBackStackInternal(contentFrameId, replacingFragment, this);
        }
    }

    private void switchFragmentsAddToBackStackInternal(int contentFrameId, Fragment replacingFragment, AppCommonsActivity appCommonsActivity) {

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(appCommonsConfiguration.getFragmentEnterAnimation(), appCommonsConfiguration.getFragmentExitAnimation(),
                        appCommonsConfiguration.getFragmentPopEnterAnimation(), appCommonsConfiguration.getFragmentPopExitAnimation())
                .replace(contentFrameId, replacingFragment)
                .addToBackStack(replacingFragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    @Override
    public void switchFragments(int contentFrameId, android.support.v4.app.Fragment replacingFragment) {
        hideKeyBoard();
        hideCurrentlyDisplayedSnackbar();

        if (!isRunning) {
            //This will handle switching of fragments when the activity is paused. To prevent IllegalSTateExecption.
            //This transaction will be used in the resume part too.
            DeferredFragmentTransaction deferredFragmentTransaction = new DeferredFragmentTransaction() {
                @Override
                public void commit(AppCommonsActivity appCommonsActivity) {
                    switchFragmentsInternal(getContentFrameId(), getReplacingFragment(), appCommonsActivity);
                }
            };

            deferredFragmentTransaction.setContentFrameId(contentFrameId);
            deferredFragmentTransaction.setReplacingFragment(replacingFragment);

            deferredFragmentTransactions.add(deferredFragmentTransaction);
        } else {
            switchFragmentsInternal(contentFrameId, replacingFragment, this);
        }
    }

    private void switchFragmentsInternal(int contentFrameId, Fragment replacingFragment, AppCommonsActivity appCommonsActivity) {
        FragmentManager fragmentManager = appCommonsActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(appCommonsConfiguration.getFragmentEnterAnimation(), appCommonsConfiguration.getFragmentExitAnimation(),
                        appCommonsConfiguration.getFragmentPopEnterAnimation(), appCommonsConfiguration.getFragmentPopExitAnimation())
                .replace(contentFrameId, replacingFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void switchChildFragmentsAddToBackStack(int contentFrameId, Fragment parentFragment, Fragment replacingFragment) {
        hideKeyBoard();
        hideCurrentlyDisplayedSnackbar();

        if (!isRunning) {
            //This will handle switching of fragments when the activity is paused. To prevent IllegalSTateExecption.
            //This transaction will be used in the resume part too.
            DeferredFragmentTransaction deferredFragmentTransaction = new DeferredFragmentTransaction() {
                @Override
                public void commit(AppCommonsActivity appCommonsActivity) {
                    switchChildFragmentsAddToBackStackInternal(getContentFrameId(), getParentFragment(), getReplacingFragment(), appCommonsActivity);
                }
            };

            deferredFragmentTransaction.setContentFrameId(contentFrameId);
            deferredFragmentTransaction.setReplacingFragment(replacingFragment);
            deferredFragmentTransaction.setParentFragment(parentFragment);

            deferredFragmentTransactions.add(deferredFragmentTransaction);
        } else {
            switchChildFragmentsAddToBackStackInternal(contentFrameId, parentFragment, replacingFragment, this);
        }
    }

    private void switchChildFragmentsAddToBackStackInternal(int contentFrameId, Fragment parentFragment, Fragment replacingFragment, AppCommonsActivity appCommonsActivity) {
        FragmentManager childFragmentManager = parentFragment.getChildFragmentManager();
        childFragmentManager.beginTransaction()
                .setCustomAnimations(appCommonsConfiguration.getFragmentEnterAnimation(), appCommonsConfiguration.getFragmentExitAnimation(),
                        appCommonsConfiguration.getFragmentPopEnterAnimation(), appCommonsConfiguration.getFragmentPopExitAnimation())
                .replace(contentFrameId, replacingFragment)
                .addToBackStack(replacingFragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    @Override
    public void switchChildFragments(int contentFrameId, Fragment parentFragment, Fragment replacingFragment) {
        hideKeyBoard();
        hideCurrentlyDisplayedSnackbar();

        if (!isRunning) {
            //This will handle switching of fragments when the activity is paused. To prevent IllegalSTateExecption.
            //This transaction will be used in the resume part too.
            DeferredFragmentTransaction deferredFragmentTransaction = new DeferredFragmentTransaction() {
                @Override
                public void commit(AppCommonsActivity appCommonsActivity) {
                    switchChildFragmentsInternal(getContentFrameId(), getParentFragment(), getReplacingFragment(), appCommonsActivity);
                }
            };

            deferredFragmentTransaction.setContentFrameId(contentFrameId);
            deferredFragmentTransaction.setReplacingFragment(replacingFragment);
            deferredFragmentTransaction.setParentFragment(parentFragment);

            deferredFragmentTransactions.add(deferredFragmentTransaction);
        } else {
            switchChildFragmentsInternal(contentFrameId, parentFragment, replacingFragment, this);
        }

    }

    private void switchChildFragmentsInternal(int contentFrameId, Fragment parentFragment, Fragment replacingFragment, AppCommonsActivity appCommonsActivity) {

        FragmentManager childFragmentManager = parentFragment.getChildFragmentManager();
        childFragmentManager.beginTransaction()
                .setCustomAnimations(appCommonsConfiguration.getFragmentEnterAnimation(), appCommonsConfiguration.getFragmentExitAnimation(),
                        appCommonsConfiguration.getFragmentPopEnterAnimation(), appCommonsConfiguration.getFragmentPopExitAnimation())
                .replace(contentFrameId, replacingFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    @Override
    public void hideToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void showToolBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }

    @Override
    public void setActionBarTitle(@StringRes int messageId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(messageId);
        }
    }

    @Override
    public void setActionBarTitle(String message) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(message);
        }
    }

    @Override
    public void showFragmentDialog(final DialogFragment dialogFragment) {
        if (!isRunning) {

            DeferredFragmentTransaction deferredFragmentTransaction = new DeferredFragmentTransaction() {
                @Override
                public void commit(AppCommonsActivity appCommonsActivity) {
                    showFragmentDialogInternal(dialogFragment);
                }
            };
            deferredFragmentTransactions.add(deferredFragmentTransaction);
        } else {
            showFragmentDialogInternal(dialogFragment);
        }
    }

    private void showFragmentDialogInternal(DialogFragment dialogFragment) {
        dialogFragment.show(this.getSupportFragmentManager(), dialogFragment.getTag());
    }


    @Override
    public void dismissFragmentDialog(final DialogFragment dialogFragment) {
        if (!isRunning) {

            DeferredFragmentTransaction deferredFragmentTransaction = new DeferredFragmentTransaction() {
                @Override
                public void commit(AppCommonsActivity appCommonsActivity) {
                    dismissFragmentDialogInternal(dialogFragment);
                }
            };
            deferredFragmentTransactions.add(deferredFragmentTransaction);

        } else {
            dismissFragmentDialogInternal(dialogFragment);
        }
    }

    private void dismissFragmentDialogInternal(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }


    @Override
    public Snackbar showShortSnackBar(View view, int messageId, int actionLabel, final View.OnClickListener onClickListener, AlertLevel alertLevel) {
        final Snackbar snackBar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_SHORT);
        View snackView = snackBar.getView();
        buildSnackbar(snackView, alertLevel);
        snackBar.setActionTextColor(appCommonsConfiguration.getSnackbarActionTextColor());
        snackBar.setAction(actionLabel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
                snackBar.dismiss();
            }
        });
        snackBar.show();
        this.currentlyDisplayedSnackbar = snackBar;
        return snackBar;
    }

    @Override
    public Snackbar showShortSnackBar(View view, int messageId, AlertLevel alertLevel) {
        Snackbar snackbar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_SHORT);
        View snackView = snackbar.getView();
        buildSnackbar(snackView, alertLevel);
        snackbar.show();
        this.currentlyDisplayedSnackbar = snackbar;
        return snackbar;
    }

    @Override
    public Snackbar showIndefiniteSnackBar(View view, int messageId, int actionLabel, AlertLevel alertLevel) {
        final Snackbar snackBar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_INDEFINITE);
        View snackView = snackBar.getView();
        buildSnackbar(snackView, alertLevel);
        snackBar.setActionTextColor(appCommonsConfiguration.getSnackbarActionTextColor());
        snackBar.setAction(actionLabel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBar.dismiss();
            }
        });
        snackBar.show();
        this.currentlyDisplayedSnackbar = snackBar;
        return snackBar;
    }

    @Override
    public Snackbar showIndefiniteSnackBar(View view, int messageId, AlertLevel alertLevel) {
        final Snackbar snackBar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_INDEFINITE);
        View snackView = snackBar.getView();
        buildSnackbar(snackView, alertLevel);
        snackBar.setActionTextColor(appCommonsConfiguration.getSnackbarActionTextColor());
        snackBar.setAction(R.string.label_dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBar.dismiss();
            }
        });
        snackBar.show();
        this.currentlyDisplayedSnackbar = snackBar;
        return snackBar;
    }

    @Override
    public Snackbar showIndefiniteSnackBar(View view, int messageId, final View.OnClickListener onClickListener, AlertLevel alertLevel) {
        final Snackbar snackBar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_INDEFINITE);
        View snackView = snackBar.getView();
        buildSnackbar(snackView, alertLevel);
        snackBar.setActionTextColor(appCommonsConfiguration.getSnackbarActionTextColor());
        snackBar.setAction(R.string.label_dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
                snackBar.dismiss();
            }
        });
        snackBar.show();
        this.currentlyDisplayedSnackbar = snackBar;
        return snackBar;
    }

    @Override
    public Snackbar showIndefiniteSnackBar(View view, int messageId, int actionLabel, final View.OnClickListener onClickListener, AlertLevel alertLevel) {
        final Snackbar snackBar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_INDEFINITE);
        View snackView = snackBar.getView();
        buildSnackbar(snackView, alertLevel);
        snackBar.setActionTextColor(appCommonsConfiguration.getSnackbarActionTextColor());
        snackBar.setAction(actionLabel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
                snackBar.dismiss();
            }
        });
        snackBar.show();
        this.currentlyDisplayedSnackbar = snackBar;
        return snackBar;
    }

    @Override
    public Snackbar showLongSnackBar(View view, int messageId, AlertLevel alertLevel) {
        Snackbar snackbar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        buildSnackbar(snackView, alertLevel);
        snackbar.show();
        this.currentlyDisplayedSnackbar = snackbar;
        return snackbar;
    }

    private void buildSnackbar(View snackView, AlertLevel alertLevel) {
        TextView tv = (TextView)
                snackView.findViewById(R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(this, alertLevel.getFgColor()));
        snackView.setBackgroundColor(ContextCompat.getColor(this, alertLevel.getBgColor()));
        tv.setTextSize(appCommonsConfiguration.getSnackbarTextSize());
    }

    @Override
    public void showLongToast(int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showShortToast(int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(AppCommonsContext.class.getSimpleName(), appCommonsContext);
    }

    private void forceUserToEnableRequiredPermissions() {
        List<AndroidPermissionItem> notGrantedPermissions;
        if (!(notGrantedPermissions = PermissionUtil.listNotGrantedPermissions(appCommonsConfiguration.getDangerousPermissions())).isEmpty()) {
            handleEnablePermissions(notGrantedPermissions);
        }
    }

    public void handleEnablePermissions(List<AndroidPermissionItem> notGrantedPermissions) {
        String[] requestedPermissions = extractRequestedPermissions(notGrantedPermissions);

        ActivityCompat.requestPermissions(this,
                requestedPermissions,
                PERMISSION_REQUEST_CODE);
    }

    private String[] extractRequestedPermissions(List<AndroidPermissionItem> notGrantedPermissions) {
        String[] requestedPermissions = new String[notGrantedPermissions.size()];
        int i = 0;
        for (AndroidPermissionItem permissionItem : notGrantedPermissions) {
            requestedPermissions[i++] = permissionItem.getPermissionConstant();
        }
        return requestedPermissions;
    }

    public void navigateBack() {
        // if there is a fragment and the back stack of this fragment is not empty,
        // then emulate 'onBackPressed' behaviour, because in default, it is not working
        hideCurrentlyDisplayedSnackbar();
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getFragments() == null) {
            super.onBackPressed();
            return;
        }
        for (Fragment frag : fm.getFragments()) {
            if (frag != null && frag.isVisible()) {
                FragmentManager childFm = frag.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 0) {
                    popBackStackImmediateInternal(childFm);
                    return;
                } else {
                    if (fm.getBackStackEntryCount() > 0) {
                        popBackStackImmediateInternal(fm);
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
    }

    protected void popBackStackImmediateInternal(final FragmentManager fragmentManager) {

        if (!isRunning) {

            DeferredFragmentTransaction deferredFragmentTransaction = new DeferredFragmentTransaction() {
                @Override
                public void commit(AppCommonsActivity appCommonsActivity) {
                    fragmentManager.popBackStackImmediate();
                }
            };
            deferredFragmentTransactions.add(deferredFragmentTransaction);
        } else {
            fragmentManager.popBackStackImmediate();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Permissions are all set, you're good to go");
            } else {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                this.startActivity(intent);
                showLongToast(appCommonsConfiguration.getEnableRequiredPermissionsMessage());
            }
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        while (!deferredFragmentTransactions.isEmpty()) {
            if(!isFinishing()) {
                deferredFragmentTransactions.remove().commit(this);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
        forceUserToEnableRequiredPermissions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }
}
