package android.eservices.insidefridge;

import android.app.Application;

import android.eservices.insidefridge.data.di.FakeDependencyInjection;

import com.facebook.stetho.Stetho;

public class CocktailApplication extends Application {

    public static final String API_KEY = "307a2dfe3cmshab5301ebabb5427p126057jsn155145a71bdb";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}
