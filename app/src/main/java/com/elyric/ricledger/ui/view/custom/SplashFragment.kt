package com.elyric.ricledger.ui.view.custom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.elyric.ricledger.R
import com.elyric.ricledger.ui.MainActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SplashFragment : Fragment(), AppToolBarOwner{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        // 延时跳转
        view.postDelayed({
            navigateToTargetPage(navController)
        },3000)
    }

    override fun onResume() {
        super.onResume()
        val dispatcher = (requireActivity() as MainActivity).getToolbarDispatcher()
        dispatcher.publish(provideToolBarState())
    }

    private fun navigateToTargetPage(navController: NavController) {

        val option = NavOptions.Builder()
            .setPopUpTo(R.id.splashFragment,true)
            .build()
        navController.navigate(R.id.billListFragment,null,option)
    }

    override fun provideToolBarState(): AppToolBarState {
        return AppToolBarState.FullScreen(
            R.id.splashFragment
        )
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}