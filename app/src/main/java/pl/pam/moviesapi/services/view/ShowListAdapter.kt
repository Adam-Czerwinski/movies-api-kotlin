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


        holder.recyclerviewMovieBinding.layoutShow.setOnTouchListener(object :
            OnSwipeTouchListener(holder.recyclerviewMovieBinding.layoutShow.context) {

            override fun onSwipeTop() {
                Log.i(ShowListAdapter::class.java.simpleName,"onSwipeTop")

                super.onSwipeTop()
            }

            override fun onSwipeBottom() {
                Log.i(ShowListAdapter::class.java.simpleName,"onSwipeBottom")
                super.onSwipeBottom()
            }

            override fun onSwipeLeft() {
                Log.i(ShowListAdapter::class.java.simpleName,"onSwipeLeft")
                fragment.prevPage.callOnClick();
                super.onSwipeLeft()
            }

            override fun onSwipeRight() {
                Log.i(ShowListAdapter::class.java.simpleName,"onSwipeRight")
                fragment.nextPage.callOnClick();
                super.onSwipeRight()
            }
        })

    }

    inner class MoviesViewHolder(
        val recyclerviewMovieBinding: ShowBinding
    ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)


    open class OnSwipeTouchListener(ctx: Context) : OnTouchListener {

        private val gestureDetector: GestureDetector

        companion object {

            private val SWIPE_THRESHOLD = 100
            private val SWIPE_VELOCITY_THRESHOLD = 100
        }

        init {
            gestureDetector = GestureDetector(ctx, GestureListener())
        }

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        private inner class GestureListener : SimpleOnGestureListener() {


            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                var result = false
                try {
                    val diffY = e2.y - e1.y
                    val diffX = e2.x - e1.x
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight()
                            } else {
                                onSwipeLeft()
                            }
                            result = true
                        }
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom()
                        } else {
                            onSwipeTop()
                        }
                        result = true
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }

                return result
            }


        }

        open fun onSwipeRight() {}

        open fun onSwipeLeft() {}

        open fun onSwipeTop() {}

        open fun onSwipeBottom() {}
    }


}