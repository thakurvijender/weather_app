package viz.oragami.weatherapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.RequestParams;

public class MainActivity extends AppCompatActivity {
    TextView temperature,location;
    ImageView imageView;
    boolean mUseLocation= true;
    final int REQUEST_CODE = 123;
    final int NEWCITY_CODE = 007;
    final String URL = "http://api.openweathermap.org/data/2.5/weather";
    final String APP_ID= "e72ca729af228beabd5d20e3b7749713";
    final long MIN_TIME=5000;
    final float MIN_DIATANCE = 1000;
    final String LOGCAT= "vijender";

    final String LOCATION_PROVIDER= LocationManager.GPS_PROVIDER;
    //change to network provider in mobile app




    //location stigma here
    LocationManager locationManager;
    LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperature=findViewById(R.id.mtemperature);
        location=findViewById(R.id.mlocation);
        imageView= findViewById(R.id.mimageView);



    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(LOGCAT, "on resume called");
        getWeatherforCurrentLocation();
       // if (mUseLocation) getWeatherforCurrentLocation;

    }
    private void getWeatherforCurrentLocation() {

        Log.d(LOGCAT, "getting waether of current location");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(LOGCAT, "location changed");

                String longitude = String.valueOf(location.getLongitude());
                String latitude = String.valueOf(location.getLatitude());

                Log.d(LOGCAT, "longitude is" + longitude);
                Log.d(LOGCAT, "latitude is" + latitude);
                RequestParams requestParams= new RequestParams();


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {


            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_CODE){
            if (grantResults.length>0 &&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Log.d(LOGCAT,"permission granted");
                getWeatherforCurrentLocation();
            }
            else{
                Log.d(LOGCAT,"permission denied");

            }
        }
    }
}
