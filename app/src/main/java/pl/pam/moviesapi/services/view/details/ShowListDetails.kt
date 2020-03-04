package pl.pam.moviesapi.services.view.details


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.show_list_details.*

import pl.pam.moviesapi.R
import pl.pam.moviesapi.services.dto.Show
import pl.pam.moviesapi.utils.Utils

/**
 * A simple [Fragment] subclass.
 */
class ShowListDetails : Fragment() {

    private lateinit var show: Show;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        show = ShowListDetailsArgs.fromBundle(arguments!!).show;
        return inflater.inflate(R.layout.show_list_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        show_details_description.text = show.description;
        show_details_release_date.text = show.releaseDate;
        if(show.originalTitle!==null)
            show_details_title.text = show.originalTitle;
        else
            show_details_title.text = show.originalName;

        if (show.releaseDate !== null)
            show_details_release_date.text = show.releaseDate
        else
            show_details_release_date.text = show.firstAirDate

        show_details_popularity.text = show.popularity.toString();

        show_details_vote_average.text = show.voteAverage.toString();

        show_details_vote_count.text = show.voteCount.toString();


        val posterUrl: String = Utils.getInstance().getProperty("api.img.url") as String
        if (show.posterPath != null && show.posterPath.isNotEmpty())
            Picasso.get().load(posterUrl + "w780/" + show.posterPath.removePrefix("/"))
                .into(show_details_poster)
    }

}
