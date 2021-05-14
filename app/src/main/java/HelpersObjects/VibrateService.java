package HelpersObjects;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.widget.Toast;

public class VibrateService extends Service {
    Vibrator vibrator;

    // pattern for vibration state
    long[] pattern = {50, 0};

    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        //Указываем длительность вибрации в миллисекундах
        vibrator.vibrate(50);
    }

    public void vibroOnError(){
        vibrator.vibrate(pattern, 2);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}