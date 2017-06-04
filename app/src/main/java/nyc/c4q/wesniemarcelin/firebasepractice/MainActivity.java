package nyc.c4q.wesniemarcelin.firebasepractice;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import nyc.c4q.wesniemarcelin.firebasepractice.model.UserMessage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "YOOOO";
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;
    private static Random rand = new Random();

    Button redButton;
    Button greenButton;
    Button blueButton;
    CardView cv;
    Button send_buttn;
    EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cv = (CardView) findViewById(R.id.cv_text);
        send_buttn = (Button) findViewById(R.id.send_bttn);
        editText = (EditText) findViewById(R.id.ed_txt);


        redButton = (Button) findViewById(R.id.red_button);
        greenButton = (Button) findViewById(R.id.green_button);
        blueButton = (Button) findViewById(R.id.blue_button);
        System.out.println(isGooglePlayServicesAvailable());
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("posts");
        mDatabase.setValue("Hello, World 22!");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("onDataChange", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        redButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);


    }

    public boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(getApplicationContext());
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(MainActivity.this, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    private void listenForChanges() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                UserMessage post = dataSnapshot.getValue(UserMessage.class);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);
    }

    private void readDB() {
        // Read from the database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void writeToDB() {
        // Write a message to the database
        mDatabase.setValue("Hello, World!");
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.blue_button:
                System.out.println("Blue button clicked");
                mDatabase.child(getRandomNumber()).setValue(createBluePost());
                cv.setVisibility(View.VISIBLE);
                send_buttn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cv.setVisibility(View.GONE);
                    }
                });
                break;
            case R.id.red_button:
                System.out.println("Red button clicked");
                mDatabase.child("colors").child(Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID)).setValue(createRedPost().toString());
                cv.setVisibility(View.VISIBLE);
                send_buttn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cv.setVisibility(View.GONE);
                    }
                });

                mDatabase.child(getRandomNumber()).setValue(createRedPost());break;
            case R.id.green_button:
                System.out.println("Green button clicked");
                mDatabase.child(getRandomNumber()).setValue(createGreenPost());
                cv.setVisibility(View.VISIBLE);
                send_buttn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cv.setVisibility(View.GONE);
                    }
                });
                break;
        }
    }

    public static Map<String, String> createRedPost(){
        Map<String, String> red = new ArrayMap<>();

        try{
            red.put("timestamp_start",getCurrentUTCTime());
            red.put("timestamp_end", "2017-06-03 02:00:00");
            red.put("action_text", "RED");
            red.put("action_type", "abc");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(red.toString());
        return red;
    }

    public static Map<String, String> createGreenPost(){
        Map<String, String> red = new ArrayMap<>();

        try{
            red.put("timestamp_start",getCurrentUTCTime());
            red.put("timestamp_end", "2017-06-03 02:00:00");
            red.put("action_text", "GREEN");
            red.put("action_type", "abc");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(red.toString());
        return red;
    }

    public static Map<String, String> createBluePost(){
        Map<String, String> red = new ArrayMap<>();
        String time = String.valueOf(System.currentTimeMillis());

        try{
            red.put("timestamp_start",getCurrentUTCTime());
            red.put("timestamp_end", "2017-06-03 02:00:00");
            red.put("action_text", "BLUE");
            red.put("action_type", "abc");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(red.toString());
        return red;
    }
    private static String getCurrentUTCTime() {

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("utc"));
        String formattedDate = df.format(c.getTime());
        return  formattedDate;
    }


    public static String getRandomNumber(){
        Integer  number = rand.nextInt(Integer.MAX_VALUE) + 1;
        String numToString =number.toString();
        return numToString;
    }
}

