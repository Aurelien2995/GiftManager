package com.example.giftmanager.utils;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.giftmanager.data.entities.Person;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NotificationUtils {

    public static final String CHANNEL_ID = "birthday_channel";

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Anniversaires",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Rappels d'anniversaires");

            NotificationManager manager =
                    context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    public static void scheduleBirthday(Context context, Person person) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date birthDate = sdf.parse(person.birthday);

            Calendar now = Calendar.getInstance();
            Calendar birthday = Calendar.getInstance();
            birthday.setTime(birthDate);
            birthday.set(Calendar.YEAR, now.get(Calendar.YEAR));
            birthday.set(Calendar.HOUR_OF_DAY, 9); // 9h du matin
            birthday.set(Calendar.MINUTE, 0);
            birthday.set(Calendar.SECOND, 0);
            birthday.set(Calendar.MILLISECOND, 0);

            // Si déjà passé → année suivante
            if (birthday.before(now)) {
                birthday.add(Calendar.YEAR, 1);
            }

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            // ---- Notification le jour même ----
            Intent todayIntent = new Intent(context, BirthdayReceiver.class);
            todayIntent.putExtra("title", "\uD83C\uDF89 Anniversaire");
            todayIntent.putExtra("type", "today");
            todayIntent.putExtra("message", "C'est l'anniversaire de" + person.name + " aujourd'hui !");

            PendingIntent todayPendingIntent = PendingIntent.getBroadcast(
                    context,
                    person.id * 10, // ID unique pour le jour même
                    todayIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        birthday.getTimeInMillis(),
                        todayPendingIntent
                );
            }

            // ---- Notification une semaine avant ----
            Calendar weekBefore = (Calendar) birthday.clone();
            weekBefore.add(Calendar.DAY_OF_YEAR, -7);
            Intent weekIntent = new Intent(context, BirthdayReceiver.class);
            weekIntent.putExtra("title", "Anniversaire bientôt \uD83C\uDF81");
            weekIntent.putExtra("type", "week_before");
            weekIntent.putExtra("message", person.name + " fête son anniversaire dans 7 jours");

            PendingIntent weekPendingIntent = PendingIntent.getBroadcast(
                    context,
                    person.id * 10 + 1, // ID unique pour la semaine avant
                    weekIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        weekBefore.getTimeInMillis(),
                        weekPendingIntent
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
