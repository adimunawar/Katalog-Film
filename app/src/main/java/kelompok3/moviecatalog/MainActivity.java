package kelompok3.moviecatalog;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {

    ListView listView;
    EditText editJudul;
    ImageView imgPoster;
    Button btnCari;
    MovieAdapter adapter;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    @Override

    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
        adapter	= new MovieAdapter(this);
        adapter.notifyDataSetChanged();

        listView	= (ListView)findViewById(R.id.listviewmovie);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                MovieItem item = (MovieItem)parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetailMovie.class);

                intent.putExtra(DetailMovie.EXTRA_TITLE, item.getMovie_title());
                intent.putExtra(DetailMovie.EXTRA_OVERVIEW,
                        item.getMovie_description());
                intent.putExtra(DetailMovie.EXTRA_RELEASE_DATE, item.getMovie_date());

                intent.putExtra(DetailMovie.EXTRA_POSTER_JPG, item.getMovie_image());
                intent.putExtra(DetailMovie.EXTRA_RATE_COUNT,
                        item.getMovie_rate_count());
                intent.putExtra(DetailMovie.EXTRA_RATE, item.getMovie_rate());

                startActivity(intent);
            }
        });
        editJudul	= (EditText)findViewById(R.id.edtmovie);
        imgPoster	=	(ImageView)findViewById(R.id.imgMovie);
        btnCari	=	(Button)findViewById(R.id.btncari);
        btnCari.setOnClickListener(movieListener);

        String judul_movie = editJudul.getText().toString();

        Bundle bundle = new Bundle(); bundle.putString(EXTRAS_MOVIE, judul_movie);

        getLoaderManager().initLoader(0, bundle, this);
    }


    @Override

    public Loader<ArrayList<MovieItem>> onCreateLoader(int i, Bundle bundle) { String judulMovie = "";

        if (bundle != null){
            judulMovie = bundle.getString(EXTRAS_MOVIE);
        }

        return new MovieLoader(this,judulMovie);
    }

    @Override

    public void onLoadFinished(Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> MovieItem) {

        adapter.setData(MovieItem);
    }

    @Override

    public void onLoaderReset(Loader<ArrayList<MovieItem>> loader) { adapter.setData(null);

    }

    View.OnClickListener movieListener = new View.OnClickListener() { @Override
    public void onClick(View view) {

        String judul_movie = editJudul.getText().toString(); if(TextUtils.isEmpty(judul_movie)){

            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, judul_movie);
        getLoaderManager().restartLoader(0, bundle, MainActivity.this);
    }
    };
}
