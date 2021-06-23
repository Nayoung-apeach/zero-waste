package kr.hs.emirim.js.zero_waste;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback{

    private MapView mapView;

    public MapFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView)layout.findViewById(R.id.map);
        mapView.getMapAsync(this);

        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수
        if(mapView != null) {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng MIRIM = new LatLng(37.46662797606851, 126.93289374450282);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(MIRIM);
        markerOptions.title("미림마이스터고");
        markerOptions.snippet("전시 진행 중");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_custom_marker2));

        googleMap.addMarker(markerOptions);
        setMarkers(googleMap);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MIRIM,13));
    }

    private void setMarkers(GoogleMap googleMap){
        MarkersLocation markersLocation = new MarkersLocation();
        String[][] infos = markersLocation.getMarkerslo();

        for(int i=0; i<infos.length; i++){
            // 마커 옵션 설정
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(Double.parseDouble(infos[i][0]),Double.parseDouble(infos[i][1])));
            markerOptions.title(infos[i][2]);
            markerOptions.snippet(infos[i][3]);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_custom_marker));

            // 마커 생성
            googleMap.addMarker(markerOptions);
        }
    }

}