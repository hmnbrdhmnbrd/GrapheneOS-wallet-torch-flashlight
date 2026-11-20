package com.example.torchwallet;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.service.quickaccesswallet.GetWalletCardsCallback;
import android.service.quickaccesswallet.GetWalletCardsRequest;
import android.service.quickaccesswallet.GetWalletCardsResponse;
import android.service.quickaccesswallet.QuickAccessWalletService;
import android.service.quickaccesswallet.SelectWalletCardRequest;
import android.service.quickaccesswallet.WalletCard;

import java.util.Collections;

public class StubWalletService extends QuickAccessWalletService {
    @Override
    public void onWalletCardsRequested(GetWalletCardsRequest request, GetWalletCardsCallback callback) {
        Intent intent = new Intent(this, TorchActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
        );

        Bitmap bmp = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        Icon cardImage = Icon.createWithBitmap(bmp);

        WalletCard card = new WalletCard.Builder(
                "torch_card",
                cardImage,
                "Toggle Flashlight",
                pendingIntent
        ).build();

        callback.onSuccess(new GetWalletCardsResponse(Collections.singletonList(card), 0));
    }

    @Override
    public void onWalletCardSelected(SelectWalletCardRequest request) { }

    @Override
    public void onWalletDismissed() { }
}
