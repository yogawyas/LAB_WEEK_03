package com.example.lab_week_03

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ListFragment : Fragment() {

    private var coffeeListener: CoffeeListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CoffeeListener) {
            coffeeListener = context
        } else {
            throw RuntimeException("$context must implement CoffeeListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        coffeeListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coffeeList = listOf<View>(
            view.findViewById(R.id.affogato),
            view.findViewById(R.id.americano),
            view.findViewById(R.id.latte)
        )

        coffeeList.forEach { coffee ->
            coffee.setOnClickListener {
                coffeeListener?.onSelected(coffee.id)
            }
        }
    }

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}
