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
		lastGpsButtonPrepare();
		lastNetworkButtonPrepare();

		updateGpsButtonPrepare();
		updateNetworkButtonPrepare();
		updatePassiveButtonPrepare();

		stopButtonPrepare();
	}

	private void stopButtonPrepare() {
		Button button = (Button) findViewById(R.id.stop);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				locationService.stop();
				currentLocation.setText("");
			}
		});
	}

	private void updatePassiveButtonPrepare() {
		Button button = (Button) findViewById(R.id.update_network);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				locationService.startPassiveLocationCapture();
			}
		});
	}

	private void updateNetworkButtonPrepare() {
		Button button = (Button) findViewById(R.id.update_gps);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				locationService.startGpsLocationCapture();
			}
		});
	}

	private void updateGpsButtonPrepare() {
		Button button = (Button) findViewById(R.id.update_network);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				locationService.startNetworkLocationCapture();
			}
		});
	}

	private void lastGpsButtonPrepare() {
		Button button = (Button) findViewById(R.id.last_gps);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Location location = locationService.getLastKnownLocationGPS();
				if (location != null) {
					currentLocation.setText(location.toString());
				} else {
					currentLocation.setText("Não localização valida!");
				}
			}
		});
	}

	private void lastNetworkButtonPrepare() {
		Button button = (Button) findViewById(R.id.last_network);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Location location = locationService
						.getLastKnownLocationNetwork();
				if (location != null) {
					currentLocation.setText(location.toString());
				} else {
					currentLocation.setText("Não localização valida!");
				}
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