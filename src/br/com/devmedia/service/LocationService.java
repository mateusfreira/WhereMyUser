package br.com.devmedia.service;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class LocationService {
	private LocationManager locationManager;
	private Context context;
	private LocationListener listener;
	private long minTimeBetweenLocations = 5 * 60 * 1000;

	public LocationService(Context context, LocationListener locationListener) {
		this.context = context;
		this.locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		this.listener = locationListener;
	}

	public Location getLastKnownLocationGPS() {
		return this.locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	}

	public Location getLastKnownLocationNetwork() {
		return this.locationManager
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	}

	public void startGpsLocationCapture() {
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				minTimeBetweenLocations, 0, listener);
	}

	public void startNetworkLocationCapture() {
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, minTimeBetweenLocations, 0,
				listener);
	}

	public void startPassiveLocationCapture() {
		locationManager.requestLocationUpdates(
				LocationManager.PASSIVE_PROVIDER, minTimeBetweenLocations, 0,
				listener);
	}

	public long getMinTimeBetweenLocations() {
		return minTimeBetweenLocations;
	}

	public void setMinTimeBetweenLocations(long minTimeBetweenLocations) {
		this.minTimeBetweenLocations = minTimeBetweenLocations;
	}

	public void stop() {
		locationManager.removeUpdates(listener);
	}

	public boolean verifyGPSEnabled(boolean showAlert) {
		boolean result = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!result && showAlert) {
			sugestGPSEnabled();
		}
		return result;
	}

	public void sugestGPSEnabled() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(
				"Opa percebemos que seu gps esta dasabilitado, deseja habilitar agora?")
				.setCancelable(false)
				.setPositiveButton("SIm",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									final int id) {
								context.startActivity(new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
							}
						})
				.setNegativeButton("Não",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									final int id) {
								dialog.cancel();
							}
						});
		final AlertDialog alert = builder.create();
		alert.show();
	}

}
