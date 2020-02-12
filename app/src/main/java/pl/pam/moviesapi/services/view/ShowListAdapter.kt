package pl.pam.moviesapi.services.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.View.OnTouchListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_show_list.*
import pl.pam.moviesapi.databinding.ShowBinding
import pl.pam.moviesapi.services.dto.Show
import pl.pam.moviesapi.utils.Utils


class ShowListAdapter(private val showList: MutableList<Show>, private val fragment: ShowList) :
    RecyclerView.Adapter<ShowListAdapter.MoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                pl.pam.moviesapi.R.layout.show,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return showList.size
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.recyclerviewMovieBinding.show = showList[position];

        if (showList[position].originalTitle !== null)
            holder.recyclerviewMovieBinding.showTitle.text = showList[position].originalTitle
        else
            holder.recyclerviewMovieBinding.showTitle.text = showList[position].originalName

        if (showList[position].releaseDate !== null)
            holder.recyclerviewMovieBinding.showReleaseDate.text = showList[position].releaseDate
        else
            holder.recyclerviewMovieBinding.showReleaseDate.text = showList[position].firstAirDate

        val posterUrl: String = Utils.getInstance().getProperty("api.img.url") as String
        if (showList[position].posterPath != null && showList[position].posterPath.isNotEmpty())
            Picasso.get().load(posterUrl + "w300/" + showList[position].posterPath.removePrefix("/"))
                .into(holder.recyclerviewMovieBinding.showPoster)







        holder.recyclerviewMovieBinding.layoutShow.setOnLongClickListener {

            println("łapię")
            it.findNavController()
                .navigate(ShowListDirections.actionShowListToShowListDetails(showList[position]))
            //your action on long click
            true
        };

    }

    inner class MoviesViewHolder(
        val recyclerviewMovieBinding: ShowBinding
    ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)


}