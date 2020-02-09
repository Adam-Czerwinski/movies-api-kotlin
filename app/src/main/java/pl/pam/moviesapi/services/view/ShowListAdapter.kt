package pl.pam.moviesapi.services.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pl.pam.moviesapi.R
import pl.pam.moviesapi.databinding.ShowBinding
import pl.pam.moviesapi.services.dto.Show
import pl.pam.moviesapi.utils.Utils

class ShowListAdapter(private val showList: MutableList<Show>) :
    RecyclerView.Adapter<ShowListAdapter.MoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.show,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return showList.size
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.recyclerviewMovieBinding.show = showList[position];

        val posterUrl: String = Utils.getInstance().getProperty("api.img.url") as String
        if(showList[position].posterPath!= null && showList[position].posterPath.isNotEmpty())
        Picasso.get().load(posterUrl + "w300/" + showList[position].posterPath.removePrefix("/"))
            .into(holder.recyclerviewMovieBinding.showPoster)
    }

    inner class MoviesViewHolder(
        val recyclerviewMovieBinding: ShowBinding
    ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)
}