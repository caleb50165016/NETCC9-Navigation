package com.example.registerloginexample;



import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

public class MainActivity extends AppCompatActivity {
    private Button btn_gps;
    private Button btn_obj;
    TMapView tMapView=null;
    TMapGpsManager tMapGPS=null;



    double start_la = 37.540863;

    double start_lo = 127.079455;

    double end_la = 37.252636;

    double end_lo = 127.040715;

    TMapPoint tMapPointStart = new TMapPoint(start_la, start_lo);
    TMapPoint tMapPointEnd = new TMapPoint(end_la, end_lo);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //지도 부분
        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.tmap);
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey("l7xxb5335ed5258b47b8a8edd1ffcd3f925f");
        linearLayoutTmap.addView( tMapView );
        tMapView.setCenterPoint(start_lo,start_la,  true);
        tMapView.setZoomLevel(15);


        //경로 부분
        TMapPolyLine polyLine = new TMapPolyLine();
        PathAsync pathAsync = new PathAsync();
        pathAsync.execute(polyLine);



        //내 위치 표시 버튼
        btn_gps = findViewById(R.id.btn_gps);
        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addGPSMarker();
            }
        });


        //동적 객체 위치 표시 버튼
        btn_obj = findViewById(R.id.btn_obj);
        btn_obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOBJMarker();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)    {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item1:
                objPopup();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void objPopup(){
        AlertDialog.Builder ad= new AlertDialog.Builder(MainActivity.this);
        ad.setIcon(R.mipmap.ic_launcher);
        ad.setTitle("동적 객체 정보\n");
        ad.setMessage("위도 37.531740\n경도 127.066697\n전방 1.5km");

        ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        ad.show();



    }
    private void addGPSMarker(){
        TMapMarkerItem tourMarkerItem = new TMapMarkerItem();
        TMapPoint tpoint = new TMapPoint(start_la, start_lo);
        tourMarkerItem.setTMapPoint(tpoint);
        tourMarkerItem.setVisible(TMapMarkerItem.VISIBLE);
        tMapView.addMarkerItem("tourMarker", tourMarkerItem);

    }

    private void addOBJMarker(){
        TMapMarkerItem tourMarkerItem = new TMapMarkerItem();
        TMapPoint tpoint = new TMapPoint(37.531740, 127.066697);
        tourMarkerItem.setTMapPoint(tpoint);
        tourMarkerItem.setVisible(TMapMarkerItem.VISIBLE);
        tMapView.addMarkerItem("tourMarker", tourMarkerItem);

    }






    class PathAsync extends AsyncTask<TMapPolyLine, Void, TMapPolyLine> {
        @Override
        protected TMapPolyLine doInBackground(TMapPolyLine... tMapPolyLines) {
            TMapPolyLine tMapPolyLine = tMapPolyLines[0];
            try {
                tMapPolyLine = new TMapData().findPathDataWithType(TMapData.TMapPathType.CAR_PATH, tMapPointStart, tMapPointEnd);
                tMapPolyLine.setLineColor(Color.BLUE);
                tMapPolyLine.setLineWidth(3);


            }catch(Exception e) {
                e.printStackTrace();
                Log.e("error",e.getMessage());
            }
            return tMapPolyLine;
        }

        @Override
        protected void onPostExecute(TMapPolyLine tMapPolyLine) {
            super.onPostExecute(tMapPolyLine);
            tMapView.addTMapPolyLine("Line1", tMapPolyLine);
        }
    }

}

