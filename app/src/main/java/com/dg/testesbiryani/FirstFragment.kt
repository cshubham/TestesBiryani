package com.dg.testesbiryani

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dg.testesbiryani.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("shch", "onAttach frag1")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("shch", "onCreate frag1")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        Log.d("shch", "onCreateview frag1 " + Thread.currentThread().name)
        binding.buttonFirst.setOnClickListener {
            //  findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Log.d("shch", " First Fragment ovc " + Thread.currentThread().name)
            val dialog = DialogFrag()
        //    dialog.show(childFragmentManager, MainActivity2::class.java)
//                val intent = Intent(context, MainActivity3::class.java)
//                startActivity(intent)

        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("shch", "onViewCreate frag1")

//        binding.buttonFirst.setOnClickListener {
//          //  findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//Log.d("shch", " First Fragment ovc " + Thread.currentThread().name)
//
////                val intent = Intent(context, MainActivity3::class.java)
////                startActivity(intent)
//
//        }

        binding.buttonFirst2.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            val intent = Intent(context, MainActivity3::class.java)
            startActivity(intent)

        }


    }

    override fun onResume() {
        super.onResume()
        Log.d("shch", "onResume frag1")
    }

    override fun onPause() {
        super.onPause()
        Log.d("shch", "onPause frag1")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("shch", "onDestroyView frag1")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("shch", "onDestroy frag1")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("shch", "onDetach frag1")
    }

}