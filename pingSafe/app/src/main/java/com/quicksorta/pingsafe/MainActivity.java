package com.quicksorta.pingsafe;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.*;

<<<<<<< HEAD
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
=======
import java.util.HashMap;
import java.util.Map;
>>>>>>> origin/master


public class MainActivity extends FragmentActivity implements

    GooglePlayServicesClient.ConnectionCallbacks,
    GooglePlayServicesClient.OnConnectionFailedListener,
    LocationListener {

    // Global constants
    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */

    private final static int
            CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    // Milliseconds per second
    private static final int MILLISECONDS_PER_SECOND = 1000;
    // Update frequency in seconds
    public static final int UPDATE_INTERVAL_IN_SECONDS = 1;
    // Update frequency in milliseconds
    private static final long UPDATE_INTERVAL =
            MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;
    // The fastest update frequency, in seconds
    private static final int FASTEST_INTERVAL_IN_SECONDS = 1;
    // A fast frequency ceiling in milliseconds
    private static final long FASTEST_INTERVAL =
            MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;

    LocationClient mLocationClient;
    boolean mUpdatesRequested;
    boolean sentToStrangers = false;
    TextView longitudeView;
    TextView latitudeView;
    User selfUser;
    Firebase myFirebaseRef;
    Firebase usersRef;
    GeoFire geoFire;


    // Define an object that holds accuracy and frequency parameters
    LocationRequest mLocationRequest;

    //user that holds coordinates and name
    User user;
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD

        //create and initialize Firebase
        Firebase.setAndroidContext(this);
=======
>>>>>>> origin/master

        myFirebaseRef = new Firebase("https://scorching-inferno-2497.firebaseio.com/");
        Firebase hopperRef = myFirebaseRef.child("users");

        Map<String, Object> users = new HashMap<String, Object>();
        user = new User("Michael1234");
        users.put(user.getFullName(), user);

        hopperRef.updateChildren(users);


        setContentView(R.layout.fragment_main);
        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create();
        // Use high accuracy
        mLocationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
         /*
         * Create a new location client, using the enclosing class to
         * handle callbacks.
         */
        mLocationClient = new LocationClient(this, this, this);
        mLocationClient.connect();
        longitudeView = (TextView) findViewById(R.id.longitude);
        latitudeView = (TextView) findViewById(R.id.latitude);

        //initializing Firebase Context and instantiating object by referring it to my database
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://dazzling-fire-2743.firebaseio.com/");
        geoFire = new GeoFire(myFirebaseRef);
        usersRef = myFirebaseRef.child("users");
        Firebase newUsersRef = usersRef.push();
        selfUser = new User("Kanye West");

        usersRef.setValue(selfUser);
        selfUser.setUserNameID(newUsersRef.getKey());
        //function initializes User class and pushes users to database. Also handles location updates

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Ping received !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //function checks for updates to specific user's location and prints to the command line.

    }
<<<<<<< HEAD
    public class User extends Object{
=======

    //User class
    public class User {
>>>>>>> origin/master

        private String fullName;
        private double latitude;
        private double longitude;
<<<<<<< HEAD
=======
        private String userNameID;
        private boolean ping;
>>>>>>> origin/master

        public User() {}

        public User(String fullName) {
            this.fullName = fullName;
            this.latitude = 0;
            this.longitude = 0;
<<<<<<< HEAD
        }

=======
            this.ping = false;
        }
        public void setPing(Boolean pingBool){
            this.ping = pingBool;
        }
        public boolean getPing(){ return ping; }
>>>>>>> origin/master
        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

<<<<<<< HEAD
=======
        public String getUserNameID() { return userNameID; }
        public void setUserNameID(String ID) {
            userNameID = ID; }

>>>>>>> origin/master
        public void setLatitude(double updateLat) {
            this.latitude = updateLat;
        }

        public void setLongitude(double updateLong) {
            this.longitude = updateLong;
        }
        public String getFullName() {
            return fullName;
        }
    }
<<<<<<< HEAD
=======

    //Initializes five users and pushes to database
    public void createUsers(Firebase myFirebaseRef){
        User simon_bloch = new User("Simon Bloch");
        Firebase usersRef = myFirebaseRef.child("users");
        Map<String, User> users = new HashMap<String, User>();
        users.put("User2", simon_bloch);
        usersRef.setValue(users);
    }


>>>>>>> origin/master
    public void onLocationChanged(Location location){
//        String msg = "Updated Location: " +
//                Double.toString(location.getLatitude()) + "," +
//                Double.toString(location.getLongitude());
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        myFirebaseRef.child("users").child(user.getFullName()).child("latitude").setValue(latitude);
        myFirebaseRef.child("users").child(user.getFullName()).child("longitude").setValue(longitude);
        sendToStrangers();
=======
        selfUser.setLatitude(location.getLatitude());
        selfUser.setLongitude(location.getLongitude());
//        usersRef.child(selfUser.getUserNameID()+"/latitude").setValue(selfUser.getLatitude());
//        usersRef.child(selfUser.getUserNameID()+"/longitude").setValue(selfUser.getLongitude());
        geoFire.setLocation(selfUser.getUserNameID(), new GeoLocation(selfUser.getLatitude(), selfUser.getLongitude()));

        Toast.makeText(this, "Updated latitude: " + Double.toString(selfUser.getLatitude()), Toast.LENGTH_SHORT).show();
//        sentToStrangers();
>>>>>>> origin/master
    }

//    public void sentToStrangers() {
//            if (!sentToStrangers && (selfUser.getLatitude() != 0)) {
//                sentToStrangers = true;
//                //send to firebase
//                Toast.makeText(this, "Sent to strangers: " + Double.toString(selfUser.getLatitude()), Toast.LENGTH_SHORT).show();
//            }
//    }

    public void sendLocation(View view){
//        mCurrentLocation = mLocationClient.getLastLocation();
//        double longitude = mCurrentLocation.getLongitude();
//        double latitude = mCurrentLocation.getLatitude();
//        if(!mUpdatesRequested) {
//
////            mLocationClient.connect();
//            mUpdatesRequested = true;
//        }

        longitudeView.setText(String.valueOf(selfUser.getLongitude()));
        latitudeView.setText(String.valueOf(selfUser.getLatitude()));
        selfUser.setPing(true);
        usersRef.child(selfUser.getUserNameID()+"/ping").setValue(selfUser.getPing());
//        sentToStrangers();
    }



    /*
     * Called when the Activity becomes visible.
     */
    protected void onStart() {
        super.onStart();
        // Connect the client.

    }

    /*
    * Called when the Activity is no longer visible.
    */
    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        mLocationClient.disconnect();
        super.onStop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Define a DialogFragment that displays the error dialog
    public static class ErrorDialogFragment extends DialogFragment {
        // Global field to contain the error dialog
        private Dialog mDialog;
        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }
        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }
        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }

    /*
     * Handle results returned to the FragmentActivity
     * by Google Play services
     */
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        // Decide what to do based on the original request code
        switch (requestCode) {

            case CONNECTION_FAILURE_RESOLUTION_REQUEST :
            /*
             * If the result code is Activity.RESULT_OK, try
             * to connect again
             */
                switch (resultCode) {
                    case Activity.RESULT_OK :
                    /*
                     * Try the request again
                     */

                        break;
                }
        }
    }

    private boolean servicesConnected() {
        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.
                        isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates",
                    "Google Play services is available.");
            // Continue
            return true;
            // Google Play services was not available for some reason.
            // resultCode holds the error code.
        } else {
            // Get the error dialog from Google Play services
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                    resultCode,
                    this,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST);

            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                // Create a new DialogFragment for the error dialog
                ErrorDialogFragment errorFragment =
                        new ErrorDialogFragment();
                // Set the dialog in the DialogFragment
                errorFragment.setDialog(errorDialog);
                // Show the error dialog in the DialogFragment
                errorFragment.show(getSupportFragmentManager(), "Location Updates");
            }
        }
        return false;
    }


    /*
     * Called by Location Services when the request to connect the
     * client finishes successfully. At this point, you can
     * request the current location or start periodic updates
     */
    @Override
    public void onConnected(Bundle dataBundle) {
        // Display the connection status
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        mLocationClient.requestLocationUpdates(mLocationRequest, this);

    }

    /*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
    @Override
    public void onDisconnected() {
        // Display the connection status
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
    }

    public void showErrorDialog(int connectionResult) {
        // Display the connection status
        Toast.makeText(this, "Error. I did my best.",
                Toast.LENGTH_SHORT).show();
    }


    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            showErrorDialog(connectionResult.getErrorCode());
        }
    }
}
