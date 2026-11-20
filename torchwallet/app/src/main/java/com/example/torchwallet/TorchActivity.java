package com.example.torchwallet;

import android.app.Activity;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class TorchActivity extends Activity {
    private CameraManager cm;
    private CameraManager.TorchCallback callback;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cm = (CameraManager) getSystemService(CAMERA_SERVICE);
        final String id = findFlashCamera();
        if (id == null) {
            finishQuietly();
            return;
        }
        callback = new CameraManager.TorchCallback() {
            @Override
            public void onTorchModeChanged(String cameraId, boolean enabled) {
                if (done || !cameraId.equals(id)) return;
                done = true;
                try {
                    cm.setTorchMode(id, !enabled);
                } catch (Exception ignored) { }
                finishQuietly();
            }
        };
        cm.registerTorchCallback(callback, handler);
        handler.postDelayed(this::finishQuietly, 1500); // safety net
    }

    private String findFlashCamera() {
        try {
            for (String id : cm.getCameraIdList()) {
                CameraCharacteristics c = cm.getCameraCharacteristics(id);
                Boolean flash = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                Integer facing = c.get(CameraCharacteristics.LENS_FACING);
                if (flash != null && flash && facing != null
                        && facing == CameraCharacteristics.LENS_FACING_BACK) {
                    return id;
                }
            }
        } catch (Exception ignored) { }
        return null;
    }

    private void finishQuietly() {
        handler.removeCallbacksAndMessages(null);
        if (callback != null) {
            try { cm.unregisterTorchCallback(callback); } catch (Exception ignored) { }
            callback = null;
        }
        if (!isFinishing()) {
            finish();
            overridePendingTransition(0, 0);
        }
    }
}
