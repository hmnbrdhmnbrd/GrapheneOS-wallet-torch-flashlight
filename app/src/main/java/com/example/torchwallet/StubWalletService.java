package com.example.torchwallet;

import android.app.PendingIntent;
import android.content.Intent;
import android.service.quickaccesswallet.GetWalletCardsCallback;
import android.service.quickaccesswallet.GetWalletCardsRequest;
import android.service.quickaccesswallet.GetWalletCardsResponse;
import android.service.quickaccesswallet.QuickAccessWalletService;
import android.service.quickaccesswallet.SelectWalletCardRequest;

import java.util.Collections;

public class StubWalletService extends QuickAccessWalletService {
    private PendingIntent torchIntent() {
        Intent i = new Intent(this, TorchActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(this, 0, i,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override // API 33+
    public PendingIntent getTargetActivityPendingIntent() {
        return torchIntent();
    }

    // API 36+. No @Override because compileSdk 34 doesn't have it;
    // the framework calls it by name at runtime.
    public PendingIntent getGestureTargetActivityPendingIntent() {
        return torchIntent();
    }

    @Override
    public void onWalletCardsRequested(GetWalletCardsRequest request,
                                       GetWalletCardsCallback callback) {
        callback.onSuccess(new GetWalletCardsResponse(Collections.emptyList(), 0));
    }

    @Override
    public void onWalletCardSelected(SelectWalletCardRequest request) { }

    @Override
    public void onWalletDismissed() { }
}
