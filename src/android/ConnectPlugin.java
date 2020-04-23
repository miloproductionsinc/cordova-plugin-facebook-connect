package org.apache.cordova.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.webkit.WebView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

public class ConnectPlugin extends CordovaPlugin {

    private final String TAG = "ConnectPlugin";

    private CallbackManager callbackManager;

    @Override
    protected void pluginInitialize() {
        FacebookSdk.sdkInitialize(cordova.getActivity().getApplicationContext());

        // create callbackManager
        callbackManager = CallbackManager.Factory.create();

        // augment web view to enable hybrid app events
        enableHybridAppEvents();

        // Set up the activity result callback to this class
        cordova.setActivityResultCallback(this);
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        // Developers can observe how frequently users activate their app by logging an app activation event.
        AppEventsLogger.activateApp(cordova.getActivity().getApplication());
    }

    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        AppEventsLogger.deactivateApp(cordova.getActivity().getApplication());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "activity result in plugin: requestCode(" + requestCode + "), resultCode(" + resultCode + ")");
        callbackManager.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
        if (action.equals("activateApp")) {
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    AppEventsLogger.activateApp(cordova.getActivity().getApplication());
                }
            });

            return true;
        }
        return false;
    }

    private void enableHybridAppEvents() {
        try {
            Context appContext = cordova.getActivity().getApplicationContext();
            Resources res = appContext.getResources();
            int enableHybridAppEventsId = res.getIdentifier("fb_hybrid_app_events", "bool", appContext.getPackageName());
            boolean enableHybridAppEvents = enableHybridAppEventsId != 0 && res.getBoolean(enableHybridAppEventsId);
            if (enableHybridAppEvents) {
                AppEventsLogger.augmentWebView((WebView) this.webView.getView(), appContext);
                Log.d(TAG, "FB Hybrid app events are enabled");
            } else {
                Log.d(TAG, "FB Hybrid app events are not enabled");
            }
        } catch (Exception e) {
            Log.d(TAG, "FB Hybrid app events cannot be enabled");
        }
    }

}
