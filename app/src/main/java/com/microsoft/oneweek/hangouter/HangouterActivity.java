package com.microsoft.oneweek.hangouter;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.microsoft.oneweek.hangouter.Adapters.POISuggestionsAdapter;
import com.microsoft.oneweek.hangouter.Enums.POIType;
import com.microsoft.oneweek.hangouter.Interfaces.IMapPresenter;
import com.microsoft.oneweek.hangouter.Interfaces.IMapView;
import com.microsoft.oneweek.hangouter.Interfaces.POI;
import com.microsoft.oneweek.hangouter.Models.HangouterViewModel;
import com.microsoft.oneweek.hangouter.Models.User;
import com.microsoft.oneweek.hangouter.Presenters.HangouterMapPresenter;
import com.microsoft.oneweek.hangouter.Utils.HangouterUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.microsoft.oneweek.hangouter.R.id.map;

public class HangouterActivity extends AppCompatActivity
        implements IMapView, OnMapReadyCallback, GoogleMap.OnMapLoadedCallback, POISuggestionsAdapter.POISelectionhandler {

    @BindView(R.id.recyclerViewPOI)
    RecyclerView mRecyclerViewPOI;
    @BindView(R.id.bottomSheetPOIs)
    View mBottomSheet;
    @BindView(R.id.fabShowPois)
    FloatingActionButton mFabShowPOIs;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private IMapPresenter mPresenter;
    private GoogleMap mMap;
    private HangouterViewModel mHangouterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hangouter_map);
        ButterKnife.bind(this);
        HangouterUtils.InitializeNetworkClient(this);
        ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map))
                .getMapAsync(this);

        mPresenter = new HangouterMapPresenter(this);
        mHangouterViewModel = new HangouterViewModel();
        InitializeRecyclerView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLoadedCallback(this);
    }

    @Override
    public void onMapLoaded() {
        mPresenter.fetchUserLocations();
    }

    @Override
    public void showUserLocation(User user) {
        mHangouterViewModel
                .addUserMarker(user, mMap.addMarker(HangouterUtils.generateMarkerOpetions(user)));
    }

    @Override
    public void showLoadingUsers() {

    }

    @Override
    public void hideLoadingUsers() {

    }

    @Override
    public void showFindingPOIs() {
        mProgressBar.setVisibility(View.VISIBLE);
        LatLng centre = mHangouterViewModel.getCentre();
        List<LatLng> userPositions = mHangouterViewModel.getUserPositions();

        for (LatLng userPosiion : userPositions) {
            mHangouterViewModel.addLine(mMap
                    .addPolyline(HangouterUtils.generateLineOptions(userPosiion, centre)));
        }
    }

    @Override
    public void hideFindingPOIs() {
        mHangouterViewModel.clearLines();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showPOIsReady(List<? extends POI> pois) {

        mRecyclerViewPOI.setAdapter(
                new POISuggestionsAdapter(pois, this)
        );
        mFabShowPOIs.setBackgroundTintList(
                ColorStateList.valueOf(getResources().getColor(R.color.colorFABSuccess)));
    }

    @Override
    public void zoomToShowAll() {
        mMap.moveCamera(
                CameraUpdateFactory.newLatLngBounds(
                        mHangouterViewModel.getLatLngBoundsForAll(),
                        50
                )
        );
    }

    @OnClick(R.id.buttonFindPOI)
    public void findPOIs() {
        mPresenter.loadPOISuggestions(mHangouterViewModel.getCentre(), POIType.Restaurant);
    }

    private void InitializeRecyclerView() {
        mRecyclerViewPOI.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onPOISelected(POI poi) {
        setResult(RESULT_OK, poi.getAsIntent());
        finish();
    }
}
