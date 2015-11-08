package com.bentech.android.appcommons.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by Daniel on 07/11/2015.
 */
public class AppCommonsActivity extends AppCompatActivity implements ActivityOperations, FragmentOperations, FeedbackOperations {
    private final AppCommonsConfiguration appCommonsConfiguration;
    private AppCommonsContext appCommonsContext;

    public AppCommonsActivity() {
        appCommonsConfiguration = AppCommons.getAppCommonsConfiguration();
    }

    public AppCommonsConfiguration getAppCommonsConfiguration() {
        return appCommonsConfiguration;
    }

    @Override
    public AppCommonsContext getAppCommonsContext() {
        return appCommonsContext;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void setAppCommonsContext(AppCommonsContext appCommonsContext) {
        this.appCommonsContext = appCommonsContext;
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
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void popBackStack(FragmentManager fragmentManager) {
        if (fragmentManager.getBackStackEntryCount() > 0)
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void switchFragmentsAddToBackStack(int contentFrameId, android.support.v4.app.Fragment replacingFragment) {
        hideKeyBoard();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(appCommonsConfiguration.getFragmentEnterAnimation(), appCommonsConfiguration.getFragmentExitAnimation(),
                        appCommonsConfiguration.getFragmentPopEnterAnimation(), appCommonsConfiguration.getFragmentPopExitAnimation())
                .replace(contentFrameId, replacingFragment)
                .addToBackStack(replacingFragment.getClass().getSimpleName())
                .commit();
    }


    @Override
    public void switchFragments(int contentFrameId, android.support.v4.app.Fragment replacingFragment) {
        hideKeyBoard();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(appCommonsConfiguration.getFragmentEnterAnimation(), appCommonsConfiguration.getFragmentExitAnimation(),
                        appCommonsConfiguration.getFragmentPopEnterAnimation(), appCommonsConfiguration.getFragmentPopExitAnimation())
                .replace(contentFrameId, replacingFragment)
                .commit();
    }

    @Override
    public void switchChildFragmentsAddToBackStack(int contentFrameId, Fragment parentFragment, Fragment replacingFragment) {
        hideKeyBoard();
        FragmentManager childFragmentManager = parentFragment.getChildFragmentManager();
        childFragmentManager.beginTransaction()
                .setCustomAnimations(appCommonsConfiguration.getFragmentEnterAnimation(), appCommonsConfiguration.getFragmentExitAnimation(),
                        appCommonsConfiguration.getFragmentPopEnterAnimation(), appCommonsConfiguration.getFragmentPopExitAnimation())
                .replace(contentFrameId, replacingFragment)
                .addToBackStack(replacingFragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void switchChildFragments(int contentFrameId, Fragment parentFragment, Fragment replacingFragment) {
        hideKeyBoard();
        FragmentManager childFragmentManager = parentFragment.getChildFragmentManager();
        childFragmentManager.beginTransaction()
                .setCustomAnimations(appCommonsConfiguration.getFragmentEnterAnimation(), appCommonsConfiguration.getFragmentExitAnimation(),
                        appCommonsConfiguration.getFragmentPopEnterAnimation(), appCommonsConfiguration.getFragmentPopExitAnimation())
                .replace(contentFrameId, replacingFragment)
                .commit();
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
    public void showFragmentDialog(DialogFragment dialogFragment) {
        dialogFragment.show(this.getSupportFragmentManager(), dialogFragment.getTag());
    }

    @Override
    public Snackbar showShortSnackBar(View view, int messageId, int actionLabel, final View.OnClickListener onClickListener) {
        final Snackbar snackBar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_SHORT);
        View snackView = snackBar.getView();
        TextView tv = (TextView)
                snackView.findViewById(R.id.snackbar_text);
        tv.setTextColor(appCommonsConfiguration.getSnackbarTextColor());
        tv.setTextSize(appCommonsConfiguration.getSnackbarTextSize());
        snackBar.setActionTextColor(appCommonsConfiguration.getSnackbarActionTextColor());
        snackBar.setAction(actionLabel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
                snackBar.dismiss();
            }
        });
        snackBar.show();
        return snackBar;
    }

    @Override
    public Snackbar showShortSnackBar(View view, int messageId) {
        Snackbar snackbar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_SHORT);
        View snackView = snackbar.getView();
        TextView tv = (TextView)
                snackView.findViewById(R.id.snackbar_text);
        tv.setTextColor(appCommonsConfiguration.getSnackbarTextColor());
        tv.setTextSize(appCommonsConfiguration.getSnackbarTextSize());
        snackbar.show();
        return snackbar;
    }

    @Override
    public Snackbar showIndefiniteSnackBar(View view, int messageId, int actionLabel) {
        final Snackbar snackBar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_INDEFINITE);
        View snackView = snackBar.getView();
        TextView tv = (TextView)
                snackView.findViewById(R.id.snackbar_text);
        tv.setTextColor(appCommonsConfiguration.getSnackbarTextColor());
        tv.setTextSize(appCommonsConfiguration.getSnackbarTextSize());
        snackBar.setActionTextColor(appCommonsConfiguration.getSnackbarActionTextColor());
        snackBar.setAction(actionLabel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBar.dismiss();
            }
        });
        snackBar.show();
        return snackBar;
    }

    @Override
    public Snackbar showIndefiniteSnackBar(View view, int messageId) {
        final Snackbar snackBar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_INDEFINITE);
        View snackView = snackBar.getView();
        TextView tv = (TextView)
                snackView.findViewById(R.id.snackbar_text);
        tv.setTextColor(appCommonsConfiguration.getSnackbarTextColor());
        tv.setTextSize(appCommonsConfiguration.getSnackbarTextSize());
        snackBar.setActionTextColor(appCommonsConfiguration.getSnackbarActionTextColor());
        snackBar.setAction(R.string.label_dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBar.dismiss();
            }
        });
        snackBar.show();
        return snackBar;
    }

    @Override
    public Snackbar showIndefiniteSnackBar(View view, int messageId, final View.OnClickListener onClickListener) {
        final Snackbar snackBar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_INDEFINITE);
        View snackView = snackBar.getView();
        TextView tv = (TextView)
                snackView.findViewById(R.id.snackbar_text);
        tv.setTextColor(appCommonsConfiguration.getSnackbarTextColor());
        tv.setTextSize(appCommonsConfiguration.getSnackbarTextSize());
        snackBar.setActionTextColor(appCommonsConfiguration.getSnackbarActionTextColor());
        snackBar.setAction(R.string.label_dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
                snackBar.dismiss();
            }
        });
        snackBar.show();

        return snackBar;
    }


    @Override
    public Snackbar showIndefiniteSnackBar(View view, int messageId, int actionLabel, final View.OnClickListener onClickListener) {
        final Snackbar snackBar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_INDEFINITE);
        View snackView = snackBar.getView();
        TextView tv = (TextView)
                snackView.findViewById(R.id.snackbar_text);
        tv.setTextColor(appCommonsConfiguration.getSnackbarTextColor());
        tv.setTextSize(appCommonsConfiguration.getSnackbarTextSize());
        snackBar.setActionTextColor(appCommonsConfiguration.getSnackbarActionTextColor());
        snackBar.setAction(actionLabel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
                snackBar.dismiss();
            }
        });
        snackBar.show();
        return snackBar;
    }

    @Override
    public Snackbar showLongSnackBar(View view, int messageId) {
        Snackbar snackbar = Snackbar.make(view, getString(messageId), Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        TextView tv = (TextView)
                snackView.findViewById(R.id.snackbar_text);
        tv.setTextColor(appCommonsConfiguration.getSnackbarTextColor());
        tv.setTextSize(appCommonsConfiguration.getSnackbarTextSize());
        snackbar.show();

        return snackbar;
    }

    @Override
    public void showLongToast(int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_LONG).show();
    }

}
