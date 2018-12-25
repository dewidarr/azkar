package com.azkara.hp.azkar.Util;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.azkara.hp.azkar.Model.Azkary;
import com.azkara.hp.azkar.Model.DailyTask;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Reciever.NotoficationReciever;
import com.azkara.hp.azkar.Reciever.OverLayWindowReceiver;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.azkara.hp.azkar.Util.Constants.IntentStrings.ZekrType;

/**
 * Created by NaderNabil216@gmail.com on 7/15/2018.
 */
public class GeneralMethods {
    public static void initOverLayZekrAlarm(Context context) {
        int repeatTime = SharedPrefManager.getInstance().doStuff(context).getAlarmManagerRepeatTime();
        if (repeatTime != 0) {
            Intent startIntent = new Intent(context, OverLayWindowReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 4, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Calendar overLayCalendar = SharedPrefManager.getInstance().doStuff(context).getOverlayCalendar();
            if(Build.VERSION.SDK_INT < 23){
                if(Build.VERSION.SDK_INT >= 19){
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,Calendar.getInstance().getTimeInMillis()+1000,pendingIntent);
                }
                else{
                    alarmManager.set(AlarmManager.RTC_WAKEUP,/*overLayCalendar.getTimeInMillis()*/Calendar.getInstance().getTimeInMillis()+1000,pendingIntent);
                }
            }
            else{
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,Calendar.getInstance().getTimeInMillis()+1000,pendingIntent);
            }
        }
    }

    public static void removeOverLayZekrAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent startIntent = new Intent(context, OverLayWindowReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), 4, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

    public static void initAzkarSabahAlarm(Context context) {
        Calendar zekrCalendar = SharedPrefManager.getInstance().doStuff(context).getAzkarSabahHour();
        Intent startIntent = new Intent(context, NotoficationReciever.class);
        startIntent.putExtra(ZekrType, 1);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

         if(Build.VERSION.SDK_INT < 23){
            if(Build.VERSION.SDK_INT >= 19){
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,zekrCalendar.getTimeInMillis(),pendingIntent);
            }
            else{
                alarmManager.set(AlarmManager.RTC_WAKEUP,zekrCalendar.getTimeInMillis(),pendingIntent);
            }
        }
        else{
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,zekrCalendar.getTimeInMillis(),pendingIntent);
        }
    }

    public static void initAzkarMasaaAlarm(Context context) {
        Calendar zekrCalendar = SharedPrefManager.getInstance().doStuff(context).getAzkarMesaaHour();
        Intent startIntent = new Intent(context, NotoficationReciever.class);
        startIntent.putExtra(ZekrType, 2);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 2, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if(Build.VERSION.SDK_INT < 23){
            if(Build.VERSION.SDK_INT >= 19){
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,zekrCalendar.getTimeInMillis(),pendingIntent);
            }
            else{
                alarmManager.set(AlarmManager.RTC_WAKEUP,zekrCalendar.getTimeInMillis(),pendingIntent);
            }
        }
        else{
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,zekrCalendar.getTimeInMillis(),pendingIntent);
        }
    }

    public static void initAzkarNoomAlarm(Context context) {
        Calendar zekrCalendar = SharedPrefManager.getInstance().doStuff(context).getAzkarNoomHour();
        Intent startIntent = new Intent(context, NotoficationReciever.class);
        startIntent.putExtra(ZekrType, 3);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 3, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if(Build.VERSION.SDK_INT < 23){
            if(Build.VERSION.SDK_INT >= 19){
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,zekrCalendar.getTimeInMillis(),pendingIntent);
            }
            else{
                alarmManager.set(AlarmManager.RTC_WAKEUP,zekrCalendar.getTimeInMillis(),pendingIntent);
            }
        }
        else{
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,zekrCalendar.getTimeInMillis(),pendingIntent);
        }
    }

    public static ArrayList<Azkary> getDefaultAzkaryData(Context context) {
        String[] tasksStrings = context.getResources().getStringArray(R.array.AzkaryAzkar);
        ArrayList<Azkary> data = new ArrayList<>();
        for (String tasksString : tasksStrings) {
            data.add(new Azkary(tasksString));
        }
        return data;
    }

    public static ArrayList<DailyTask> getDefaultWerdMohasbaData(Context context) {
        String[] tasksStrings = context.getResources().getStringArray(R.array.Tasks);
        ArrayList<DailyTask> data = new ArrayList<>();
        for (int counter = 0; counter < tasksStrings.length; counter++) {
            data.add(new DailyTask(getTypeOfCell(counter), tasksStrings[counter]));
        }
        return data;
    }


    private static int getTypeOfCell(int rowPosition) {
        int type;
        switch (rowPosition) {
            case 0:
            case 1:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 13:
            case 14:
            case 16:
            case 17:
            case 19:
            case 20:
            case 21:
                type = Constants.CellsType.TYPE_SALAH;
                break;
            case 2:
            case 3:
            case 9:
            case 11:
            case 12:
            case 15:
            case 18:
            case 22:
                type = Constants.CellsType.TYPE_ZEKR;
                break;
            case 4:
                type = Constants.CellsType.TYPE_QOUR2AN;
                break;
            case 23:
                type = Constants.CellsType.TYPE_SEYAM;
                break;
            default:
                type = Constants.CellsType.TYPE_SALAH;
        }
        return type;
    }

    public static void checkTheme(AppCompatActivity activity) {
        int selectedTheme = SharedPrefManager.getInstance().doStuff(activity).getThemeColor();
        if (selectedTheme == Constants.ConstantsValues.LightTheme) {
            activity.setTheme(R.style.LightAppTheme);
        } else if (selectedTheme == Constants.ConstantsValues.DarkTheme) {
            activity.setTheme(R.style.DarkAppTheme);
        } else if (selectedTheme == Constants.ConstantsValues.GreenTheme) {
            activity.setTheme(R.style.GreenAppTheme);
        } else if (selectedTheme == Constants.ConstantsValues.PinkTheme) {
            activity.setTheme(R.style.PinkAppTheme);
        }
    }

    public static ArrayList<String> getDefaultAzkarSebha(Context context) {
        return new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.SebhaAzkar)));
    }

    public static String getRundomZekr(Context context) {
        SharedPrefManager prefManager = SharedPrefManager.getInstance().doStuff(context);
        ArrayList<Azkary> azkaryData = prefManager.getAzkaryData(context);
        ArrayList<Azkary> checkAzkary = getCheckedAzkary(azkaryData);
        if (checkAzkary.isEmpty()) {
            return "";
        } else {
            Random random = new Random();
            int randomPosition = random.nextInt(checkAzkary.size());
            return checkAzkary.get(randomPosition).getZekr_content();
        }
    }

    private static ArrayList<Azkary> getCheckedAzkary ( ArrayList<Azkary> azkaryData){
        ArrayList<Azkary> checkAzkary = new ArrayList<>();
        for (Azkary azkary : azkaryData){
            if (azkary.isChecked()){
                checkAzkary.add(azkary);
            }
        }
        return checkAzkary;
    }

    public static ProgressDialog show_progress_dialoug(Context context, String Body_text, boolean cancelable) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(cancelable);
        progressDialog.setMessage(Body_text);
        progressDialog.show();
        return progressDialog;
    }

    public static void show_alert_dialoug(Context context, String body, String title, boolean cancelable, String positive_text, String negative_text, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener NoListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setCancelable(cancelable).setMessage(body);
        if (!title.isEmpty()) {
            builder.setTitle(title);
        }
        if (!positive_text.isEmpty()) {
            builder.setPositiveButton(positive_text, yesListener);
        }
        if (!negative_text.isEmpty()) {
            builder.setNegativeButton(negative_text, NoListener);
        }

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public static void changeActivityFont(final Activity activity) {
        new Thread() {
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Calligrapher calligrapher = new Calligrapher(activity);
                        calligrapher.setFont(activity, "fonts/cairo_regular.ttf", true);
                    }
                });
            }
        }.start();
    }

    public static void changeViewFont(View view) {
        Calligrapher calligrapher = new Calligrapher(view.getContext());
        calligrapher.setFont(view, "fonts/cairo_regular.ttf");
    }


    public static Typeface changeFont1(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/arialbd1.ttf");
    }

    public static Typeface changeFont2(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/arialbi2.ttf");
    }

    public static Typeface changeFont3(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/cairo_regular.ttf");
    }

    public static void shareApp(Context context) {
        String sharingString = context.getString(R.string.shareString);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");// Plain format text
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, sharingString);
        context.startActivity(Intent.createChooser(sharingIntent, "Share Text Using"));
    }

    public static void openAppOnGooglePlay(Context context) {
        final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static String getFormatedTime(Date date) {
        Locale locale = new Locale("en");
        SimpleDateFormat stf = new SimpleDateFormat("hh:mm aaa", locale);
        return stf.format(date);
    }
}
