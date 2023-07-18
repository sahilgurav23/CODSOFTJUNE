package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    EditText title, content;
    Button bt;
    static final String CHANNEL_ID = "channel";
    static final int NOTIFICATION_ID = 100;

    static final int REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        bt = findViewById(R.id.clickbt);

        bt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                // Create a notification channel if it doesn't exist
                Notification notification;
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.bell,null);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap largeIcon = bitmapDrawable.getBitmap();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // if new instance is created then old one is deleted form activity stack
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,REQ_CODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                        .bigPicture(largeIcon)
                        .setBigContentTitle(title.getText().toString());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    notification = new Notification.Builder(MainActivity.this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.images)
                            .setContentText(content.getText().toString())
                            .setContentTitle(title.getText().toString())
                            .setContentIntent(pendingIntent)
                            .setStyle(bigPictureStyle)
                            .setChannelId(CHANNEL_ID)
                            .setColor(ContextCompat.getColor(MainActivity.this, R.color.bg))
                            .build();
                    manager.createNotificationChannels(Collections.singletonList(new NotificationChannel(CHANNEL_ID, "new_channel", NotificationManager.IMPORTANCE_HIGH)));
                    // merge the same id messages
                    manager.notify(NOTIFICATION_ID, notification);
                }

                title.setText("");
                content.setText("");
                Toast.makeText(MainActivity.this, "Notification is created", Toast.LENGTH_SHORT).show();
            }
        });
    }
}