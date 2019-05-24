package kelompok3.moviecatalog;

import org.json.JSONObject;

public class MovieItem {

    private String movie_title;
    private String movie_description;
    private String movie_date;
    private String movie_image;
    private String movie_rate_count;
    private String movie_rate;

    public MovieItem(JSONObject object){
        try {

            String title	= object.getString("title");
            String description	= object.getString("overview");
            String release_date = object.getString("release_date");
            String image	= object.getString("poster_path");
            String rate_count	= object.getString("vote_count");
            String rate	= object.getString("vote_average");
            this.movie_title  = title;
            this.movie_description = description;
            this.movie_date = release_date;
            this.movie_image = image;
            this.movie_rate_count = rate_count;
            this.movie_rate = rate;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String mov_title) { this.movie_title = mov_title;

    }

    public String getMovie_description() {
        return movie_description;
    }

    public void setMovie_description(String mov_description) { this.movie_description = mov_description;

    }

    public String getMovie_date() {

        return movie_date;
    }

    public void setMovie_date(String mov_date) { this.movie_date = mov_date;

    }

    public String getMovie_image() {
        return movie_image;
    }

    public void setMovie_image(String mov_image) { this.movie_image = mov_image;
    }

    public String getMovie_rate_count() {
        return movie_rate_count;
    }

    public void setMovie_rate_count(String mov_rate_count) { this.movie_rate_count = mov_rate_count;
    }

    public String getMovie_rate() {
        return movie_rate;
    }

    public void setMovie_rate(String mov_rate) { this.movie_rate = mov_rate;

    }
}
