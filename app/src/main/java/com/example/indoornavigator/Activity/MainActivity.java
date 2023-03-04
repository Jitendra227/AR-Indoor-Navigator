package com.example.indoornavigator.Activity;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.indoornavigator.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ExtendedFloatingActionButton floor;
    FloatingActionButton mf0, mf1, mf2, mf3, mf4, mf5;
    TextView f0, f1, f2, f3, f4, f5;
    boolean isAllFabsVisible;

    public static final double MIN_OPENGL_VERSION = 3.0;
    private ArFragment arFragment;
    private int clickNo = 0;

    EditText searchBtn;
    ImageView mapLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        mapLayout = findViewById(R.id.main_maps_layout);
        searchBtn = findViewById(R.id.main_search_location);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_ar_scene_view);

        ActivityManager actv = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = actv.getDeviceConfigurationInfo();

        System.out.println("\n<======================================================>");
        System.out.println(Double.parseDouble(configurationInfo.getGlEsVersion()));
        System.out.println(configurationInfo.reqGlEsVersion>=0x30000);


        floor = findViewById(R.id.floor);
        mf0 = findViewById(R.id.ground_floor);
        mf1 = findViewById(R.id.first_floor);
        mf2 = findViewById(R.id.second_floor);
        mf3 = findViewById(R.id.third_floor);
        mf4 = findViewById(R.id.fourth_floor);
        mf5 = findViewById(R.id.fifth_floor);

        f0 = findViewById(R.id.ground_floor_text);
        f1 = findViewById(R.id.first_floor_text);
        f2 = findViewById(R.id.second_floor_text);
        f3 = findViewById(R.id.third_floor_text);
        f4 = findViewById(R.id.fourth_floor_text);
        f5 = findViewById(R.id.fifth_floor_text);

        mf0.setVisibility(View.GONE);
        mf1.setVisibility(View.GONE);
        mf2.setVisibility(View.GONE);
        mf3.setVisibility(View.GONE);
        mf4.setVisibility(View.GONE);
        mf5.setVisibility(View.GONE);

        f0.setVisibility(View.GONE);
        f1.setVisibility(View.GONE);
        f2.setVisibility(View.GONE);
        f3.setVisibility(View.GONE);
        f4.setVisibility(View.GONE);
        f5.setVisibility(View.GONE);

        isAllFabsVisible = false;

        floor.shrink();
        floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAllFabsVisible) {
                    mf0.show();mf1.show();mf2.show();mf3.show();mf4.show();mf5.show();
                    f0.setVisibility(View.VISIBLE);
                    f1.setVisibility(View.VISIBLE);
                    f2.setVisibility(View.VISIBLE);
                    f3.setVisibility(View.VISIBLE);
                    f4.setVisibility(View.VISIBLE);
                    f5.setVisibility(View.VISIBLE);

                    floor.extend();
                    isAllFabsVisible = true;
                } else {
                    mf0.hide();mf1.hide();mf2.hide();mf3.hide();mf4.hide();mf5.hide();
                    f0.setVisibility(View.GONE);
                    f1.setVisibility(View.GONE);
                    f2.setVisibility(View.GONE);
                    f3.setVisibility(View.GONE);
                    f4.setVisibility(View.GONE);
                    f5.setVisibility(View.GONE);

                    floor.shrink();
                    isAllFabsVisible = false;
                }
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchLocationActivity.class);
                startActivity(intent);
            }
        });

        if(checkSystemSupport(this)){
            arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                clickNo++;
                if (clickNo == 1) {
                    Anchor anchor = hitResult.createAnchor();
                    ModelRenderable.builder()
                            .setSource(this, R.raw.model)
                            .setIsFilamentGltf(true)
                            .build()
                            .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                            .exceptionally(throwable -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                return null;
                            });
                }
            });
        }else {
            return;
        }
    }

    public static boolean checkSystemSupport(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity
                    .getSystemService(Context.ACTIVITY_SERVICE)))
                    .getDeviceConfigurationInfo()
                    .getGlEsVersion();
            if (Double.parseDouble(openGlVersion) >= 3.0) {
                return true;
            } else {
                Toast.makeText(activity, "App needs OpenGl Version 3.0 or later", Toast.LENGTH_SHORT).show();
                activity.finish();
                return false;
            }
        } else {
            Toast.makeText(activity, "App does not support required Build Version", Toast.LENGTH_SHORT).show();
            activity.finish();
            return false;
        }
    }

    private void addModel(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());
        TransformableNode transform = new TransformableNode(arFragment.getTransformationSystem());
        transform.setParent(anchorNode);
        transform.setRenderable(modelRenderable);
        transform.select();
    }

}