package kelompok3.moviecatalog;

import android.content.AsyncTaskLoader;
import android.content.Context;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class MovieLoader extends AsyncTaskLoader<ArrayList<MovieItem>> {

    private ArrayList<MovieItem> mData;
    private boolean mHasResult = false;

    private String mtitleMovie;

    public MovieLoader(final Context context, String titleMovie) { super(context);

        onContentChanged();
        this.mtitleMovie = titleMovie;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    public void deliverResult(ArrayList<MovieItem> data) { mData = data;

        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = "a1e2f056e152242a9bf38f89a173bcb9";
    private void onReleaseResources(ArrayList<MovieItem> data) { }

    @Override

    public ArrayList<MovieItem> loadInBackground() { SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieItem> MovieItem_ = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" +
                API_KEY+"&language=en-US&query="+mtitleMovie;
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override

            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {

                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for	(int i = 0 ;
                            i < list.length() ;
                            i++){ JSONObject movie = list.getJSONObject(i);
                        MovieItem MovieItem = new MovieItem(movie);
                        MovieItem_.add(MovieItem);

                    }
                }catch (Exception e){
                    e.printStackTrace();

                }
            }

            @Override

            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return MovieItem_;
    }
}

