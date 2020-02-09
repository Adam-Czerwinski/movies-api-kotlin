package pl.pam.moviesapi.services.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_show_list.*
import kotlinx.android.synthetic.main.fragment_show_list.view.*
import pl.pam.moviesapi.MainActivity

import pl.pam.moviesapi.R
import pl.pam.moviesapi.services.api.poster.PosterApiService
import pl.pam.moviesapi.services.api.poster.PosterApiServiceGenerator
import pl.pam.moviesapi.services.api.show.MoviesApiService
import pl.pam.moviesapi.services.api.show.MoviesApiServiceGenerator
import pl.pam.moviesapi.services.dto.MoviesListResponseDTO
import pl.pam.moviesapi.services.dto.Show
import pl.pam.moviesapi.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ShowList : Fragment() {

    private lateinit var viewModel: ShowViewModel
    private lateinit var showList: MutableList<Show>
    private lateinit var mas: MoviesApiService;
    private lateinit var rview: RecyclerView;
    private var currentPage:Int = 1;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_show_list, container, false)

        rview = rootView.findViewById(R.id.show_rview) as RecyclerView
        rview.layoutManager = LinearLayoutManager(activity)


        this.initializeData()

        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ShowViewModel::class.java)
        button_search.setOnClickListener {
currentPage = 1;
            val input = search_input.text.toString();

            if (input.isEmpty()) {
                this.initializeData();

            } else {
                val call = mas.findMovies(Utils.getInstance().getProperty("api.key") as String, input,currentPage.toString());

                call.enqueue(object : Callback<MoviesListResponseDTO?> {
                    override fun onFailure(call: Call<MoviesListResponseDTO?>, t: Throwable) {
                        Log.w(MainActivity::class.simpleName, "Nie udało się pobrać szukanych filmmów");
                    }

                    override fun onResponse(
                        call: Call<MoviesListResponseDTO?>,
                        response: Response<MoviesListResponseDTO?>
                    ) {
                        if (response.code() != 200)
                            Log.w(
                                MainActivity::class.simpleName,
                                "Nie udało się pobrać szukanych filmów"
                            );
                        else {
                            showList = response.body()!!.movies
                            if(showList.size!=0)
                            rview.adapter = ShowListAdapter(showList)
                            Log.i(MainActivity::class.simpleName, "Pobrano bazę szukanych filmów");
                        }
                    }
                })
            }
        }

        nextPage.setOnClickListener {
            currentPage++;
            val input = search_input.text.toString();

            if (input.isEmpty()) {
                this.initializeData();

            } else {
                val call = mas.findMovies(Utils.getInstance().getProperty("api.key") as String, input,currentPage.toString());

                call.enqueue(object : Callback<MoviesListResponseDTO?> {
                    override fun onFailure(call: Call<MoviesListResponseDTO?>, t: Throwable) {
                        Log.w(MainActivity::class.simpleName, "Nie udało się pobrać szukanych filmmów");
                    }

                    override fun onResponse(
                        call: Call<MoviesListResponseDTO?>,
                        response: Response<MoviesListResponseDTO?>
                    ) {
                        if (response.code() != 200)
                            Log.w(
                                MainActivity::class.simpleName,
                                "Nie udało się pobrać szukanych filmów"
                            );
                        else {
                            showList = response.body()!!.movies
                            if(showList.size!=0)
                            rview.adapter = ShowListAdapter(showList)
                            Log.i(MainActivity::class.simpleName, "Pobrano bazę szukanych filmów");
                        }
                    }
                })
            }
        }

        prevPage.setOnClickListener {
            if(currentPage!=1)
            {
                currentPage--;

                val input = search_input.text.toString();

                if (input.isEmpty()) {
                    this.initializeData();

                } else {
                    val call = mas.findMovies(Utils.getInstance().getProperty("api.key") as String, input,currentPage.toString());

                    call.enqueue(object : Callback<MoviesListResponseDTO?> {
                        override fun onFailure(call: Call<MoviesListResponseDTO?>, t: Throwable) {
                            Log.w(MainActivity::class.simpleName, "Nie udało się pobrać szukanych filmmów");
                        }

                        override fun onResponse(
                            call: Call<MoviesListResponseDTO?>,
                            response: Response<MoviesListResponseDTO?>
                        ) {
                            if (response.code() != 200)
                                Log.w(
                                    MainActivity::class.simpleName,
                                    "Nie udało się pobrać szukanych filmów"
                                );
                            else {
                                showList = response.body()!!.movies

                                if(showList.size!=0)
                                rview.adapter = ShowListAdapter(showList)



                                Log.i(MainActivity::class.simpleName, "Pobrano bazę szukanych filmów");
                            }
                        }
                    })
                }
            }


        }
    }

    private fun initializeData(){
        mas = MoviesApiServiceGenerator.createService(MoviesApiService::class.java)
        val callMovies: Call<MoviesListResponseDTO> =
            mas.getMovies(Utils.getInstance().getProperty("api.key") as String, currentPage.toString())
        callMovies.enqueue(object : Callback<MoviesListResponseDTO?> {
            override fun onFailure(call: Call<MoviesListResponseDTO?>, t: Throwable) {
                Log.w(MainActivity::class.simpleName, "Nie udało się pobrać filmów");
            }

            override fun onResponse(
                call: Call<MoviesListResponseDTO?>,
                response: Response<MoviesListResponseDTO?>
            ) {
                if (response.code() != 200)
                    Log.w(MainActivity::class.simpleName, "Nie udało się pobrać filmów");
                else {
                    showList = response.body()!!.movies

                    rview.adapter = ShowListAdapter(showList)
                    Log.i(MainActivity::class.simpleName, "Pobrano bazę filmów");
                }
            }
        })
    }

}

