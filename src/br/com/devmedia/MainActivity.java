package br.com.devmedia;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.com.devmedia.service.LocationService;

public class MainActivity extends Activity implements LocationListener {

	private LocationService locationService;
	private TextView currentLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.locationService = new LocationService(this, this);
		this.currentLocation = (TextView) findViewById(R.id.current_location);
		lasGpsButtonPrepare();
		lasNetworkButtonPrepare();
	}

	private void lasGpsButtonPrepare() {
		Button lastGps = (Button) findViewById(R.id.last_gps);
		lastGps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				currentLocation.setText(locationService
						.getLastKnownLocationGPS().toString());
			}
		});
	}

	private void lasNetworkButtonPrepare() {
		Button lastGps = (Button) findViewById(R.id.last_network);
		lastGps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				currentLocation.setText(locationService
						.getLastKnownLocationNetwork().toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		currentLocation.setText(location.toString());

	}

	@Override
	public void onProviderDisabled(String arg0) {

	}

	@Override
	public void onProviderEnabled(String arg0) {

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

	}

}