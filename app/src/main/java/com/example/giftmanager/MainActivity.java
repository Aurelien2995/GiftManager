package com.example.giftmanager;

import static com.example.giftmanager.utils.NotificationUtils.createNotificationChannel;
import static com.example.giftmanager.utils.NotificationUtils.scheduleBirthday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.giftmanager.utils.NotificationUtils;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new com.example.giftmanager.ui.PersonListFragment())
                    .commit();
        }
        // Vérifie si on est sur Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Demande la permission
                requestPermissions(
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQUEST_NOTIFICATION_PERMISSION
                );
            } else {
                // Permission déjà accordée → on peut programmer les notifications
                NotificationUtils.createNotificationChannel(this);
            }
        } else {
            // Android < 13 → pas de permission nécessaire
            NotificationUtils.createNotificationChannel(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission accordée → programmer les notifications
                //scheduleBirthday(this,);
            } else {
                // Permission refusée → on peut prévenir l'utilisateur
                Toast.makeText(this, "Notifications désactivées", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
