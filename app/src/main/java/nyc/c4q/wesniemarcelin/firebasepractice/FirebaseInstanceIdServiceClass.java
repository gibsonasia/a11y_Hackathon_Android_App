package nyc.c4q.wesniemarcelin.firebasepractice;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by wesniemarcelin on 6/3/17.
 */

public class FirebaseInstanceIdServiceClass extends FirebaseInstanceIdService {

    @Override
    @android.support.annotation.WorkerThread
    public void onTokenRefresh() {

        /* obtain the current InstanceId token: */
        String token = FirebaseInstanceId.getInstance().getToken();
    }
}
