package br.com.devmedia.service;

import android.content.Context;
import android.location.LocationManager;

public class LocationService {
	private LocationManager locationManager;
	private Context context;

	public LocationService(Context context) {
		this.context = context;
		this.locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
	}

}
