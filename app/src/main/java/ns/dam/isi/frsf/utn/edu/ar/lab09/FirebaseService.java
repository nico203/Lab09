package ns.dam.isi.frsf.utn.edu.ar.lab09;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdInternalReceiver;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by NicolasAndres on 7/2/2017.
 */

public class FirebaseService extends FirebaseInstanceIdService
{
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTokenRefresh() {
        // obtener token InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        saveTokenToPrefs(refreshedToken);
    }

    private void saveTokenToPrefs(String _token) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("registration_id", _token);
        editor.apply();
        // luego en cualquier parte de la aplicación podremos recuperar el token con
        // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // preferences.getString("registration_id", null);
    }

}
