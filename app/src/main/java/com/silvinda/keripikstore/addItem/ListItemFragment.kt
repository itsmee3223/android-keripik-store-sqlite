package com.silvinda.keripikstore.addItem

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.silvinda.keripikstore.R
import com.silvinda.keripikstore.home.HomeScreenActivity
import com.silvinda.keripikstore.viewmodel.KeripikViewModel
import kotlinx.android.synthetic.main.fragment_list_item.view.*

class ListItemFragment : Fragment() {

    private lateinit var mKeripikViewModel: KeripikViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_item, container, false)

        // Recyclerview
        val adapter = ItemListAdapter(requireContext())
        val recyclerView = view.rc_history
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // KeripikViewModel
        mKeripikViewModel = ViewModelProvider(this)[KeripikViewModel::class.java]
        mKeripikViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        view.iv_tambah.setOnClickListener {
            val intent = Intent(context, AddItemActivity::class.java)
            startActivity(intent)
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
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