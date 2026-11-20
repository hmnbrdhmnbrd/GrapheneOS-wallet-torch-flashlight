package com.example.torchwallet;

import android.service.quickaccesswallet.GetWalletCardsCallback;
import android.service.quickaccesswallet.GetWalletCardsRequest;
import android.service.quickaccesswallet.GetWalletCardsResponse;
import android.service.quickaccesswallet.QuickAccessWalletService;
import android.service.quickaccesswallet.SelectWalletCardRequest;

import java.util.Collections;

public class StubWalletService extends QuickAccessWalletService {
    @Override
    public void onWalletCardsRequested(GetWalletCardsRequest request, GetWalletCardsCallback callback) {
        callback.onSuccess(new GetWalletCardsResponse(Collections.emptyList(), 0));
    }

    @Override
    public void onWalletCardSelected(SelectWalletCardRequest request) { }

    @Override
    public void onWalletDismissed() { }
}
