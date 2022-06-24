package com.silvinda.keripikstore.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.silvinda.keripikstore.databinding.ActivityAddItemBinding.inflate
import com.silvinda.keripikstore.databinding.FragmentDashboardBinding
import com.silvinda.keripikstore.detail.DetailActivity
import com.silvinda.keripikstore.viewmodel.KeripikViewModel
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*


class HomeFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var mKeripikViewModel: KeripikViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
//
//        // Recyclerview
////        val adapter = TerlarisAdapter()
//        val adapter2 = LainnyaAdapter(requireContext())
//
//        val recyclerView = view.rv_terlaris
//        val recyclerView2 = view.rv_lainnya
//
////        recyclerView.adapter = adapter
//        recyclerView2.adapter = adapter2
//
////        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        binding.rvTerlaris.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView2.layoutManager = LinearLayoutManager(requireContext())
//
//        // KeripikViewModel
//        mKeripikViewModel = ViewModelProvider(this)[KeripikViewModel::class.java]
//        mKeripikViewModel.dataTerlaris.observe(viewLifecycleOwner) { it ->
//            adapter = TerlarisAdapter(it) {
//                startActivity(Intent(context, DetailActivity::class.java).putExtra("keripik", it))
//            }
//        }
//
//        mKeripikViewModel.dataLainnya.observe(viewLifecycleOwner) {
//            adapter2.setData(it)
//        }
//
//        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mKeripikViewModel = ViewModelProvider(this)[KeripikViewModel::class.java]
        binding.rvTerlaris.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvLainnya.layoutManager = LinearLayoutManager(requireContext().applicationContext)

        mKeripikViewModel.dataTerlaris.observe(viewLifecycleOwner){ it ->
            binding.rvTerlaris.adapter = TerlarisAdapter(it){
                startActivity(Intent(context, DetailActivity::class.java).putExtra("keripik", it))
            }
        }

        mKeripikViewModel.dataLainnya.observe(viewLifecycleOwner) { it ->
            binding.rvLainnya.adapter = LainnyaAdapter(it) {
                startActivity(Intent(context, DetailActivity::class.java).putExtra("keripik", it))
            }

        }


    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.delete_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == R.id.menu_delete){
//            deleteAllUsers()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun deleteAllUsers() {
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setPositiveButton("Yes") { _, _ ->
//            mKeripikViewModel.deleteAllUsers()
//            Toast.makeText(
//                requireContext(),
//                "Successfully removed everything",
//                Toast.LENGTH_SHORT).show()
//        }
//        builder.setNegativeButton("No") { _, _ -> }
//        builder.setTitle("Delete everything?")
//        builder.setMessage("Are you sure you want to delete everything?")
//        builder.create().show()
//    }
}